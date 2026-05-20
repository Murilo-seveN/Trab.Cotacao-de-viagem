@RestController
@RequestMapping("/destinos")
public class DestinoController {

    @Autowired
    private DestinoService destinoService;

    @PostMapping
    public ResponseEntity<ListarDestinoDTO> criar(
            @RequestBody CriarDestinoDTO dto) {

        return ResponseEntity.ok(destinoService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<ListarDestinoDTO>> listar() {

        return ResponseEntity.ok(destinoService.listar());
    }
}