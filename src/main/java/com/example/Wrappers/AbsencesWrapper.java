package com.example.Wrappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.model.Absences;
import com.example.Services.EtudiantServices;

public class AbsencesWrapper implements RowMapper<Absences> {

    private final EtudiantServices etudiantServices;

    public AbsencesWrapper() {
        this.etudiantServices = new EtudiantServices();
    }

    @Override
    public Absences mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Absences(
            rs.getInt("ID"),
            rs.getDate("Date_Debut"),
            rs.getDate("Date_Fin"),
            rs.getString("Type"),
            etudiantServices.getById(rs.getInt("FK_Etudiant"))
        );
    }
    
}
