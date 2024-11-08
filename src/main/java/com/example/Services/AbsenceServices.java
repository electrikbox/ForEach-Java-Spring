package com.example.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.Wrappers.AbsencesWrapper;
import com.example.model.Absences;


@Service
public class AbsenceServices {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AbsencesWrapper absencesWrapper;

    public List<Absences> getAll() {
        String sql = "SELECT * FROM Absences;";
        return this.jdbcTemplate.query(sql, absencesWrapper);
    }

    public Absences getById(int id) {
        String sql = "SELECT * FROM Absences WHERE ID = ?;";
        return this.jdbcTemplate.queryForObject(sql, absencesWrapper, id);
    }

    public int insert(Absences absence){
        String sql = "INSERT INTO Absences VALUES (?, ?, ?, ?)";
        return this.jdbcTemplate.update(
            sql,
            absence.getDateDebut(),
            null,
            absence.getType(),
            absence.getEtudiant().getId()
        );
    }

    public int update(Absences absence){
        String sql = "UPDATE Absences SET Date_Debut = ?, Date_Fin = ?, Type = ?, FK_Etudiant = ? WHERE ID = ?";
        return this.jdbcTemplate.update(
            sql,
            absence.getDateDebut(),
            absence.getDateFin(),
            absence.getType(),
            absence.getEtudiant().getId(),
            absence.getId()
        );
    }

    public int delete(int id){
        String sql = "DELETE FROM Absences WHERE Id=?";
        return this.jdbcTemplate.update(sql, id);
    }
    
}
