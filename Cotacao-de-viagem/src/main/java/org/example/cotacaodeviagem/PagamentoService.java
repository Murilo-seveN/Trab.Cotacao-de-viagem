@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private CotacaoRepository cotacaoRepository;

    public ListarPagamentoDTO criar(CriarPagamentoDTO dto) {

        Cotacao cotacao = cotacaoRepository.findById(dto.getCotacaoId())
                .orElseThrow(() -> new RuntimeException("Cotação não encontrada"));

        Pagamento pagamento = new Pagamento();

        pagamento.setCotacao(cotacao);
        pagamento.setValorPago(dto.getValorPago());
        pagamento.setStatus(dto.getStatus());
        pagamento.setDataPagamento(LocalDateTime.now());

        return converter(pagamentoRepository.save(pagamento));
    }

    private ListarPagamentoDTO converter(Pagamento pagamento) {

        ListarPagamentoDTO dto = new ListarPagamentoDTO();

        dto.setId(pagamento.getId());
        dto.setCotacaoId(pagamento.getCotacao().getId());
        dto.setValorPago(pagamento.getValorPago());
        dto.setStatus(pagamento.getStatus());
        dto.setDataPagamento(pagamento.getDataPagamento());

        return dto;
    }
}