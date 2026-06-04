package com.fatec.mappers;

import com.fatec.dtos.IngressosRequest;
import com.fatec.dtos.IngressosResponse;
import com.fatec.entities.Ingressos;

public class IngressosMapper {

    public static Ingressos toEntity(IngressosRequest request) {
        Ingressos i = new Ingressos();
        i.setCpf(request.cpf());
        i.setTitular(request.titular());
        i.setJogo(request.jogo());
        i.setSetor(request.setor());
        i.setAssento(request.assento());
        i.setPreco(request.preco());
        return i;
    }

    public static IngressosResponse toDTO(Ingressos ingresso) {
        return new IngressosResponse(
                ingresso.getId(),
                ingresso.getCpf(),
                ingresso.getTitular(),
                ingresso.getJogo(),
                ingresso.getSetor(),
                ingresso.getAssento(),
                ingresso.getPreco());
    }
}