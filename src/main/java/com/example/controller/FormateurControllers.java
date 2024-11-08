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

import com.example.DTO.FormateurResponseDTO;
import com.example.Services.FormateursServices;
import com.example.model.Formateurs;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/formateurs")
public class FormateurControllers {

    @Autowired
    private FormateursServices formateurServices;
    @Autowired
    private ObjectMapper mapper;

    /**
     * Handles HTTP GET requests to retrieve all formateurs.
     *
     * @return ResponseEntity containing a JSON representation of all formateurs
     *         and appropriate HTTP headers. If an error occurs during JSON
     *         processing, returns a ResponseEntity with an error message and
     *         HTTP status 500 (Internal Server Error).
     */
    @GetMapping
    public ResponseEntity<String> getAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        try {
            String json = mapper.writeValueAsString(formateurServices.getAll());
            return new ResponseEntity<>(json, headers, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            String errorJson = "{\"error\": \"Error processing formateurs data\"}";
            return new ResponseEntity<>(errorJson, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Handles HTTP GET requests to retrieve an Formateur by its ID.
     *
     * @param id the ID of the Etudiant to retrieve
     * @return a ResponseEntity containing the Formateur data in JSON format and HTTP status code 200 (OK) if successful,
     *         or an error message in JSON format and HTTP status code 500 (Internal Server Error) if there is a processing error
     */
    @GetMapping("/{id}")
    public ResponseEntity<String> getByID(@PathVariable int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        try {
            String json = mapper.writeValueAsString(formateurServices.getById(id));
            return new ResponseEntity<>(json, headers, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            String errorJson = "{\"error\": \"Error processing formateurs data\"}";
            return new ResponseEntity<>(errorJson, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Inserts a new Formateur record.
     *
     * @param etudiant The Formateurs object to be inserted.
     * @return A ResponseEntity containing the JSON representation of the inserted FormateurResponseDTO
     *         and the appropriate HTTP status code.
     */
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Formateurs formateur) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        try {
            formateurServices.insert(formateur);

            FormateurResponseDTO formateurDTO = new FormateurResponseDTO(
                formateur.getNom(),
                formateur.getPrenom(),
                formateur.getEmail(),
                formateur.getTelephone()
            );

            String json = mapper.writeValueAsString(formateurDTO);
            return new ResponseEntity<>(json, headers, HttpStatus.CREATED);

        } catch (JsonProcessingException e) {
            String errorJson = "{\"error\": \"Error processing formateur data\"}";
            return new ResponseEntity<>(errorJson, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates an existing Formateur with the provided data.
     *
     * @param id The ID of the Formateur to update.
     * @param formateur The Formateur object containing the updated data.
     * @return A ResponseEntity containing a success message or an error message.
     *         - 200 OK if the update is successful.
     *         - 404 NOT FOUND if the Formateur with the given ID does not exist.
     *         - 500 INTERNAL SERVER ERROR if there is an error processing the update.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody Formateurs formateur) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        try {
            Formateurs existingFormateur = formateurServices.getById(id);
            if (existingFormateur == null) {
                return new ResponseEntity<>("{\"error\": \"Etudiant not found\"}", headers, HttpStatus.NOT_FOUND);
            }

            if (formateur.getNom() != null) existingFormateur.setNom(formateur.getNom());
            if (formateur.getPrenom() != null) existingFormateur.setPrenom(formateur.getPrenom());
            if (formateur.getEmail() != null) existingFormateur.setEmail(formateur.getEmail());
            if (formateur.getTelephone() != null) existingFormateur.setTelephone(formateur.getTelephone());

            formateurServices.update(existingFormateur);

            FormateurResponseDTO formateurDTO = new FormateurResponseDTO(
                formateur.getNom(),
                formateur.getPrenom(),
                formateur.getEmail(),
                formateur.getTelephone()
            );

            String json = mapper.writeValueAsString(formateurDTO);
            return new ResponseEntity<>(json, headers, HttpStatus.OK);

        } catch (JsonProcessingException e) {
            String errorJson = "{\"error\": \"Error processing update data\"}";
            return new ResponseEntity<>(errorJson, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes an Formateur by its ID.
     *
     * @param id the ID of the Formateur to delete
     * @return a ResponseEntity containing a JSON message indicating the result of the delete operation
     *         - HTTP 200 OK if the Formateur was successfully deleted
     *         - HTTP 404 NOT FOUND if the Formateur was not found
     *         - HTTP 500 INTERNAL SERVER ERROR if there was an error processing the delete request
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        try {
            Formateurs existingFormateur = formateurServices.getById(id);
            if (existingFormateur == null) {
                return new ResponseEntity<>("{\"error\": \"Formateur not found\"}", headers, HttpStatus.NOT_FOUND);
            }

            formateurServices.delete(id);
            String json = "{\"message\": \"Formateur " + id + " deleted\"}";
            return new ResponseEntity<>(json, headers, HttpStatus.OK);

        } catch (Exception e) {
            String errorJson = "{\"error\": \"Error processing delete data\"}";
            return new ResponseEntity<>(errorJson, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
