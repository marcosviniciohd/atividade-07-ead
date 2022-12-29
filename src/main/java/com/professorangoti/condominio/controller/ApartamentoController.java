package com.professorangoti.condominio.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.professorangoti.condominio.model.Apartamento;
import com.professorangoti.condominio.repository.ApartamentoRepository;
import com.professorangoti.condominio.repository.ImagemRepository;
import com.professorangoti.condominio.repository.ProprietarioRepository;

@Controller
public class ApartamentoController {

    @Autowired
    private ApartamentoRepository repoApartamento;

    @Autowired
    private ImagemRepository repoImagem;

    @Autowired
    private ProprietarioRepository repoPropietario;

    private final Path root = Paths.get("C:\\Spring\\condominio\\src\\main\\resources\\static\\image-upload");

    @GetMapping("cad_apto")
    public String formCadastroApartamento(Model model) {
        model.addAttribute("apartamento", new Apartamento());
        model.addAttribute("proprietarios", repoPropietario.findAll());
        return "form_apto";
    }
    
    @PostMapping("cad_apto")
    public String gravaNovoApartamento(@Valid Apartamento apartamento, BindingResult validacao, Model model) {
        if (validacao.hasErrors()) {
            model.addAttribute("proprietarios", repoPropietario.findAll());
            return "form_apto";
        }
        repoApartamento.save(apartamento);
        return "redirect:/rel_apto";
    }

    @GetMapping("rel_apto")
    public String relatorio(Model model) {
        List<Apartamento> lista2 = repoApartamento.findAll();
        model.addAttribute("apartamentos", lista2);
        return "rel_apto";
    }

    @GetMapping("fotos_apto")
    public String exibeFotosDeApartamento(Model model, @RequestParam("id") String id) {
        model.addAttribute("fotos", repoImagem.getImagens(Long.parseLong(id)));
        model.addAttribute("dados", repoApartamento.findById(Long.parseLong(id)));
        return "fotos_apto";
    }

    @GetMapping("excluir_foto")
    public String excluirFoto(@RequestParam("id") String id) throws IOException {
        String fileName = repoImagem.findById(Long.parseLong(id));
        repoImagem.delete(Long.parseLong(id));
        Files.deleteIfExists(root.resolve(fileName));
        return "redirect:/rel_apto";
    }

    // upload de fotos
    @GetMapping("upload")
    public String exibeFormpload(Model model, @RequestParam("id") String id) {
        model.addAttribute("id", id);
        return "upload_form";
    }

    @PostMapping("upload")
    public String submit(@RequestParam("file") MultipartFile file, @RequestParam("id") String id) throws IOException {
        Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()));
        repoImagem.adicionaImagem(Long.parseLong(id), file.getOriginalFilename());
        return "redirect:/rel_apto";
    }

}
