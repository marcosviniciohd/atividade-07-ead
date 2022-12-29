package com.professorangoti.condominio.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.professorangoti.condominio.model.Foto;

@Repository
public class ImagemRepository {

  @Autowired
  private JdbcTemplate jdbc;

  public void adicionaImagem(Long id, String url) {
    String sql = "insert into imagem(url, apartamento_id) values (?,?);";
    jdbc.update(sql, url, id);
  }

  public List<Foto> getImagens(Long apartamentoID) {
    return jdbc.query("select * from imagem where apartamento_id = ? ;", (registro, contador) -> new Foto(registro.getString("url"), registro.getLong("id")),
        apartamentoID);
  }

  public void delete(long id) {
    String sql = "delete from imagem where id=?;";
    jdbc.update(sql, id);
  }

  public String findById(long id) {
    return jdbc.queryForObject("select url from imagem where id = ?;", (registro, contador) -> registro.getString("url"), id);
  }
}
