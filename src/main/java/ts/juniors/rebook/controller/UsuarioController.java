package ts.juniors.rebook.controller;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ts.juniors.rebook.dto.UsuarioDto;
import ts.juniors.rebook.dto.UsuarioInsertDto;
import ts.juniors.rebook.dto.UsuarioLoginDto;
import ts.juniors.rebook.infra.security.JwtTokenDto;
import ts.juniors.rebook.infra.security.TokenService;
import ts.juniors.rebook.model.Usuario;
import ts.juniors.rebook.service.UsuarioService;

import java.net.URI;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @GetMapping
    public Page<UsuarioDto> listarUsuarios(@PageableDefault(size = 10) Pageable paginacao) {
        return service.GetTodosUsuarios(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> detalharUsuarioPorId(@PathVariable @NotNull Long id) {
        UsuarioDto dto = service.GetPorId(id);
        return ResponseEntity.ok(dto);
    }
    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody @Valid UsuarioLoginDto dto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new JwtTokenDto(tokenJWT));
    }
    @PostMapping
    public ResponseEntity<UsuarioInsertDto> cadastrarUsuario(@RequestBody @Valid UsuarioInsertDto dto, UriComponentsBuilder uriBuilder) {
        UsuarioInsertDto usuario = service.PostUsuario(dto);
        URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();



        return ResponseEntity.created(uri).body(usuario);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> atualizarUsuario(
            @PathVariable @NotNull Long id,
            @RequestBody @Valid UsuarioDto dto,
            @RequestHeader("Authorization") String authHeader) {

        String tokenJWT = authHeader.replace("Bearer ", "");

        UsuarioDto atualizado = service.PutUsuario(id, dto, tokenJWT);
        return ResponseEntity.ok(atualizado);
    }

        @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerDeletar(@PathVariable @NotNull Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        service.DeleteUsuario(id, email); 
        return ResponseEntity.noContent().build();
    }
}
