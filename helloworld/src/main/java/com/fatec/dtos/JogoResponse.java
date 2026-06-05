package com.fatec.dtos;

public record JogoResponse(
        String dataHora,
        String grupo,
        String timeMandante,
        String timeVisitante) {
}