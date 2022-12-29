package com.professorangoti.condominio.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.professorangoti.condominio.model.Apartamento;
import com.professorangoti.condominio.model.Proprietario;

@Repository
public class ApartamentoRepository {

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    ProprietarioRepository repo;

    public void save(Apartamento apartamento) {
        String sql = "insert into apartamento(qtde_quartos, nro_porta, tipo, proprietario_id_proprietario) values (?,?,?,?);";
        jdbc.update(sql, apartamento.getQuantidadeDeQuartos(), apartamento.getNumeroDoApartamento(), apartamento.getTipoDeOcupacao(),
                apartamento.getProprietario().getId());
    }

    // public List<Apartamento> findAll() {
    // return jdbc.query("select * from apartamento;",
    // (registro, contador) -> {
    // Apartamento apto = new Apartamento();
    // apto.setId(registro.getLong("id_apartamento"));
    // apto.setNumeroDoApartamento(registro.getInt("nro_porta"));
    // apto.setQuantidadeDeQuartos(registro.getInt("qtde_quartos"));
    // apto.setTipoDeOcupacao(registro.getString("tipo"));
    // apto.setProprietario(repo.findById(registro.getLong("proprietario_id_proprietario")));
    // // Proprietario proprietario = new Proprietario();
    // // proprietario.setId(registro.getLong("id_proprietario"));
    // // proprietario.setNome(registro.getString("nome"));
    // // proprietario.setTelefone(registro.getString("telefone"));
    // // apto.setProprietario(proprietario);
    // return apto;
    // });
    // }

    public List<Apartamento> findAll() {
        return jdbc.query("select * from apartamento a, proprietario p where a.proprietario_id_proprietario=p.id_proprietario;", (registro, contador) -> {
            Apartamento apto = new Apartamento();
            apto.setId(registro.getLong("id_apartamento"));
            apto.setNumeroDoApartamento(registro.getInt("nro_porta"));
            apto.setQuantidadeDeQuartos(registro.getInt("qtde_quartos"));
            apto.setTipoDeOcupacao(registro.getString("tipo"));
            Proprietario proprietario = new Proprietario();
            proprietario.setId(registro.getLong("id_proprietario"));
            proprietario.setNome(registro.getString("nome"));
            proprietario.setTelefone(registro.getString("telefone"));
            apto.setProprietario(proprietario);
            return apto;
        });
    }

    public Apartamento findById(Long id) {
        return jdbc.queryForObject("select * from apartamento a, proprietario p where a.id_apartamento=? and a.proprietario_id_proprietario=p.id_proprietario;",
                (registro, contador) -> {
                    Apartamento apto = new Apartamento();
                    apto.setId(registro.getLong("id_apartamento"));
                    apto.setNumeroDoApartamento(registro.getInt("nro_porta"));
                    apto.setQuantidadeDeQuartos(registro.getInt("qtde_quartos"));
                    apto.setTipoDeOcupacao(registro.getString("tipo"));
                    Proprietario proprietario = new Proprietario();
                    proprietario.setId(registro.getLong("id_proprietario"));
                    proprietario.setNome(registro.getString("nome"));
                    proprietario.setTelefone(registro.getString("telefone"));
                    apto.setProprietario(proprietario);
                    return apto;
                }, id);
    }
}
