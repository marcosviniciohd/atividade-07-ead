package com.professorangoti.condominio.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.professorangoti.condominio.model.Proprietario;

@Repository
public class ProprietarioRepository {

    @Autowired
    JdbcTemplate jdbc;

    public void save(Proprietario proprietario) {
        String sql = "insert into proprietario(nome, telefone) values (?,?);";
        jdbc.update(sql, proprietario.getNome(), proprietario.getTelefone());
    }

    public List<Proprietario> findAll() {
        return jdbc.query("select * from proprietario;", this::mapper);
    }

    public Proprietario findById(Long id) {
        return jdbc.queryForObject("select * from proprietario where id_proprietario=?", this::mapper, id);
    }

    private Proprietario mapper(ResultSet registro, int linha) throws SQLException {
        return new Proprietario(registro.getLong("id_proprietario"), registro.getString("nome"),
                registro.getString("telefone"));
    }

    public void delete(Long id) {
        jdbc.update("delete from proprietario where id_proprietario=?", id);
    }
}
