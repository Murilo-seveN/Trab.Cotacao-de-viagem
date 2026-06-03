package org.example.cotacaodeviagem.service;

import lombok.RequiredArgsConstructor;
import org.example.cotacaodeviagem.dto.*;
import org.example.cotacaodeviagem.entity.*;
import org.example.cotacaodeviagem.repository.*;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CotacaoService {

    private final CotacaoRepository cotacaoRepository;
    private final ClienteRepository clienteRepository;
    private final DestinoRepository destinoRepository;
    private final DescontoRepository descontoRepository;

    public CotacaoResponseDTO criar(CotacaoRequestDTO dto) {

        if (dto.getNumeroDePessoas() <= 0) {
            throw new RuntimeException("O numero de pessoas deve ser maior que zero");
        }
        if (dto.getDataViagem() != null && dto.getDataRetorno() != null) {
            if (dto.getDataRetorno().isBefore(dto.getDataViagem())) {
                throw new RuntimeException("A data de retorno nao pode ser anterior a data de viagem");
            }
        }

        ClienteEntity cliente = clienteRepository.findById(dto.getClienteId())
            .orElseThrow(() -> new RuntimeException("Cliente nao encontrado"));
        DestinoEntity destino = destinoRepository.findById(dto.getDestinoId())
            .orElseThrow(() -> new RuntimeException("Destino nao encontrado"));

        BigDecimal valorBase = destino.getPrecoPorPessoa()
            .multiply(BigDecimal.valueOf(dto.getNumeroDePessoas()));


        long diasViagem = 0;
        if (dto.getDataViagem() != null && dto.getDataRetorno() != null) {
            diasViagem = ChronoUnit.DAYS.between(dto.getDataViagem(), dto.getDataRetorno());
        }


        BigDecimal desconto = BigDecimal.ZERO;
        String descricaoDesconto = null;

        if (diasViagem >= 15) {
            desconto = valorBase.multiply(new BigDecimal("0.20"));
            descricaoDesconto = "Desconto de 20% para viagens de 15 dias ou mais";
        } else if (diasViagem >= 8) {
            desconto = valorBase.multiply(new BigDecimal("0.10"));
            descricaoDesconto = "Desconto de 10% para viagens de 8 dias ou mais";
        }

        BigDecimal valorTotal = valorBase.subtract(desconto);

        CotacaoEntity e = new CotacaoEntity();
        e.setCliente(cliente);
        e.setDestino(destino);
        e.setDataCotacao(LocalDateTime.now());
        e.setDataViagem(dto.getDataViagem());
        e.setDataRetorno(dto.getDataRetorno());
        e.setNumeroDePessoas(dto.getNumeroDePessoas());
        e.setValorTotal(valorTotal);
        e.setStatus("PENDENTE");

        CotacaoEntity salva = cotacaoRepository.save(e);

        if (desconto.compareTo(BigDecimal.ZERO) > 0) {
            DescontoEntity d = new DescontoEntity();
            d.setCotacao(salva);
            d.setValorDesconto(desconto);
            d.setDescricao(descricaoDesconto);
            d.setDataAplicacao(LocalDateTime.now());
            descontoRepository.save(d);
        }

        return toResponse(salva);
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
