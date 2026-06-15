package com.fatec.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fatec.dtos.FootbalDataDTO.RespostaApi;
import com.fatec.dtos.JogoResponse;

@Service
public class FutebolService {

    // Puxando os dados do application.properties
    @Value("${football.api.url}")
    private String apiUrl;

    @Value("${football.api.key}")
    private String apiKey;

    public List<JogoResponse> buscarJogos() {
        RestTemplate restTemplate = new RestTemplate();

        // Anexa a chave secreta no cabeçalho da requisição
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", apiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            // O Java converte o JSON direto pro Record
            ResponseEntity<RespostaApi> response = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.GET,
                    entity,
                    RespostaApi.class);

            // Mapear para JogoResponse com os campos de placar e status
            if (response.getBody() != null && response.getBody().matches() != null) {
                return response.getBody().matches().stream()
                        .map(partida -> new JogoResponse(
                                partida.utcDate(),
                                partida.group() != null ? partida.group() : "Fase de Grupos",
                                partida.homeTeam().name(),
                                partida.awayTeam().name(),
                                partida.status(), // Status da partida
                                (partida.score() != null && partida.score().fullTime() != null)
                                        ? partida.score().fullTime().home()
                                        : null, // Gols Mandante
                                (partida.score() != null && partida.score().fullTime() != null)
                                        ? partida.score().fullTime().away()
                                        : null // Gols Visitante
                        ))
                        .toList();
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar jogos na API externa: " + e.getMessage());
        }

        // Se der erro devolve uma lista vazia
        return Collections.emptyList();
    }
}