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

import com.example.Services.CoursServices;
import com.example.model.Cours;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/cours")
public class CourControllers {
    
    @Autowired
    private CoursServices courServices;
    @Autowired
    private ObjectMapper mapper;

    /**
     * Handles GET requests to retrieve all cours data.
     *
     * @return ResponseEntity containing the JSON representation of all cours data
     *         with HTTP status 200 (OK) if successful, or an error message with
     *         HTTP status 500 (Internal Server Error) if there is a processing error.
     */
    @GetMapping
    public ResponseEntity<String> getAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        try {
            String json = mapper.writeValueAsString(courServices.getAll());
            return new ResponseEntity<>(json, headers, HttpStatus.OK);

        } catch (JsonProcessingException e) {
            String errorJson = "{\"error\": \"Error processing cours data\"}";
            return new ResponseEntity<>(errorJson, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Handles HTTP GET requests to retrieve a course by its ID.
     *
     * @param id the ID of the course to retrieve
     * @return a ResponseEntity containing the course data in JSON format if found,
     *         or an error message in JSON format if an error occurs during processing
     */
    @GetMapping("/{id}")
    public ResponseEntity<String> getByID(@PathVariable("id") int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        try {
            String json = mapper.writeValueAsString(courServices.getById(id));
            return new ResponseEntity<>(json, headers, HttpStatus.OK);

        } catch (JsonProcessingException e) {
            String errorJson = "{\"error\": \"Error processing cours data\"}";
            return new ResponseEntity<>(errorJson, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Inserts a new Cours object into the system.
     *
     * @param cour the Cours object to be inserted
     * @return a ResponseEntity containing a JSON message indicating the result of the operation
     *         - If the insertion is successful, returns a JSON message with the inserted Cours ID and HTTP status 201 (Created)
     *         - If an error occurs, returns a JSON error message and HTTP status 500 (Internal Server Error)
     */
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Cours cour) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        try {
            courServices.insert(cour);

            String json = "{\"inserted\": \"Cour with id " + cour.getId() + " inserted\"}";
            return new ResponseEntity<>(json, headers, HttpStatus.CREATED);

        } catch (Exception e) {
            String errorJson = "{\"error\": \"Error processing etudiant data\"}";
            return new ResponseEntity<>(errorJson, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates an existing Cours resource with the provided data.
     *
     * @param id   the ID of the Cours to update
     * @param cour the Cours object containing the updated data
     * @return a ResponseEntity containing a JSON message indicating the result of the update operation
     *         - HTTP 200 OK if the update was successful
     *         - HTTP 404 NOT FOUND if the Cours with the specified ID does not exist
     *         - HTTP 500 INTERNAL SERVER ERROR if an error occurred during the update process
     */
    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody Cours cour) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        try {
            Cours existingCour = courServices.getById(id);
            if (existingCour == null) {
                return new ResponseEntity<>("{\"error\": \"Cour not found\"}", headers, HttpStatus.NOT_FOUND);
            }

            if (cour.getDateDebut() != null) existingCour.setDateDebut(cour.getDateDebut());
            if (cour.getDateFin() != null) existingCour.setDateFin(cour.getDateFin());
            if (cour.getEtudiants() != null) existingCour.setEtudiants(cour.getEtudiants());
            if (cour.getFormateur() != null) existingCour.setFormateur(cour.getFormateur());

            courServices.update(existingCour);

            String json = "{\"updated\": \"Cour with id " + id + " updated\"}";
            return new ResponseEntity<>(json, headers, HttpStatus.OK);

        } catch (Exception e) {
            String errorJson = "{\"error\": \"Error processing update data\"}";
            return new ResponseEntity<>(errorJson, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes a Cour resource by its ID.
     *
     * @param id the ID of the Cour to be deleted
     * @return a ResponseEntity containing a JSON message indicating the result of the delete operation
     *         - If the Cour is successfully deleted, returns a JSON message with HTTP status 200 (OK)
     *         - If the Cour is not found, returns a JSON error message with HTTP status 404 (Not Found)
     *         - If an error occurs during the delete operation, returns a JSON error message with HTTP status 500 (Internal Server Error)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        try {
            Cours existingCour = courServices.getById(id);
            if (existingCour == null) {
                return new ResponseEntity<>("{\"error\": \"Cour not found\"}", headers, HttpStatus.NOT_FOUND);
            }

            courServices.delete(id);
            String json = "{\"message\": \"Cour with id " + id + " deleted\"}";
            return new ResponseEntity<>(json, headers, HttpStatus.OK);

        } catch (Exception e) {
            String errorJson = "{\"error\": \"Error processing delete data\"}" + e.getMessage();
            return new ResponseEntity<>(errorJson, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

