package org.example.cotacaodeviagem.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cotacoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CotacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "destino_id", nullable = false)
    private DestinoEntity destino;

    private LocalDateTime dataCotacao;
    private LocalDateTime dataViagem;
    private LocalDateTime dataRetorno;
    private Integer numeroDePessoas;
    private BigDecimal valorTotal;

    @Column(nullable = false)
    private String status;
}
