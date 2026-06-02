package org.example.cotacaodeviagem.controller;

import lombok.RequiredArgsConstructor;
import org.example.cotacaodeviagem.dto.*;
import org.example.cotacaodeviagem.service.DescontoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/descontos")
@RequiredArgsConstructor
public class DescontoController {

    private final DescontoService service;

    @PostMapping
    public ResponseEntity<DescontoResponseDTO> registrar(
            @RequestBody DescontoRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.registrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<DescontoResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DescontoResponseDTO> buscar(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/cotacao/{cotacaoId}")
    public ResponseEntity<List<DescontoResponseDTO>> listarPorCotacao(
            @PathVariable Long cotacaoId) {

        return ResponseEntity.ok(
                service.listarPorCotacao(cotacaoId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<DescontoResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody DescontoUpdateDTO dto) {

        return ResponseEntity.ok(
                service.atualizar(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(
            @PathVariable Long id) {

        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
