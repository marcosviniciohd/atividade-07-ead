package com.professorangoti.condominio.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.professorangoti.condominio.model.Proprietario;
import com.professorangoti.condominio.repository.ProprietarioRepository;

@Controller
public class ProprietarioController {

    @Autowired
    ProprietarioRepository repository;

    @GetMapping("cad_prop")
    public String formCadastroProprietario(Model model) {
        model.addAttribute("proprietario", new Proprietario());
        return "form_prop";
    }

    @PostMapping("cad_prop")
    public String gravaNovoProprietario(@Valid Proprietario proprietario, BindingResult validacao) {
        if (validacao.hasErrors())
            return "form_prop";
        repository.save(proprietario);
        return "redirect:/rel_prop";
    }

    @GetMapping("rel_prop")
    public String relatorio(Model model) {
        model.addAttribute("proprietarios", repository.findAll());
        return "rel_prop";
    }

    @GetMapping("excluir_prop")
    public String apagaProp(@RequestParam("id") Long chave) {
        repository.delete(chave);
        return "redirect:/rel_prop";
    }

}
