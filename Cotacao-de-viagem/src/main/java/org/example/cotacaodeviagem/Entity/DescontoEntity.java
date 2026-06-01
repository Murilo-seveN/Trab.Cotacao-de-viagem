package org.example.cotacaodeviagem.entity; 

 
import jakarta.persistence.*; 
import lombok.Data; 
import lombok.NoArgsConstructor; 
import lombok.AllArgsConstructor; 
import java.math.BigDecimal; 
import java.time.LocalDateTime; 

 

@Entity 
@Table(name = "descontos") 
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class DescontoEntity { 

 

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id; 

 

    @ManyToOne 
    @JoinColumn(name = "cotacao_id", nullable = false) 
    private CotacaoEntity cotacao; 

 

    @Column(nullable = false) 
    private BigDecimal valorDesconto; 

 
    private String descricao; 

    private LocalDateTime dataAplicacao; 

} 

 
