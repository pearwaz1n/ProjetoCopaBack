package com.fatec.dtos;

import java.util.List;

public class FootbalDataDTO {

    public record Equipe(String name) {
    }

    // Estrutura para pegar os placares
    public record PlacarFullTime(Integer home, Integer away) {
    }

    public record Score(PlacarFullTime fullTime) {
    }

    public record Partida(
            String status,
            String utcDate,
            String group,
            Equipe homeTeam,
            Equipe awayTeam,
            Score score) {
    }

    public record RespostaApi(List<Partida> matches) {
    }
}