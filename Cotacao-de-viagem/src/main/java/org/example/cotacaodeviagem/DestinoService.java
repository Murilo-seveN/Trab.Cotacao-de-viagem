@Service
public class DestinoService {

    @Autowired
    private DestinoRepository destinoRepository;

    public List<ListarDestinoDTO> listar() {
        return destinoRepository.findAll()
                .stream()
                .map(this::converter)
                .toList();
    }

    public ListarDestinoDTO criar(CriarDestinoDTO dto) {

        Destino destino = new Destino();

        destino.setNome(dto.getNome());
        destino.setDescricao(dto.getDescricao());
        destino.setLocalizacao(dto.getLocalizacao());
        destino.setPrecoPorPessoa(dto.getPrecoPorPessoa());

        return converter(destinoRepository.save(destino));
    }

    private ListarDestinoDTO converter(Destino destino) {

        ListarDestinoDTO dto = new ListarDestinoDTO();

        dto.setId(destino.getId());
        dto.setNome(destino.getNome());
        dto.setDescricao(destino.getDescricao());
        dto.setLocalizacao(destino.getLocalizacao());
        dto.setPrecoPorPessoa(destino.getPrecoPorPessoa());

        return dto;
    }
}