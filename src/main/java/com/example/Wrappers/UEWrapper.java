package com.example.Wrappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.model.UE;

public class UEWrapper implements RowMapper<UE>{
    
    @SuppressWarnings("null")
    @Override
    public UE mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UE(
            rs.getInt("ID"),
            rs.getString("Libelle")
        );
    }
}
