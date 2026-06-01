package org.example.cotacaodeviagem.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class DescontoUpdateDTO {
    private BigDecimal valorDesconto;
    private String descricao;
}
