package com.example.model;
import java.sql.Date;

public class Absences {

    private int id;
    private Date dateDebut;
    private Date dateFin;
    private String type;
    private Etudiants etudiant;

    public Absences() {
    }

    public Absences(int id, Date dateDebut, Date dateFin, String type, Etudiants etudiant) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.type = type;
        this.etudiant = etudiant;
    }

    public Absences(Date dateDebut, Date dateFin, String type, Etudiants etudiant) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.type = type;
        this.etudiant = etudiant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Etudiants getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiants etudiant) {
        this.etudiant = etudiant;
    }
    
}
