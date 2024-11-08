package com.example.DTO;

/**
 * FormateurResponseDTO is a Data Transfer Object (DTO) that represents the response data for a formateur (trainer).
 * It contains the formateur's name, surname, email, and telephone number.
 * 
 * This class provides a constructor for initializing these fields, as well as getter and setter methods
 * for accessing and modifying them.
 * 
 * Fields:
 *   @nom - The formateur's last name.
 *   @prenom - The formateur's first name.
 *   @email - The formateur's email address.
 *   @telephone - The formateur's telephone number.
 * 
 * Methods:
 *   getNom() - Returns the formateur's last name.
 *   setNom(String nom) - Sets the formateur's last name.
 *   getPrenom() - Returns the formateur's first name.
 *   setPrenom(String prenom) - Sets the formateur's first name.
 *   getEmail() - Returns the formateur's email address.
 *   setEmail(String email) - Sets the formateur's email address.
 *   getTelephone() - Returns the formateur's telephone number.
 *   setTelephone(String telephone) - Sets the formateur's telephone number.
 */
public class FormateurResponseDTO {

    private String nom;
    private String prenom;
    private String email;
    private String telephone;

    // Constructeur
    public FormateurResponseDTO(String nom, String prenom, String email, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
    }

    // Getters et Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
