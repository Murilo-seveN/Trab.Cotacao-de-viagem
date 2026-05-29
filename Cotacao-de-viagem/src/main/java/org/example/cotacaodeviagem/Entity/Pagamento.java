@Entity
@Table(name = "pagamentos")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cotacao_id")
    private Cotacao cotacao;

    private BigDecimal valorPago;

    private String status;

    private LocalDateTime dataPagamento;
}