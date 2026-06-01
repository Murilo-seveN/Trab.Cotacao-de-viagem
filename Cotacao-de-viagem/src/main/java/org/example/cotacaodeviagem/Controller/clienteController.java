package org.example.cotacaodeviagem.controller;

import lombok.RequiredArgsConstructor;
import org.example.cotacaodeviagem.dto.ClienteRequestDTO;
import org.example.cotacaodeviagem.dto.ClienteResponseDTO;
import org.example.cotacaodeviagem.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criar(@RequestBody ClienteRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{email}")
    public ResponseEntity<ClienteResponseDTO> buscar(@PathVariable String email) {
        return ResponseEntity.ok(service.buscarPorEmail(email));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizar(
            @PathVariable Long id, @RequestBody ClienteRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }
}
