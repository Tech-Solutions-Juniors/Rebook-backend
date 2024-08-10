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
import ts.juniors.rebook.dto.LivroDto;
import ts.juniors.rebook.service.LivroService;

import java.net.URI;

@RestController
@RequestMapping("/Livro")
public class LivroController {

    @Autowired
    private LivroService service;

    @GetMapping
    public Page<LivroDto> listarLivros(@PageableDefault(size = 10) Pageable paginacao) {
        return service.GetTodosLivros(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDto> detalharLivroPorId(@PathVariable @NotNull Long id) {
        LivroDto dto = service.GetPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<LivroDto> cadastrarLivro(@RequestBody @Valid LivroDto dto, UriComponentsBuilder uriBuilder) {
        LivroDto livro = service.PostLivro(dto);
        URI uri = uriBuilder.path("/Livro/{id}").buildAndExpand(livro.getId()).toUri();

        return ResponseEntity.created(uri).body(livro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDto> atualizarLivro(@PathVariable @NotNull Long id, @RequestBody @Valid LivroDto dto) {
        LivroDto atualizado = service.PutLivro(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LivroDto> removerDeletar(@PathVariable @NotNull Long id) {
        service.DeleteLivro(id);
        return ResponseEntity.noContent().build();
    }
}
