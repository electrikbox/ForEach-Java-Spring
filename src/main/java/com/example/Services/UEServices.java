package com.example.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.Wrappers.UEWrapper;
import com.example.model.UE;


@Service
public class UEServices {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<UE> getAll() {
        String sql = "SELECT * FROM UE;";
        return this.jdbcTemplate.query(sql, new UEWrapper());
    }

    public UE getById(int id) {
        String sql = "SELECT * FROM UE WHERE ID = ?;";
        return this.jdbcTemplate.queryForObject(sql, new UEWrapper(), id);
    }

    public int insert(UE ue){
        String sql = "INSERT INTO UE(Libelle) VALUES (?)";
        return this.jdbcTemplate.update(sql, ue.getLibelle());
    }

    public int update(UE ue){
        String sql = "UPDATE UE SET Libelle=? WHERE Id=?";
        return this.jdbcTemplate.update(sql, ue.getLibelle(), ue.getId());
    }

    public int delete(int id){
        String sql = "DELETE FROM UE WHERE Id=?";
        return this.jdbcTemplate.update(sql, id);
    }
}
