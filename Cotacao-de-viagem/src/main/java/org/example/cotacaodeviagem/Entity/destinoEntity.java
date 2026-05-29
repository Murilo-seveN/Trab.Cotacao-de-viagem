@Entity
@Table(name = "destinos")
public class destinoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private String localizacao;

    private BigDecimal precoPorPessoa;
}