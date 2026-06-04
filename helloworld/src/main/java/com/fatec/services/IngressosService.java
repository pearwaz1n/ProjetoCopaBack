package com.fatec.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import com.fatec.dtos.IngressosRequest;
import com.fatec.dtos.IngressosResponse;
import com.fatec.entities.Ingressos;
import com.fatec.mappers.IngressosMapper;
import com.fatec.repository.IngressosRepository;

@Service
public class IngressosService {

    @Autowired
    private IngressosRepository repository;

    public List<IngressosResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(IngressosMapper::toDTO)
                .toList();
    }

    public IngressosResponse findById(Long id) {
        return repository.findById(id)
                .map(IngressosMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Ingresso não cadastrado"));
    }

    public void deleteById(Long id) {
        if (repository.existsById(id))
            repository.deleteById(id);
        else
            throw new EntityNotFoundException("Ingresso não cadastrado");
    }

    public IngressosResponse save(IngressosRequest request) {
        Ingressos i = repository.save(IngressosMapper.toEntity(request));
        return IngressosMapper.toDTO(i);
    }

    public void update(IngressosRequest request, Long id) {
        Ingressos i = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ingresso não cadastrado"));

        i.setCpf(request.cpf());
        i.setTitular(request.titular());
        i.setJogo(request.jogo());
        i.setSetor(request.setor());
        i.setAssento(request.assento());
        i.setPreco(request.preco());

        repository.save(i);
    }
}