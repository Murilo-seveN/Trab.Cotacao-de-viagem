package org.example.cotacaodeviagem.dto;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CotacaoRequestDTO {
    private Long clienteId;
    private Long destinoId;
    private LocalDateTime dataViagem;
    private LocalDateTime dataRetorno;
    private Integer numeroDePessoas;
}
