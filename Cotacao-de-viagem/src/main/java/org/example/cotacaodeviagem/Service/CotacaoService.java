package org.example.cotacaodeviagem.service;

import lombok.RequiredArgsConstructor;
import org.example.cotacaodeviagem.dto.*;
import org.example.cotacaodeviagem.entity.*;
import org.example.cotacaodeviagem.repository.*;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CotacaoService {

    private final CotacaoRepository cotacaoRepository;
    private final ClienteRepository clienteRepository;
    private final DestinoRepository destinoRepository;

    public CotacaoResponseDTO criar(CotacaoRequestDTO dto) {
        ClienteEntity cliente = clienteRepository.findById(dto.getClienteId())
            .orElseThrow(() -> new RuntimeException("Cliente nao encontrado"));
        DestinoEntity destino = destinoRepository.findById(dto.getDestinoId())
            .orElseThrow(() -> new RuntimeException("Destino nao encontrado"));

        BigDecimal valorTotal = destino.getPrecoPorPessoa()
            .multiply(BigDecimal.valueOf(dto.getNumeroDePessoas()));

        CotacaoEntity e = new CotacaoEntity();
        e.setCliente(cliente);
        e.setDestino(destino);
        e.setDataCotacao(LocalDateTime.now());
        e.setDataViagem(dto.getDataViagem());
        e.setDataRetorno(dto.getDataRetorno());
        e.setNumeroDePessoas(dto.getNumeroDePessoas());
        e.setValorTotal(valorTotal);
        e.setStatus("PENDENTE");

        return toResponse(cotacaoRepository.save(e));
    }

    public List<CotacaoResponseDTO> listar() {
        return cotacaoRepository.findAll().stream()
            .map(this::toResponse).collect(Collectors.toList());
    }

    public CotacaoResponseDTO buscarPorId(Long id) {
        return toResponse(cotacaoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cotacao nao encontrada")));
    }

    public CotacaoResponseDTO atualizarStatus(Long id, String status) {
        CotacaoEntity e = cotacaoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cotacao nao encontrada"));
        e.setStatus(status);
        return toResponse(cotacaoRepository.save(e));
    }

    public void remover(Long id) {
        cotacaoRepository.deleteById(id);
    }

    private CotacaoResponseDTO toResponse(CotacaoEntity e) {
        CotacaoResponseDTO dto = new CotacaoResponseDTO();
        dto.setId(e.getId());
        dto.setClienteId(e.getCliente().getId());
        dto.setDestinoId(e.getDestino().getId());
        dto.setDataCotacao(e.getDataCotacao());
        dto.setDataViagem(e.getDataViagem());
        dto.setDataRetorno(e.getDataRetorno());
        dto.setNumeroDePessoas(e.getNumeroDePessoas());
        dto.setValorTotal(e.getValorTotal());
        dto.setStatus(e.getStatus());
        return dto;
    }
}
