package com.fatec.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.dtos.JogoResponse;
import com.fatec.services.FutebolService;

@RestController
@RequestMapping("/jogos")
@CrossOrigin // Libera o acesso para o Angular na porta 4200
public class FutebolController {

    @Autowired
    private FutebolService service;

    @GetMapping
    public ResponseEntity<List<JogoResponse>> listarJogosDaCopa() {
        return ResponseEntity.ok(service.buscarJogos());
    }
}