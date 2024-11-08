package com.example.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.Wrappers.EtudiantsWrapper;
import com.example.model.Etudiants;

@Service
public class EtudiantServices {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Etudiants> getAll() {
        String sql = "SELECT * FROM Etudiants;";
        return jdbcTemplate.query(sql, new EtudiantsWrapper());
    }

    public Etudiants getById(int id) {
        String sql = "SELECT * FROM Etudiants WHERE ID = ?;";
        return jdbcTemplate.queryForObject(sql, new EtudiantsWrapper(), id);
    }

    public List<Etudiants> getByCourID(int id){
        String sql = "SELECT ET.* FROM Etudiants ET INNER JOIN Suivre SU ON SU.FK_Etudiant = ET.Id WHERE FK_Cour=?;";
        return jdbcTemplate.query(sql, new EtudiantsWrapper(),id);
   }

    public int insert(Etudiants etudiant) {
        String sql = "INSERT INTO Etudiants (Nom, Prenom, Email, Telephone) VALUES (?, ?, ?, ?);";
        return jdbcTemplate.update(
            sql,
            etudiant.getNom(),
            etudiant.getPrenom(),
            etudiant.getEmail(),
            etudiant.getTelephone()
        );
    }

    public int update(Etudiants etudiant){
        String sql = "UPDATE Etudiants SET Nom = ?, Prenom = ?, Email = ?, Telephone = ? WHERE ID = ?";
        return jdbcTemplate.update(
            sql,
            etudiant.getNom(),
            etudiant.getPrenom(),
            etudiant.getEmail(),
            etudiant.getTelephone(),
            etudiant.getId()
        );
    }

    public int delete(int id){
        String sql = "DELETE FROM Etudiants WHERE Id=?";
        return jdbcTemplate.update(sql, id);
    }
}
