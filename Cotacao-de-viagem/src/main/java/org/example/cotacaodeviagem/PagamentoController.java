@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<ListarPagamentoDTO> criar(
            @RequestBody CriarPagamentoDTO dto) {

        return ResponseEntity.ok(pagamentoService.criar(dto));
    }
}