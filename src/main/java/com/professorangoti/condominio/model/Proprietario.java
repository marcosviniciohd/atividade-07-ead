package com.professorangoti.condominio.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proprietario {
    // O atributo id do objeto é necessário para fazer a sincronização com o
    // registro no banco de dados. Ojetos não possuem chave primária
    private Long id;

    @NotEmpty
    private String nome;
    
    @NotEmpty
    @Size(min = 9, max = 11, message = "informe o telefone com apenas dígitos, com o tamanho entre 9 e 11 dígitos")
    private String telefone;
}
