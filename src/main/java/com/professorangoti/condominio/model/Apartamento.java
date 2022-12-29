package com.professorangoti.condominio.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Apartamento {
    // O atributo id do objeto é necessário para fazer a sincronização com o
    // registro no banco de dados. Ojetos não possuem chave primária
    private Long id;

    // Um apartamento tem um dono. observe que uma chave estrangeira no banco de
    // dados é mapeada diretamente como um objeto na classe Java
    private Proprietario proprietario;

    @Min(2)
    @Max(4)
    private int quantidadeDeQuartos;

    @Min(1)
    private int numeroDoApartamento;

    // O tipo de ocupação pode ser: proprietário, inquilino ou vazio.
    // Para simplificar vou usar valores literais do tipo String.
    // Aqui poderíamos usar enumeração Java.
    private String tipoDeOcupacao;

}
