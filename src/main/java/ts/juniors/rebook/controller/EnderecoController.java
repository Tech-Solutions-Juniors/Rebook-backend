package ts.juniors.rebook.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ts.juniors.rebook.dto.EnderecoDTO;
import ts.juniors.rebook.mapper.EnderecoMapper;
import ts.juniors.rebook.model.Endereco;
import ts.juniors.rebook.service.EnderecoService;
import java.util.List;
import java.util.stream.Collectors;
import static ts.juniors.rebook.mapper.EnderecoMapper.convertToDTO;
import static ts.juniors.rebook.mapper.EnderecoMapper.convertToEntity;

@RestController
@RequestMapping("/v1/enderecos")
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/{id}")
    public EnderecoDTO getEndereco(@PathVariable Long id) {
        Endereco endereco = enderecoService.findById(id);
        return convertToDTO(endereco);
    }

    @PostMapping
    public EnderecoDTO createEndereco(@RequestBody EnderecoDTO enderecoDTO) {
        Endereco endereco = convertToEntity(enderecoDTO);
        Endereco savedEndereco = enderecoService.save(endereco);
        return convertToDTO(savedEndereco);
    }

    @PutMapping("/{id}")
    public EnderecoDTO updateEndereco(@PathVariable Long id, @RequestBody EnderecoDTO enderecoDTO) {
        Endereco endereco = convertToEntity(enderecoDTO);
        Endereco updateEndereco = enderecoService.update(id, endereco);
        return convertToDTO(updateEndereco);

    }

    @DeleteMapping("/{id}")
    public void deleteEndereco(@PathVariable Long id) {
        enderecoService.deleteById(id);
    }


    @GetMapping("/v1/enderecos")
    public List<EnderecoDTO> getAllEnderecos() {
        return enderecoService.findAll().stream()
                .map(EnderecoMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/cidade/{cidade}")
    public List<EnderecoDTO> getEnderecosByCidade(@PathVariable String cidade) {
        return enderecoService.findByCidade(cidade).stream()
                .map(EnderecoMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/estado/{estado}")
    public List<EnderecoDTO> getEnderecosByEstado(@PathVariable String estado) {
        return enderecoService.findByEstado(estado).stream()
                .map(EnderecoMapper::convertToDTO)
                .collect(Collectors.toList());
    }


}
