package com.example.Wrappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.model.Formateurs;

public class FormateursWrapper implements RowMapper<Formateurs>{

    @SuppressWarnings("null")
    @Override
    public Formateurs mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Formateurs(
            rs.getInt("ID"),
            rs.getString("Nom"),
            rs.getString("Prenom"),
            rs.getString("Email"),
            rs.getString("Telephone")
        );
    }
    
}
