package com.example.trabalhocotacao;

import com.seuprojeto.cotacoes.model.Cotacao;
import com.seuprojeto.cotacoes.dto.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CotacaoService {

    private Map<Long, Cotacao> banco = new HashMap<>();
    private Long contadorId = 1L;

    public CotacaoResponseDTO criar(CotacaoRequestDTO dto) {
        Cotacao c = new Cotacao();

        c.setId(contadorId++);
        c.setClienteId(dto.getClienteId());
        c.setDestinoId(dto.getDestinoId());
        c.setDataViagem(dto.getDataViagem());
        c.setDataRetorno(dto.getDataRetorno());
        c.setNumeroPessoas(dto.getNumeroPessoas());
        c.setStatus("PENDENTE");

        banco.put(c.getId(), c);

        return toResponseDTO(c);
    }

    public List<CotacaoResponseDTO> listar() {
        List<CotacaoResponseDTO> lista = new ArrayList<>();
        for (Cotacao c : banco.values()) {
            lista.add(toResponseDTO(c));
        }
        return lista;
    }

    public CotacaoResponseDTO buscarPorId(Long id) {
        return toResponseDTO(banco.get(id));
    }

    public CotacaoResponseDTO atualizarStatus(Long id, String status) {
        Cotacao c = banco.get(id);
        if (c != null) {
            c.setStatus(status);
        }
        return toResponseDTO(c);
    }

    public void deletar(Long id) {
        banco.remove(id);
    }

    // Conversor
    private CotacaoResponseDTO toResponseDTO(Cotacao c) {
        if (c == null) return null;

        CotacaoResponseDTO dto = new CotacaoResponseDTO();
        dto.setId(c.getId());
        dto.setClienteId(c.getClienteId());
        dto.setDestinoId(c.getDestinoId());
        dto.setDataViagem(c.getDataViagem());
        dto.setDataRetorno(c.getDataRetorno());
        dto.setNumeroPessoas(c.getNumeroPessoas());
        dto.setStatus(c.getStatus());

        return dto;
    }
}