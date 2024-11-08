package com.example.Wrappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.Services.EtudiantServices;
import com.example.Services.FormateursServices;
import com.example.Services.UEServices;
import com.example.model.Cours;

public class CoursWrapper implements RowMapper<Cours> {

    private final UEServices ueServices;
    private final FormateursServices formateursServices;
    private final EtudiantServices etudiantServices;

    public CoursWrapper() {
        this.ueServices = new UEServices();
        this.formateursServices = new FormateursServices();
        this.etudiantServices = new EtudiantServices();
    }

    @Override
    public Cours mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Cours(rs.getInt("Id"),
        rs.getDate("Date_Debut"),
        rs.getDate("Date_Fin"),
        this.ueServices.getById(rs.getInt("FK_UE")),
        this.formateursServices.getById(rs.getInt("FK_Formateur")),
        this.etudiantServices.getByCourID(rs.getInt("Id"))
        );
    }
}
