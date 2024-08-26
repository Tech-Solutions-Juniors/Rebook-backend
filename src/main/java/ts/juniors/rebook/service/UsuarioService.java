package ts.juniors.rebook.service;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ts.juniors.rebook.dto.UsuarioDto;
import ts.juniors.rebook.dto.UsuarioInsertDto;
import ts.juniors.rebook.infra.security.TokenService;
import ts.juniors.rebook.model.Livro;
import ts.juniors.rebook.model.Usuario;
import ts.juniors.rebook.repository.UsuarioRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TokenService tokenService;

    private static Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    public Page<UsuarioDto> GetTodosUsuarios(Pageable paginacao) {
        return repository
                .findAll(paginacao)
                .map(p -> modelMapper.map(p, UsuarioDto.class));
    }

    public UsuarioDto GetPorId(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        return modelMapper.map(usuario, UsuarioDto.class);
    }

    public UsuarioInsertDto PostUsuario(UsuarioInsertDto dto) {

        Usuario usuario = modelMapper.map(dto, Usuario.class);
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        repository.save(usuario);
        return modelMapper.map(usuario, UsuarioInsertDto.class);
    }

    public UsuarioDto PutUsuario(Long id, UsuarioDto dto, String tokenJWT) {
        Long userIdFromToken = tokenService.getUserIdFromToken(tokenJWT);

        if (!userIdFromToken.equals(id)) {
            throw new SecurityException("Você não tem permissão para editar este usuário.");
        }


        Usuario usuarioExistente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        usuarioExistente.setNome(dto.getNome());
        usuarioExistente.setEmail(dto.getEmail());

        if (dto.getLivros() != null) {
            List<Livro> livros = dto.getLivros().stream()
                    .map(livroDto -> modelMapper.map(livroDto, Livro.class))
                    .collect(Collectors.toList());

            usuarioExistente.getLivros().clear();
            usuarioExistente.getLivros().addAll(livros);
        }

        usuarioExistente = repository.save(usuarioExistente);
        return modelMapper.map(usuarioExistente, UsuarioDto.class);
    }

    public void DeleteUsuario(Long id, String token) {
        Long userIdFromToken = tokenService.getUserIdFromToken(token);

        if (!id.equals(userIdFromToken)) {
            throw new SecurityException("Usuário não autorizado a deletar este perfil");
        }

        repository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = repository.findByEmail(username);
        if (usuario == null) {
            logger.error("User not found: " + username);
            throw new UsernameNotFoundException("Email not found");
        }
        logger.info("User found: " + username);
        return usuario;
    }
}
