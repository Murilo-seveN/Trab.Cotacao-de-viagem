package org.example.cotacaodeviagem.dto;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CotacaoResponseDTO {
    private Long id;
    private Long clienteId;
    private Long destinoId;
    private LocalDateTime dataCotacao;
    private LocalDateTime dataViagem;
    private LocalDateTime dataRetorno;
    private Integer numeroDePessoas;
    private BigDecimal valorTotal;
    private String status;
}
