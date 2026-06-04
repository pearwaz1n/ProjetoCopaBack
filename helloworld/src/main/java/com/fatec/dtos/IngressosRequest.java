package com.fatec.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record IngressosRequest(

        @NotBlank(message = "O CPF não pode ser em branco") @CPF(message = "O CPF informado é inválido") String cpf,

        @NotBlank(message = "O nome do titular não pode ser em branco") @Size(min = 3, max = 100, message = "O nome do titular deve ter entre 3 e 100 caracteres") String titular,

        @NotBlank(message = "O jogo não pode ser em branco") String jogo,

        @NotBlank(message = "O setor não pode ser em branco") String setor,

        @NotBlank(message = "O assento não pode ser em branco") String assento,

        @Positive(message = "O preço deve ser maior que zero") Double preco

) {
}