package org.example.cotacaodeviagem.controller;

import lombok.RequiredArgsConstructor;
import org.example.cotacaodeviagem.dto.DestinoRequestDTO;
import org.example.cotacaodeviagem.dto.DestinoResponseDTO;
import org.example.cotacaodeviagem.service.DestinoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/destinos")
@RequiredArgsConstructor
public class DestinoController {

    private final DestinoService service;

    @PostMapping
    public ResponseEntity<DestinoResponseDTO> criar(@RequestBody DestinoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<DestinoResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestinoResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DestinoResponseDTO> atualizar(
            @PathVariable Long id, @RequestBody DestinoRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
