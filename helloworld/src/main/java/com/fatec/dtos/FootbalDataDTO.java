package com.fatec.dtos;

import java.util.List;

public class FootbalDataDTO {

    // 1. O time isolado
    public record Equipe(String name) {
    }

    // 2. A partida
    public record Partida(
            String utcDate,
            String group,
            Equipe homeTeam,
            Equipe awayTeam) {
    }

    // 3. A lista completa que vem da API
    public record RespostaApi(List<Partida> matches) {
    }
}