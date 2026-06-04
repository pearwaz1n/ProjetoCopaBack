package com.fatec.dtos;

public record IngressosResponse(
        Long id,
        String cpf,
        String titular,
        String jogo,
        String setor,
        String assento,
        Double preco) {
}