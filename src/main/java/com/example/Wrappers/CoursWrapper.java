package com.example.Wrappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.Services.EtudiantServices;
import com.example.Services.FormateursServices;
import com.example.Services.UEServices;
import com.example.model.Cours;

@Component
public class CoursWrapper implements RowMapper<Cours> {

    @Autowired
    private UEServices ueServices;
    @Autowired
    private FormateursServices formateursServices;
    @Autowired
    private EtudiantServices etudiantServices;

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
