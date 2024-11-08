package com.example.Wrappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.Services.CoursServices;
import com.example.Services.EtudiantServices;
import com.example.model.Suivre;

public class SuivreWrapper implements RowMapper<Suivre> {

    EtudiantServices etudiantServices;
    CoursServices coursServices;

    public SuivreWrapper() {
        this.etudiantServices = new EtudiantServices();
        this.coursServices = new CoursServices();
    }

    @Override
    public Suivre mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Suivre(
            etudiantServices.getById(rs.getInt("FK_Etudiant")),
            coursServices.getById(rs.getInt("FK_Cours"))
        );
    }
}
