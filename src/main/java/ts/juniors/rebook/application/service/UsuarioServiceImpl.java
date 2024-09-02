package ts.juniors.rebook.application.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ts.juniors.rebook.domain.dto.UsuarioDto;
import ts.juniors.rebook.domain.dto.UsuarioInsertDto;
import ts.juniors.rebook.domain.entity.Login;
import ts.juniors.rebook.domain.repository.LoginRepository;
import ts.juniors.rebook.domain.service.UsuarioService;
import ts.juniors.rebook.infra.security.TokenService;
import ts.juniors.rebook.domain.entity.Livro;
import ts.juniors.rebook.domain.entity.Usuario;
import ts.juniors.rebook.domain.repository.UsuarioRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final LoginRepository loginRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final TokenService tokenService;
    private static Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Override
    public ResponseEntity<Page<UsuarioDto>> getTodosUsuarios(Pageable paginacao) {
        Page<UsuarioDto> usuarios = usuarioRepository.findAll(paginacao)
                .map(usuario -> modelMapper.map(usuario, UsuarioDto.class));
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UsuarioDto> getPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        UsuarioDto usuarioDto = modelMapper.map(usuario, UsuarioDto.class);
        return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UsuarioInsertDto> postUsuario(UsuarioInsertDto dto) {
        Usuario usuario = modelMapper.map(dto, Usuario.class);

        Login login = new Login();
        login.setEmail(dto.getEmail());
        login.setSenha(passwordEncoder.encode(dto.getSenha()));

        usuario.setLogin(login);
        login.setUsuario(usuario);

        usuario = usuarioRepository.save(usuario);

        UsuarioInsertDto usuarioDto = modelMapper.map(usuario, UsuarioInsertDto.class);
        return new ResponseEntity<>(usuarioDto, HttpStatus.CREATED);
    }

        @Override
    public ResponseEntity<UsuarioDto> putUsuario(Long id, UsuarioDto dto, String tokenJWT) {
        Long userIdFromToken = tokenService.getUserIdFromToken(tokenJWT);

        if (!userIdFromToken.equals(id)) {
            throw new SecurityException("Você não tem permissão para editar este usuário.");
        }

        Login loginExistente = loginRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Login não encontrado"));

        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));


        loginExistente.setEmail(dto.getEmail());

        usuarioExistente.setNome(dto.getNome());

        if (dto.getLivros() != null) {
            List<Livro> livros = dto.getLivros().stream()
                    .map(livroDto -> modelMapper.map(livroDto, Livro.class))
                    .collect(Collectors.toList());

            usuarioExistente.getLivros().clear();
            usuarioExistente.getLivros().addAll(livros);
        }

        loginRepository.save(loginExistente);
        usuarioRepository.save(usuarioExistente);

        UsuarioDto usuarioAtualizadoDto = modelMapper.map(usuarioExistente, UsuarioDto.class);
        return new ResponseEntity<>(usuarioAtualizadoDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteUsuario(Long id, String token) {
        Long userIdFromToken = tokenService.getUserIdFromToken(token);

        if (!id.equals(userIdFromToken)) {
            throw new SecurityException("Usuário não autorizado a deletar este perfil");
        }


        loginRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Login login = loginRepository.findByEmail(username);
        if (login == null) {
            logger.error("User not found: " + username);
            throw new UsernameNotFoundException("Email not found");
        }
        logger.info("User found: " + username);
        return login;
    }
}
