package org.example.cotacaodeviagem.dto;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class DestinoRequestDTO {
    private String nome;
    private String descricao;
    private String localizacao;
    private BigDecimal precoPorPessoa;
}
