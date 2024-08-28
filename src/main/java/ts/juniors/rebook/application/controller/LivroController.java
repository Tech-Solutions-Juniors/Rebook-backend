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
import ts.juniors.rebook.domain.dto.LivroDto;
import ts.juniors.rebook.application.service.LivroServiceImpl;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/livros")
public class LivroController {


    private final LivroServiceImpl service;

    @GetMapping
    public Page<LivroDto> listarLivros(@PageableDefault(size = 10) Pageable paginacao) {
        return service.getTodosLivros(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDto> detalharLivroPorId(@PathVariable @NotNull Long id) {
        LivroDto dto = service.getPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<LivroDto> cadastrarLivro(@RequestBody @Valid LivroDto dto, UriComponentsBuilder uriBuilder) {
        LivroDto livro = service.postLivro(dto);
        URI uri = uriBuilder.path("/livros/{id}").buildAndExpand(livro.getId()).toUri();
        return ResponseEntity.created(uri).body(livro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDto> atualizarLivro(
            @PathVariable @NotNull Long id,
            @RequestBody @Valid LivroDto dto) {
        LivroDto atualizado = service.putLivro(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerLivro(@PathVariable @NotNull Long id) {
        service.deleteLivro(id);
        return ResponseEntity.noContent().build();
    }
}
