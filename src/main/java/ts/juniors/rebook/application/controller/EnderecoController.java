package ts.juniors.rebook.application.controller;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ts.juniors.rebook.domain.dto.EnderecoDto;
import ts.juniors.rebook.domain.entity.Endereco;
import ts.juniors.rebook.application.service.EnderecoServiceImpl;

import java.net.URI;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/endereco")
public class EnderecoController {


    private final EnderecoServiceImpl service;

    @GetMapping
    public Page<EnderecoDto> listarEnderecos(@PageableDefault(size = 10) Pageable paginacao) {
        return service.getTodosEnderecos(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDto> detalharEnderecoPorId(@PathVariable @NotNull Long id) {
        EnderecoDto dto = service.getPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<EnderecoDto> cadastrarEndereco(@RequestBody @Valid EnderecoDto dto, UriComponentsBuilder uriBuilder) {
        EnderecoDto endereco = service.postEndereco(dto);
        URI uri = uriBuilder.path("/endereco/{id}").buildAndExpand(endereco.getId()).toUri();

        return ResponseEntity.created(uri).body(endereco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDto> atualizarEndereco(@PathVariable @NotNull Long id, @RequestBody @Valid EnderecoDto dto) {
        EnderecoDto atualizado = service.putEndereco(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EnderecoDto> removerDeletar(@PathVariable @NotNull Long id) {
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
