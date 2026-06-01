package org.example.cotacaodeviagem.service;

import lombok.RequiredArgsConstructor;
import org.example.cotacaodeviagem.dto.DestinoRequestDTO;
import org.example.cotacaodeviagem.dto.DestinoResponseDTO;
import org.example.cotacaodeviagem.entity.DestinoEntity;
import org.example.cotacaodeviagem.repository.DestinoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DestinoService {

    private final DestinoRepository repository;

    public DestinoResponseDTO criar(DestinoRequestDTO dto) {
        DestinoEntity e = new DestinoEntity();
        e.setNome(dto.getNome());
        e.setDescricao(dto.getDescricao());
        e.setLocalizacao(dto.getLocalizacao());
        e.setPrecoPorPessoa(dto.getPrecoPorPessoa());
        return toResponse(repository.save(e));
    }

    public List<DestinoResponseDTO> listar() {
        return repository.findAll().stream()
                .map(this::toResponse).collect(Collectors.toList());
    }

    public DestinoResponseDTO buscarPorId(Long id) {
        return toResponse(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destino nao encontrado")));
    }

    public DestinoResponseDTO atualizar(Long id, DestinoRequestDTO dto) {
        DestinoEntity e = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destino nao encontrado"));
        e.setNome(dto.getNome());
        e.setDescricao(dto.getDescricao());
        e.setLocalizacao(dto.getLocalizacao());
        e.setPrecoPorPessoa(dto.getPrecoPorPessoa());
        return toResponse(repository.save(e));
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }

    private DestinoResponseDTO toResponse(DestinoEntity e) {
        DestinoResponseDTO dto = new DestinoResponseDTO();
        dto.setId(e.getId());
        dto.setNome(e.getNome());
        dto.setDescricao(e.getDescricao());
        dto.setLocalizacao(e.getLocalizacao());
        dto.setPrecoPorPessoa(e.getPrecoPorPessoa());
        return dto;
    }
}
