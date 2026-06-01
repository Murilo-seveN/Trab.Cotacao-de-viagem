package org.example.cotacaodeviagem.dto;
import lombok.Data;

@Data
public class ClienteRequestDTO {
    private String nome;
    private String email;
    private String telefone;
    private String documento;
}
