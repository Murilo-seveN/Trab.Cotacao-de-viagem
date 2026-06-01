package org.example.cotacaodeviagem.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class DescontoResponseDTO {
    private Long id;
    private Long cotacaoId;
    private BigDecimal valorDesconto;
    private String descricao;
    private LocalDateTime dataAplicacao;
}
