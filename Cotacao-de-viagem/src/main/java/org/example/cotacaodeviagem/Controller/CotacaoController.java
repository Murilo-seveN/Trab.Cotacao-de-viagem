package org.example.cotacaodeviagem.controller;

import lombok.RequiredArgsConstructor;
import org.example.cotacaodeviagem.dto.*;
import org.example.cotacaodeviagem.service.CotacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cotacoes")
@RequiredArgsConstructor
public class CotacaoController {

    private final CotacaoService service;

    @PostMapping
    public ResponseEntity<CotacaoResponseDTO> criar(@RequestBody CotacaoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<CotacaoResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CotacaoResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<CotacaoResponseDTO> atualizarStatus(
            @PathVariable Long id, @RequestBody CotacaoStatusDTO dto) {
        return ResponseEntity.ok(service.atualizarStatus(id, dto.getStatus()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
