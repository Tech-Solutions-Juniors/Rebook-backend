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
import ts.juniors.rebook.model.Usuario;
import ts.juniors.rebook.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;

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

    public UsuarioDto PutUsuario(Long id, UsuarioDto dto) {
        Usuario usuario = modelMapper.map(dto, Usuario.class);
        usuario.setId(id);
        usuario = repository.save(usuario);
        return modelMapper.map(usuario, UsuarioDto.class);
    }

    public void DeleteUsuario(Long id) {
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
