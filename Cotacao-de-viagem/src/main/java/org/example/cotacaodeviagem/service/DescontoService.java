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
public class DescontoService { 
 
    private final DescontoRepository descontoRepository; 
    private final CotacaoRepository cotacaoRepository; 
 
    public DescontoResponseDTO registrar(DescontoRequestDTO dto) { 
        if (dto.getValorDesconto() == null || dto.getValorDesconto().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("O valor do desconto deve ser maior que zero");
        }

        CotacaoEntity cotacao = cotacaoRepository.findById(dto.getCotacaoId()) 
            .orElseThrow(() -> new RuntimeException("Cotacao nao encontrada")); 

        // Impede que o desconto seja maior que o próprio valor atual da cotação
        if (dto.getValorDesconto().compareTo(cotacao.getValorTotal()) > 0) {
            throw new RuntimeException("O desconto nao pode ser maior que o valor total da cotacao");
        }
 
        DescontoEntity e = new DescontoEntity(); 
        e.setCotacao(cotacao); 
        e.setValorDesconto(dto.getValorDesconto()); 
        e.setDescricao(dto.getDescricao()); 
        e.setDataAplicacao(LocalDateTime.now()); 
 
        DescontoEntity salvo = descontoRepository.save(e);

        // Aplica o desconto de verdade na cotação e atualiza o saldo dela
        cotacao.setValorTotal(cotacao.getValorTotal().subtract(dto.getValorDesconto()));
        cotacaoRepository.save(cotacao);

        return toResponse(salvo); 
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
        if (dto.getValorDesconto() == null || dto.getValorDesconto().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("O valor do desconto deve ser maior que zero");
        }

        DescontoEntity e = descontoRepository.findById(id) 
            .orElseThrow(() -> new RuntimeException("Desconto nao encontrado")); 
        
        CotacaoEntity cotacao = e.getCotacao();

        // Devolve o valor do desconto antigo temporariamente para recalcular
        BigDecimal valorSemDescontoAntigo = cotacao.getValorTotal().add(e.getValorDesconto());

        if (dto.getValorDesconto().compareTo(valorSemDescontoAntigo) > 0) {
            throw new RuntimeException("O novo desconto nao pode ser maior que o valor base da cotacao");
        }

        e.setValorDesconto(dto.getValorDesconto()); 
        e.setDescricao(dto.getDescricao()); 
        DescontoEntity salvo = descontoRepository.save(e);

        // Atualiza a cotação com o valor corrigido do novo desconto
        cotacao.setValorTotal(valorSemDescontoAntigo.subtract(dto.getValorDesconto()));
        cotacaoRepository.save(cotacao);

        return toResponse(salvo); 
    } 
 
    public void remover(Long id) { 
        DescontoEntity e = descontoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Desconto nao encontrado"));

        // Se deletar o desconto, o valor dele deve voltar para o total da cotação
        CotacaoEntity cotacao = e.getCotacao();
        cotacao.setValorTotal(cotacao.getValorTotal().add(e.getValorDesconto()));
        cotacaoRepository.save(cotacao);

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
