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

// Serviço responsável pelas regras de negócio e comunicação com o banco de dados (Repository)
@Service
public class IngressosService {

    @Autowired
    private IngressosRepository repository;

    // Busca todos os ingressos no banco de dados e os converte para DTOs (para
    // enviar ao cliente)
    public List<IngressosResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(IngressosMapper::toDTO)
                .toList();
    }

    // Tenta encontrar um ingresso pelo ID, se não encontrar lança uma exceção
    // (erro)
    public IngressosResponse findById(Long id) {
        return repository.findById(id)
                .map(IngressosMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Ingresso não cadastrado"));
    }

    // Verifica se o ingresso existe e o deleta; caso contrário, avisa que não
    // existe
    public void deleteById(Long id) {
        if (repository.existsById(id))
            repository.deleteById(id);
        else
            throw new EntityNotFoundException("Ingresso não cadastrado");
    }

    // Converte os dados recebidos (Request) para a entidade do banco e salva.
    // Depois retorna como DTO.
    public IngressosResponse save(IngressosRequest request) {
        Ingressos i = repository.save(IngressosMapper.toEntity(request));
        return IngressosMapper.toDTO(i);
    }

    // Busca o ingresso, atualiza todos os seus campos com os novos dados recebidos
    // e salva novamente
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