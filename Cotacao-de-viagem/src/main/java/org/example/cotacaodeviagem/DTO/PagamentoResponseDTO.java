package org.example.cotacaodeviagem.dto;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PagamentoResponseDTO {
    private Long id;
    private Long cotacaoId;
    private BigDecimal valorPago;
    private String status;
    private LocalDateTime dataPagamento;
}
