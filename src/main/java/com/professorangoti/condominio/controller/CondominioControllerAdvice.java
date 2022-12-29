package com.professorangoti.condominio.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class CondominioControllerAdvice {

  @ExceptionHandler(java.sql.SQLIntegrityConstraintViolationException.class)
  public String violaçãoDeIntegridade(java.sql.SQLIntegrityConstraintViolationException ex, final RedirectAttributes redirectAttributes) {
    String errorMessage = ex.getMessage();
    if (errorMessage.contains("Cannot delete or update a parent row")) {
      redirectAttributes.addFlashAttribute("mensagem", "O proprietário tem apartamento cadastrado. Não é possível excluí-lo do sistema");
      return "redirect:/rel_prop";
    }
    return "error";
  }

  @ExceptionHandler(java.nio.file.AccessDeniedException.class)
  public String erroUploadArquivoVazio(java.nio.file.AccessDeniedException ex, final Model model) {
    model.addAttribute("message", "Não foi possível realizar o upload do arquivo. Tente novamente.");
    return "error";
  }

  @ExceptionHandler(org.springframework.web.multipart.MaxUploadSizeExceededException.class)
  public String violaçãoDeIntegridade(org.springframework.web.multipart.MaxUploadSizeExceededException ex, final Model model) {
    model.addAttribute("message", "Tamanho do arquivo excede o permitido (128KB). Tente novamente.");
    return "error";
  }

}
