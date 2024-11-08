package com.example.Wrappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.Services.EtudiantServices;
import com.example.model.Absences;

@Component
public class AbsencesWrapper implements RowMapper<Absences> {

    @Autowired
    private EtudiantServices etudiantServices;

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
