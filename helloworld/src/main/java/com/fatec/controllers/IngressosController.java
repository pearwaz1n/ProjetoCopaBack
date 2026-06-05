package com.fatec.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatec.dtos.IngressosRequest;
import com.fatec.dtos.IngressosResponse;
import com.fatec.services.IngressosService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ingressos")
@CrossOrigin
public class IngressosController {

    @Autowired
    private IngressosService service;

    @GetMapping
    public ResponseEntity<List<IngressosResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<IngressosResponse> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<IngressosResponse> save(@Valid @RequestBody IngressosRequest request) {
        IngressosResponse response = service.save(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody IngressosRequest request) {
        service.update(request, id);
        return ResponseEntity.noContent().build();
    }
}