package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Services.AbsenceServices;
import com.example.model.Absences;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/absences")
public class AbsencesControllers {

    @Autowired
    private AbsenceServices absencesServices;
    @Autowired
    private ObjectMapper mapper;

    /**
     * Handles HTTP GET requests to retrieve all absences.
     *
     * @return ResponseEntity containing a JSON representation of all absences
     *         and appropriate HTTP headers and status code.
     *         If an error occurs during JSON processing, returns an error message
     *         with HTTP status 500 (Internal Server Error).
     */
    @GetMapping
    public ResponseEntity<String> getAll(){
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        try {
            String json = mapper.writeValueAsString(absencesServices.getAll());
            return new ResponseEntity<>(json, headers, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            String errorJson = "{\"error\": \"Error processing cours data\"}";
            return new ResponseEntity<>(errorJson, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Handles GET requests to retrieve an absence by its ID.
     *
     * @param id the ID of the absence to retrieve
     * @return a ResponseEntity containing the JSON representation of the absence
     *         if found, or an error message if an exception occurs
     */
    @GetMapping("/{id}")
    public ResponseEntity<String> getByID(@PathVariable("id") int id){
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        try {
            String json = mapper.writeValueAsString(absencesServices.getById(id));
            return new ResponseEntity<>(json, headers, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            String errorJson = "{\"error\": \"Error processing cours data\"}";
            return new ResponseEntity<>(errorJson, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Inserts a new absence record.
     *
     * @param absence the Absences object to be inserted
     * @return a ResponseEntity containing the inserted Absences object in JSON format and the HTTP status code
     */
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Absences absence){
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        try {
            absencesServices.insert(absence);
            String json = "{\"inserted\": \"Absence with id " + absence.getId() + " inserted\"}";
            return new ResponseEntity<>(json, headers, HttpStatus.CREATED);
        } catch (Exception e) {
            String errorJson = "{\"error\": \"Error processing cours data\"}";
            return new ResponseEntity<>(errorJson, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates an existing Absence record with the provided data.
     *
     * @param absence the Absence object containing the updated data
     * @param id the ID of the Absence record to update
     * @return a ResponseEntity containing a JSON message indicating the result of the update operation
     *         - HTTP 200 OK if the update was successful
     *         - HTTP 404 NOT FOUND if the Absence record with the specified ID does not exist
     *         - HTTP 500 INTERNAL SERVER ERROR if an error occurred during the update process
     */
    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody Absences absence, @PathVariable("id") int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        try {
            Absences existingAbsences = absencesServices.getById(id);
            if (existingAbsences == null) {
                return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
            }
            if (absence.getDateDebut() != null) existingAbsences.setDateDebut(absence.getDateDebut());
            if (absence.getDateFin() != null) existingAbsences.setDateFin(absence.getDateFin());
            if (absence.getType() != null) existingAbsences.setType(absence.getType());
            if (absence.getEtudiant() != null) existingAbsences.setEtudiant(absence.getEtudiant());

            absencesServices.update(existingAbsences);
            String json = "{\"updated\": \"Absence with id " + id + " updated\"}";
            return new ResponseEntity<>(json, headers, HttpStatus.OK);
        } catch (Exception e) {
            String errorJson = "{\"error\": \"Error processing update data\"}" + e.getMessage();
            return new ResponseEntity<>(errorJson, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes an absence record by its ID.
     *
     * @param id the ID of the absence record to delete
     * @return a ResponseEntity containing a JSON message indicating the result of the delete operation
     *         - If the absence record is found and deleted, returns a 200 OK status with a success message.
     *         - If the absence record is not found, returns a 404 Not Found status with an error message.
     *         - If an error occurs during the delete operation, returns a 500 Internal Server Error status with an error message.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id){
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        try {
            Absences existingAbsences = absencesServices.getById(id);
            if (existingAbsences == null) {
                return new ResponseEntity<>("{\"error\": \"Absence not found\"}", headers, HttpStatus.NOT_FOUND);
            }

            absencesServices.delete(id);
            String json = "{\"message\": \"Absence with id " + id + " deleted\"}";
            return new ResponseEntity<>(json, headers, HttpStatus.OK);
        } catch (Exception e) {
            String errorJson = "{\"error\": \"Error processing delete data\"}";
            return new ResponseEntity<>(errorJson, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
