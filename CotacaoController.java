package com.example.trabalhocotacao;

import com.seuprojeto.cotacoes.dto.*;
import com.seuprojeto.cotacoes.service.CotacaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cotacoes")
public class CotacaoController {

    private final CotacaoService service;

    public CotacaoController(CotacaoService service) {
        this.service = service;
    }

    @PostMapping
    public CotacaoResponseDTO criar(@RequestBody CotacaoRequestDTO dto) {
        return service.criar(dto);
    }

    @GetMapping
    public List<CotacaoResponseDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public CotacaoResponseDTO buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PatchMapping("/{id}/status")
    public CotacaoResponseDTO atualizarStatus(@PathVariable Long id, @RequestBody StatusUpdateDTO dto) {
        return service.atualizarStatus(id, dto.getStatus());
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id) {
        service.deletar(id);
        return "Cotação removida com sucesso!";
    }
}