package org.example.cotacaodeviagem.dto;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class PagamentoRequestDTO {
    private Long cotacaoId;
    private BigDecimal valorPago;
}
