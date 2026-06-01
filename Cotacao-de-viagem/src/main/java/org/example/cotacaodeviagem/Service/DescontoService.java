package org.example.cotacaodeviagem.service; 
 
import lombok.RequiredArgsConstructor; 
import org.example.cotacaodeviagem.dto.*; 
import org.example.cotacaodeviagem.entity.*; 
import org.example.cotacaodeviagem.repository.*; 
import org.springframework.stereotype.Service; 
import java.time.LocalDateTime; 
import java.util.List; 
import java.util.stream.Collectors; 
 
@Service 
@RequiredArgsConstructor 
public class DescontoService { 
 
    private final DescontoRepository descontoRepository; 
    private final CotacaoRepository cotacaoRepository; 
 
    public DescontoResponseDTO registrar(DescontoRequestDTO dto) { 
        CotacaoEntity cotacao = cotacaoRepository.findById(dto.getCotacaoId()) 
            .orElseThrow(() -> new RuntimeException("Cotacao nao encontrada")); 
 
        DescontoEntity e = new DescontoEntity(); 
        e.setCotacao(cotacao); 
        e.setValorDesconto(dto.getValorDesconto()); 
       e.setDescricao(dto.getDescricao()); 
        e.setDataAplicacao(LocalDateTime.now()); 
 
        return toResponse(descontoRepository.save(e)); 
    } 
 
    public List<DescontoResponseDTO> listar() { 
        return descontoRepository.findAll().stream() 
            .map(this::toResponse).collect(Collectors.toList()); 
    } 
 
    public DescontoResponseDTO buscarPorId(Long id) { 
        return toResponse(descontoRepository.findById(id) 
            .orElseThrow(() -> new RuntimeException("Desconto nao encontrado"))); 
    } 
 
    public List<DescontoResponseDTO> listarPorCotacao(Long cotacaoId) { 
        return descontoRepository.findByCotacaoId(cotacaoId).stream() 
            .map(this::toResponse).collect(Collectors.toList()); 
    } 
 
    public DescontoResponseDTO atualizar(Long id, DescontoUpdateDTO dto) { 
        DescontoEntity e = descontoRepository.findById(id) 
            .orElseThrow(() -> new RuntimeException("Desconto nao encontrado")); 
        e.setValorDesconto(dto.getValorDesconto()); 
        e.setDescricao(dto.getDescricao()); 
        return toResponse(descontoRepository.save(e)); 
    } 
 
    public void remover(Long id) { 
        descontoRepository.deleteById(id); 
    } 
 
    private DescontoResponseDTO toResponse(DescontoEntity e) { 
        DescontoResponseDTO dto = new DescontoResponseDTO(); 
        dto.setId(e.getId()); 
        dto.setCotacaoId(e.getCotacao().getId()); 
        dto.setValorDesconto(e.getValorDesconto()); 
        dto.setDescricao(e.getDescricao()); 
        dto.setDataAplicacao(e.getDataAplicacao()); 
        return dto; 
    } 
}
