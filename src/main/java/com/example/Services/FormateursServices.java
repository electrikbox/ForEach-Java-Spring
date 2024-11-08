package com.example.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.Wrappers.FormateursWrapper;
import com.example.model.Formateurs;


@Service
public class FormateursServices {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Formateurs> getAll() {
        String sql = "SELECT * FROM Formateurs;";
        return this.jdbcTemplate.query(sql, new FormateursWrapper());
    }

    public Formateurs getById(int id) {
        String sql = "SELECT * FROM Formateurs WHERE ID = ?;";
        return this.jdbcTemplate.queryForObject(sql, new FormateursWrapper(), id);
    }

    public int insert(Formateurs formateur) {
        String sql = "INSERT INTO Formateurs (Nom, Prenom, Email, Telephone) VALUES (?, ?, ?, ?);";
        return this.jdbcTemplate.update(
            sql,
            formateur.getNom(),
            formateur.getPrenom(),
            formateur.getEmail(),
            formateur.getTelephone()
        );
    }

    public int update(Formateurs formateur){
        String sql = "UPDATE Formateurs SET Nom = ?, Prenom = ?, Email = ?, Telephone = ? WHERE ID = ?";
        return this.jdbcTemplate.update(
            sql,
            formateur.getNom(),
            formateur.getPrenom(),
            formateur.getEmail(),
            formateur.getTelephone(),
            formateur.getId()
        );
    }

    public int delete(int id){
        String sql = "DELETE FROM Formateurs WHERE Id=?";
        return this.jdbcTemplate.update(sql, id);
    }
}
