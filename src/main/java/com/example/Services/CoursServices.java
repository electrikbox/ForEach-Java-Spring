package com.example.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.Wrappers.CoursWrapper;
import com.example.model.Cours;


@Service
public class CoursServices {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Cours> getAll() {
        String sql = "SELECT * FROM Cours;";
        return this.jdbcTemplate.query(sql, new CoursWrapper());
    }

    public Cours getById(int id) {
        String sql = "SELECT * FROM Cours WHERE ID = ?;";
        return this.jdbcTemplate.queryForObject(sql, new CoursWrapper(), id);
    }

    public int insert(Cours cour) {
        String sql = "INSERT INTO Cours VALUES (?, ?, ?, ?);";
        return this.jdbcTemplate.update(
            sql,
            cour.getDateDebut(),
            cour.getDateFin(),
            cour.getUe().getId(),
            cour.getFormateur().getId()
        );
    }

    public int update(Cours cour){
        String sql = "UPDATE Cours SET Date_Debut = ?, Date_Fin = ?, FK_UE = ?, FK_Formateur = ? WHERE ID = ?";
        return this.jdbcTemplate.update(
            sql,
            cour.getDateDebut(),
            cour.getDateFin(),
            cour.getUe().getId(),
            cour.getFormateur().getId(),
            cour.getId()
        );
    }

    public int delete(int id){
        String sql = "DELETE FROM Cours WHERE Id=?";
        return this.jdbcTemplate.update(sql, id);
    }
}
