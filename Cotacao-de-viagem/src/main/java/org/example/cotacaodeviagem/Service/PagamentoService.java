package org.example.cotacaodeviagem.service;

import lombok.RequiredArgsConstructor;
import org.example.cotacaodeviagem.dto.*;
import org.example.cotacaodeviagem.entity.*;
import org.example.cotacaodeviagem.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final CotacaoRepository cotacaoRepository;

    public PagamentoResponseDTO registrar(PagamentoRequestDTO dto) {
        CotacaoEntity cotacao = cotacaoRepository.findById(dto.getCotacaoId())
                .orElseThrow(() -> new RuntimeException("Cotacao nao encontrada"));

        PagamentoEntity e = new PagamentoEntity();
        e.setCotacao(cotacao);
        e.setValorPago(dto.getValorPago());
        e.setStatus("PENDENTE");
        e.setDataPagamento(LocalDateTime.now());

        return toResponse(pagamentoRepository.save(e));
    }

    public PagamentoResponseDTO buscarPorId(Long id) {
        return toResponse(pagamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento nao encontrado")));
    }

    public PagamentoResponseDTO atualizarStatus(Long id, String status) {
        PagamentoEntity e = pagamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento nao encontrado"));
        e.setStatus(status);
        return toResponse(pagamentoRepository.save(e));
    }

    public void remover(Long id) {
        pagamentoRepository.deleteById(id);
    }

    private PagamentoResponseDTO toResponse(PagamentoEntity e) {
        PagamentoResponseDTO dto = new PagamentoResponseDTO();
        dto.setId(e.getId());
        dto.setCotacaoId(e.getCotacao().getId());
        dto.setValorPago(e.getValorPago());
        dto.setStatus(e.getStatus());
        dto.setDataPagamento(e.getDataPagamento());
        return dto;
    }
}
