package com.example.Wrappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.model.Etudiants;

public class EtudiantsWrapper implements RowMapper<Etudiants>{

    @Override
    public Etudiants mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Etudiants(
            rs.getInt("ID"),
            rs.getString("Nom"),
            rs.getString("Prenom"),
            rs.getString("Email"),
            rs.getString("Telephone")
        );
    }
}
