package com.example.Wrappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.Services.CoursServices;
import com.example.Services.EtudiantServices;
import com.example.model.Suivre;

@Component
public class SuivreWrapper implements RowMapper<Suivre> {

    @Autowired
    EtudiantServices etudiantServices;
    @Autowired
    CoursServices coursServices;

    @SuppressWarnings("null")
    @Override
    public Suivre mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Suivre(
            etudiantServices.getById(rs.getInt("FK_Etudiant")),
            coursServices.getById(rs.getInt("FK_Cours"))
        );
    }
}
