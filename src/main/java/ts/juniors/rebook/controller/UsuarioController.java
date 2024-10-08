package ts.juniors.rebook.controller;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ts.juniors.rebook.dto.UsuarioDto;
import ts.juniors.rebook.service.UsuarioService;

import java.net.URI;


@RestController
@RequestMapping("/Usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public Page<UsuarioDto> listarUsuarios(@PageableDefault(size = 10) Pageable paginacao) {
        return service.GetTodosUsuarios(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> detalharUsuarioPorId(@PathVariable @NotNull Long id) {
        UsuarioDto dto = service.GetPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> cadastrarUsuario(@RequestBody @Valid UsuarioDto dto, UriComponentsBuilder uriBuilder) {
        UsuarioDto usuario = service.PostUsuario(dto);
        URI uri = uriBuilder.path("/Usuario/{id}").buildAndExpand(usuario.getId()).toUri();



        return ResponseEntity.created(uri).body(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> atualizarUsuario(@PathVariable @NotNull Long id, @RequestBody @Valid UsuarioDto dto) {
        UsuarioDto atualizado = service.PutUsuario(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioDto> removerDeletar(@PathVariable @NotNull Long id) {
        service.DeleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
