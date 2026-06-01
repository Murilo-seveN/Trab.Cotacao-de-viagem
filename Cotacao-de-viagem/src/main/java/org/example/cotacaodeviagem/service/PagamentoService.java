package org.example.cotacaodeviagem.service;

import lombok.RequiredArgsConstructor;
import org.example.cotacaodeviagem.dto.*;
import org.example.cotacaodeviagem.entity.*;
import org.example.cotacaodeviagem.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

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
        // Garante que o valor pago seja exatamente o valor total calculado na cotação
        e.setValorPago(cotacao.getValorTotal());
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
        PagamentoEntity salvo = pagamentoRepository.save(e);

        // Se o pagamento for aprovado, atualiza também o status da cotação automaticamente
        if ("PAGO".equalsIgnoreCase(status) || "APROVADO".equalsIgnoreCase(status)) {
            CotacaoEntity cotacao = e.getCotacao();
            cotacao.setStatus("PAGO");
            cotacaoRepository.save(cotacao);
        }

        return toResponse(salvo);
    }

    public void remover(Long id) {
        pagamentoRepository.deleteById(id);
    }

    public List<PagamentoResponseDTO> listar() {
        return pagamentoRepository.findAll().stream()
                .map(this::toResponse)
                .collect(java.util.stream.Collectors.toList());
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
