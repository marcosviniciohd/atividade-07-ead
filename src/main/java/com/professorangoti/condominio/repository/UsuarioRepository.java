package com.professorangoti.condominio.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.professorangoti.condominio.model.Usuario;

@Repository
public class UsuarioRepository {
  @Autowired
  JdbcTemplate jdbc;

  public Usuario buscaPorEmail(String email) {
    String sql = "SELECT usuarios.*, group_concat(distinct papeis.nome separator ', ') papeis FROM usuarios "
        + "inner join usuarios_papeis ON usuarios.usuario_id = usuarios_papeis.usuario_id "
        + "inner JOIN papeis ON usuarios_papeis.papel_id = papeis.papel_id "
        + "where email = ? "
        + "group by usuarios.usuario_id, usuarios.email;";
    return jdbc.queryForObject(sql, this::mapper, email);
  }

  private Usuario mapper(ResultSet registro, int linha) throws SQLException {
    return new Usuario(registro.getLong("usuario_id"), 
                       registro.getString("email"), 
                       registro.getString("senha"), 
                       registro.getString("papeis"));
  }
}
