package org.example.cotacaodeviagem.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class DescontoRequestDTO {
    private Long cotacaoId;
    private BigDecimal valorDesconto;
    private String descricao;
}
