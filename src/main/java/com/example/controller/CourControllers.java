package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.DTO.CoursResponseDTO;
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

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Cours cour) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        try {
            courServices.insert(cour);

            CoursResponseDTO etudiantDTO = new CoursResponseDTO(
                cour.getId(),
                cour.getDateDebut(),
                cour.getDateFin(),
                cour.getUe(),
                cour.getFormateur()
            );

            String json = mapper.writeValueAsString(etudiantDTO);
            return new ResponseEntity<>(json, headers, HttpStatus.CREATED);

        } catch (JsonProcessingException e) {
            String errorJson = "{\"error\": \"Error processing etudiant data\"}";
            return new ResponseEntity<>(errorJson, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

