package com.example.DTO;

/**
 * EtudiantResponseDTO is a Data Transfer Object (DTO) that represents the response data for a student.
 * It contains the student's name, surname, email, and telephone number.
 * 
 * This class provides a constructor for initializing these fields, as well as getter and setter methods
 * for accessing and modifying them.
 * 
 * Fields:
 *   @nom - The student's last name.
 *   @prenom - The student's first name.
 *   @email - The student's email address.
 *   @telephone - The student's telephone number.

 * 
 * Methods:
 *   getNom() - Returns the student's last name.
 *   setNom(String nom) - Sets the student's last name.
 *   getPrenom() - Returns the student's first name.
 *   setPrenom(String prenom) - Sets the student's first name.
 *   getEmail() - Returns the student's email address.
 *   setEmail(String email) - Sets the student's email address.
 *   getTelephone() - Returns the student's telephone number.
 *   setTelephone(String telephone) - Sets the student's telephone number.

 */
public class EtudiantResponseDTO {

    private String nom;
    private String prenom;
    private String email;
    private String telephone;

    // Constructeur
    public EtudiantResponseDTO(String nom, String prenom, String email, String telephone) {
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
