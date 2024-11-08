package com.example.DTO;

import java.util.Date;

import com.example.model.Formateurs;
import com.example.model.UE;

public class CoursResponseDTO {

    private int id;
    private Date dateDebut;
    private Date dateFin;
    private UE ue;
    private Formateurs formateur;

    public CoursResponseDTO(int id, Date dateDebut, Date dateFin, UE ue, Formateurs formateur) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.ue = ue;
        this.formateur = formateur;
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
}
