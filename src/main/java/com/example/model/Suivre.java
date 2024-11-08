package com.example.model;

public class Suivre {

    private Etudiants etudiant;
    private Cours cours;
    
    public Suivre() {
    }

    public Suivre(Etudiants etudiant, Cours cours) {
        this.etudiant = etudiant;
        this.cours = cours;
    }

    public Etudiants getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiants etudiant) {
        this.etudiant = etudiant;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }   
}