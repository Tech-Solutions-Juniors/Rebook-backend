package ts.juniors.rebook.application.controller;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
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
import ts.juniors.rebook.application.service.UsuarioServiceImpl;
import ts.juniors.rebook.domain.dto.UsuarioDto;
import ts.juniors.rebook.domain.dto.UsuarioInsertDto;
import ts.juniors.rebook.domain.dto.UsuarioLoginDto;
import ts.juniors.rebook.domain.entity.Login;
import ts.juniors.rebook.infra.security.JwtTokenDto;
import ts.juniors.rebook.infra.security.TokenService;
import ts.juniors.rebook.domain.entity.Usuario;


import java.net.URI;


@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioServiceImpl service;
    private final AuthenticationManager manager;
    private final TokenService tokenService;

    @GetMapping
    public ResponseEntity<Page<UsuarioDto>> listarUsuarios(@PageableDefault(size = 10) Pageable paginacao) {
        return service.getTodosUsuarios(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> detalharUsuarioPorId(@PathVariable @NotNull Long id) {
        return service.getPorId(id);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDto> efetuarLogin(@RequestBody @Valid UsuarioLoginDto dto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Login) authentication.getPrincipal());

        return ResponseEntity.ok(new JwtTokenDto(tokenJWT));
    }

    @PostMapping
    public ResponseEntity<UsuarioInsertDto> cadastrarUsuario(@RequestBody @Valid UsuarioInsertDto dto, UriComponentsBuilder uriBuilder) {
        ResponseEntity<UsuarioInsertDto> response = service.postUsuario(dto);
        URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(response.getBody().getId()).toUri();
        return ResponseEntity.created(uri).body(response.getBody());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> atualizarUsuario(
            @PathVariable @NotNull Long id,
            @RequestBody @Valid UsuarioDto dto,
            @RequestHeader("Authorization") String authHeader) {

        String tokenJWT = authHeader.replace("Bearer ", "");
        return service.putUsuario(id, dto, tokenJWT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerDeletar(@PathVariable @NotNull Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        service.deleteUsuario(id, email);
        return ResponseEntity.noContent().build();
    }
}

