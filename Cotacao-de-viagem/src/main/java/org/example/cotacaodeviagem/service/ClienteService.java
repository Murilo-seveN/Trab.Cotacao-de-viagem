package org.example.cotacaodeviagem.service;

import lombok.RequiredArgsConstructor;
import org.example.cotacaodeviagem.dto.ClienteRequestDTO;
import org.example.cotacaodeviagem.dto.ClienteResponseDTO;
import org.example.cotacaodeviagem.entity.ClienteEntity;
import org.example.cotacaodeviagem.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteResponseDTO criar(ClienteRequestDTO dto) {

        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("E-mail ja cadastrado no sistema!");
        }

        if (repository.findByDocumento(dto.getDocumento()).isPresent()) {
            throw new RuntimeException("Documento ja cadastrado no sistema!");
        }

        ClienteEntity e = new ClienteEntity();
        e.setNome(dto.getNome());
        e.setEmail(dto.getEmail());
        e.setTelefone(dto.getTelefone());
        e.setDocumento(dto.getDocumento());
        return toResponse(repository.save(e));
    }

    public List<ClienteResponseDTO> listar() {
        return repository.findAll().stream()
                .map(this::toResponse).collect(Collectors.toList());
    }

    public ClienteResponseDTO buscarPorEmail(String email) {
        ClienteEntity e = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Cliente nao encontrado"));
        return toResponse(e);
    }

    public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO dto) {
        ClienteEntity e = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente nao encontrado"));


        repository.findByEmail(dto.getEmail()).ifPresent(clienteExistente -> {
            if (!clienteExistente.getId().equals(id)) {
                throw new RuntimeException("E-mail ja cadastrado em outro cliente!");
            }
        });

        repository.findByDocumento(dto.getDocumento()).ifPresent(clienteExistente -> {
            if (!clienteExistente.getId().equals(id)) {
                throw new RuntimeException("Documento ja cadastrado em outro cliente!");
            }
        });

        e.setNome(dto.getNome());
        e.setEmail(dto.getEmail());
        e.setTelefone(dto.getTelefone());
        e.setDocumento(dto.getDocumento());
        return toResponse(repository.save(e));
    }

    private ClienteResponseDTO toResponse(ClienteEntity e) {
        ClienteResponseDTO dto = new ClienteResponseDTO();
        dto.setId(e.getId());
        dto.setNome(e.getNome());
        dto.setEmail(e.getEmail());
        dto.setTelefone(e.getTelefone());
        dto.setDocumento(e.getDocumento());
        return dto;
    }
}
