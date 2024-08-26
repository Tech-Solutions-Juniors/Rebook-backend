package ts.juniors.rebook.controller;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ts.juniors.rebook.dto.EnderecoDTO;
import ts.juniors.rebook.model.Endereco;
import ts.juniors.rebook.service.EnderecoService;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @GetMapping
    public Page<EnderecoDTO> listarEnderecos(@PageableDefault(size = 10) Pageable paginacao) {
        return service.getTodosEnderecos(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> detalharEnderecoPorId(@PathVariable @NotNull Long id) {
        EnderecoDTO dto = service.getPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<EnderecoDTO> cadastrarEndereco(@RequestBody @Valid EnderecoDTO dto, UriComponentsBuilder uriBuilder) {
        EnderecoDTO endereco = service.postEndereco(dto);
        URI uri = uriBuilder.path("/endereco/{id}").buildAndExpand(endereco.getId()).toUri();

        return ResponseEntity.created(uri).body(endereco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDTO> atualizarEndereco(@PathVariable @NotNull Long id, @RequestBody @Valid EnderecoDTO dto) {
        EnderecoDTO atualizado = service.putEndereco(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EnderecoDTO> removerDeletar(@PathVariable @NotNull Long id) {
        service.deleteEndereco(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cidade/{cidade}")
    public List<Endereco> getEnderecosByCidade(@PathVariable String cidade) {
        return service.getPorCidade(cidade);
    }

    @GetMapping("/estado/{estado}")
    public List<Endereco> getEnderecosByEstado(@PathVariable String estado) {
        return service.getPorEstado(estado);
    }


}
