package ts.juniors.rebook.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ts.juniors.rebook.dto.UsuarioDto;
import ts.juniors.rebook.dto.UsuarioInsertDto;
import ts.juniors.rebook.model.Livro;
import ts.juniors.rebook.model.Usuario;
import ts.juniors.rebook.repository.UsuarioRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UsuarioService service;

    private Usuario usuario;
    private UsuarioDto usuarioDto;
    private UsuarioInsertDto usuarioInsertDto;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Nome Teste");
        usuario.setEmail("email@teste.com");

        usuarioDto = new UsuarioDto();
        usuarioDto.setId(1L);
        usuarioDto.setNome("Nome Teste");
        usuarioDto.setEmail("email@teste.com");

        usuarioInsertDto = new UsuarioInsertDto();
        usuarioInsertDto.setNome("Nome Teste");
        usuarioInsertDto.setEmail("email@teste.com");
        usuarioInsertDto.setSenha("senha123");
    }

    @Test
    void testGetTodosUsuarios() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Usuario> usuarioPage = new PageImpl<>(Collections.singletonList(usuario));
        when(repository.findAll(pageable)).thenReturn(usuarioPage);
        when(modelMapper.map(any(Usuario.class), eq(UsuarioDto.class))).thenReturn(usuarioDto);

        Page<UsuarioDto> result = service.GetTodosUsuarios(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(repository, times(1)).findAll(pageable);
    }

    @Test
    void testGetPorId() {
        when(repository.findById(1L)).thenReturn(Optional.of(usuario));
        when(modelMapper.map(any(Usuario.class), eq(UsuarioDto.class))).thenReturn(usuarioDto);

        UsuarioDto result = service.GetPorId(1L);

        assertNotNull(result);
        assertEquals("Nome Teste", result.getNome());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testGetPorIdNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.GetPorId(1L));
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testPostUsuario() {
        when(modelMapper.map(any(UsuarioInsertDto.class), eq(Usuario.class))).thenReturn(usuario);
        when(passwordEncoder.encode(anyString())).thenReturn("senha123");
        when(repository.save(any(Usuario.class))).thenReturn(usuario);
        when(modelMapper.map(any(Usuario.class), eq(UsuarioInsertDto.class))).thenReturn(usuarioInsertDto);

        UsuarioInsertDto result = service.PostUsuario(usuarioInsertDto);

        assertNotNull(result);
        assertEquals("Nome Teste", result.getNome());
        verify(repository, times(1)).save(any(Usuario.class));
    }

    @Test
    void testPutUsuario() {
        when(repository.findById(1L)).thenReturn(Optional.of(usuario));
        when(repository.save(any(Usuario.class))).thenReturn(usuario);
        when(modelMapper.map(any(Usuario.class), eq(UsuarioDto.class))).thenReturn(usuarioDto);

        UsuarioDto result = service.PutUsuario(1L, usuarioDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository, times(1)).save(any(Usuario.class));
    }


    @Test
    void testDeleteUsuario() {
        doNothing().when(repository).deleteById(1L);

        service.DeleteUsuario(1L);

        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void testLoadUserByUsername() {
        when(repository.findByEmail("email@teste.com")).thenReturn(usuario);

        UserDetails result = service.loadUserByUsername("email@teste.com");

        assertNotNull(result);
        assertEquals("email@teste.com", result.getUsername());
        verify(repository, times(1)).findByEmail("email@teste.com");
    }

    @Test
    void testLoadUserByUsernameNotFound() {
        when(repository.findByEmail("email@teste.com")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername("email@teste.com"));
        verify(repository, times(1)).findByEmail("email@teste.com");
    }
}
