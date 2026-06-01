package org.example.cotacaodeviagem.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "destinos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DestinoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String descricao;

    private String localizacao;

    @Column(nullable = false)
    private BigDecimal precoPorPessoa;
}
