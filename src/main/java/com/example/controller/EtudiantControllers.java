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

import com.example.DTO.EtudiantResponseDTO;
import com.example.Services.EtudiantServices;
import com.example.model.Etudiants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/etudiants")
public class EtudiantControllers {

    @Autowired
    private EtudiantServices etudiantServices;
    @Autowired
    private ObjectMapper mapper;

    /**
     * Handles HTTP GET requests to retrieve all students.
     *
     * @return ResponseEntity containing a JSON representation of all students
     *         and appropriate HTTP headers. If an error occurs during JSON
     *         processing, returns a ResponseEntity with an error message and
     *         HTTP status 500 (Internal Server Error).
     */
    @GetMapping
    public ResponseEntity<String> getAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        try {
            String json = mapper.writeValueAsString(etudiantServices.getAll());
            return new ResponseEntity<>(json, headers, HttpStatus.OK);

        } catch (JsonProcessingException e) {
            String errorJson = "{\"error\": \"Error processing etudiants data\"}";
            return new ResponseEntity<>(errorJson, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Handles HTTP GET requests to retrieve an Etudiant by its ID.
     *
     * @param id the ID of the Etudiant to retrieve
     * @return a ResponseEntity containing the Etudiant data in JSON format and HTTP status code 200 (OK) if successful,
     *         or an error message in JSON format and HTTP status code 500 (Internal Server Error) if there is a processing error
     */
    @GetMapping("/{id}")
    public ResponseEntity<String> getByID(@PathVariable int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        try {
            String json = mapper.writeValueAsString(etudiantServices.getById(id));
            return new ResponseEntity<>(json, headers, HttpStatus.OK);

        } catch (JsonProcessingException e) {
            String errorJson = "{\"error\": \"Error processing etudiants data\"}";
            return new ResponseEntity<>(errorJson, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Inserts a new Etudiant record.
     *
     * @param etudiant The Etudiants object to be inserted.
     * @return A ResponseEntity containing the JSON representation of the inserted EtudiantResponseDTO
     *         and the appropriate HTTP status code.
     */
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Etudiants etudiant) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        try {
            etudiantServices.insert(etudiant);

            EtudiantResponseDTO etudiantDTO = new EtudiantResponseDTO(
                etudiant.getNom(),
                etudiant.getPrenom(),
                etudiant.getEmail(),
                etudiant.getTelephone()
            );

            String json = mapper.writeValueAsString(etudiantDTO);
            return new ResponseEntity<>(json, headers, HttpStatus.CREATED);

        } catch (JsonProcessingException e) {
            String errorJson = "{\"error\": \"Error processing etudiant data\"}";
            return new ResponseEntity<>(errorJson, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates an existing Etudiant with the provided data.
     *
     * @param id The ID of the Etudiant to update.
     * @param etudiant The Etudiant object containing the updated data.
     * @return A ResponseEntity containing a success message or an error message.
     *         - 200 OK if the update is successful.
     *         - 404 NOT FOUND if the Etudiant with the given ID does not exist.
     *         - 500 INTERNAL SERVER ERROR if there is an error processing the update.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody Etudiants etudiant) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        try {
            Etudiants existingEtudiant = etudiantServices.getById(id);
            if (existingEtudiant == null) {
                return new ResponseEntity<>("{\"error\": \"Etudiant not found\"}", headers, HttpStatus.NOT_FOUND);
            }

            if (etudiant.getNom() != null) existingEtudiant.setNom(etudiant.getNom());
            if (etudiant.getPrenom() != null) existingEtudiant.setPrenom(etudiant.getPrenom());
            if (etudiant.getEmail() != null) existingEtudiant.setEmail(etudiant.getEmail());
            if (etudiant.getTelephone() != null) existingEtudiant.setTelephone(etudiant.getTelephone());

            etudiantServices.update(existingEtudiant);

            String json = "{\"updated\": \"Etudiant with id " + id + " updated\"}";
            return new ResponseEntity<>(json, headers, HttpStatus.OK);

        } catch (Exception e) {
            String errorJson = "{\"error\": \"Error processing update data\"}";
            return new ResponseEntity<>(errorJson, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes an Etudiant by its ID.
     *
     * @param id the ID of the Etudiant to delete
     * @return a ResponseEntity containing a JSON message indicating the result of the delete operation
     *         - HTTP 200 OK if the Etudiant was successfully deleted
     *         - HTTP 404 NOT FOUND if the Etudiant was not found
     *         - HTTP 500 INTERNAL SERVER ERROR if there was an error processing the delete request
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        try {
            Etudiants existingEtudiant = etudiantServices.getById(id);
            if (existingEtudiant == null) {
                return new ResponseEntity<>("{\"error\": \"Etudiant not found\"}", headers, HttpStatus.NOT_FOUND);
            }

            etudiantServices.delete(id);
            String json = "{\"message\": \"Etudiant with id " + id + " deleted\"}";
            return new ResponseEntity<>(json, headers, HttpStatus.OK);

        } catch (Exception e) {
            String errorJson = "{\"error\": \"Error processing delete data\"}";
            return new ResponseEntity<>(errorJson, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
