package com.example.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.Wrappers.SuivreWrapper;
import com.example.model.Cours;
import com.example.model.Etudiants;
import com.example.model.Suivre;


@Service
public class SuivreServices {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SuivreWrapper suivreWrapper;

    public List<Suivre> getAll() {
        String sql = "SELECT * FROM Suivre;";
        return this.jdbcTemplate.query(sql, suivreWrapper);
    }

    public List<Suivre> getEtudiantById(int id) {
        String sql = "SELECT * FROM UE WHERE FK_Etudiant = ?;";
        return this.jdbcTemplate.query(sql, suivreWrapper, id);
    }

    public List<Suivre> getCourById(int id) {
        String sql = "SELECT * FROM UE WHERE FK_Cour = ?;";
        return this.jdbcTemplate.query(sql, suivreWrapper, id);
    }

    public int insert(int idCour, int idEtudiant){
        String sql = "INSERT INTO Suivre VALUES (?, ?);";
        return this.jdbcTemplate.update(sql, idCour, idEtudiant);
    }

    public int insert(Cours cour, Etudiants etudiant){
        String sql = "INSERT INTO Suivre VALUES (?, ?);";
        return this.jdbcTemplate.update(sql, cour, etudiant);
    }

    public int delete(int idCour, int idEtudiant){
        String sql = "DELETE FROM Suivre WHERE FK_Cour=?, FK_Etudiant=?";
        return this.jdbcTemplate.update(sql, idCour, idEtudiant);
    }

    public int deleteAllEtudiantID(int id){
        String sql = "DELETE FROM Suivre WHERE FK_Etudiant=?";
        return this.jdbcTemplate.update(sql, id);
    }

    public int deleteAllCourID(int id){
        String sql = "DELETE FROM Suivre WHERE FK_Cour=?";
        return this.jdbcTemplate.update(sql, id);
    }
}
