package com.example.model;
import java.util.Date;
import java.util.List;

public class Cours {

    private int id;
    private Date dateDebut;
    private Date dateFin;
    private UE ue;
    private Formateurs formateur;
    private List<Etudiants> etudiants;

    public Cours(int id, Date dateDebut, Date dateFin, UE ue, Formateurs formateur) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.ue = ue;
        this.formateur = formateur;
    }

    public Cours(int id, Date dateDebut, Date dateFin, UE ue, Formateurs formateur, List<Etudiants> etudiants) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.ue = ue;
        this.formateur = formateur;
        this.etudiants = etudiants;
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

    public Formateurs getFormateur() {
        return formateur;
    }

    public void setFormateur(Formateurs formateur) {
        this.formateur = formateur;
    }

    public UE getUe() {
        return ue;
    }

    public void setUe(UE ue) {
        this.ue = ue;
    }

    public List<Etudiants> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<Etudiants> etudiants) {
        this.etudiants = etudiants;
    }
}
