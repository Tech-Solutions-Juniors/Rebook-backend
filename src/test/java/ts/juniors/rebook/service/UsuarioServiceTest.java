package ts.juniors.rebook.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ts.juniors.rebook.dto.UsuarioDto;
import ts.juniors.rebook.model.Usuario;
import ts.juniors.rebook.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void GetTodosUsuarios_ShouldReturnUsuarioDtoPage() {
        Pageable pageable = PageRequest.of(0, 10);
        Usuario usuario = new Usuario();
        UsuarioDto usuarioDto = new UsuarioDto();
        Page<Usuario> usuarioPage = new PageImpl<>(List.of(usuario));
        when(repository.findAll(pageable)).thenReturn(usuarioPage);
        when(modelMapper.map(any(Usuario.class), eq(UsuarioDto.class))).thenReturn(usuarioDto);

        Page<UsuarioDto> result = usuarioService.GetTodosUsuarios(pageable);

        assertThat(result.getContent()).hasSize(1);
        verify(repository, times(1)).findAll(pageable);
        verify(modelMapper, times(1)).map(usuario, UsuarioDto.class);
    }

    @Test
    void GetPorId_ShouldReturnUsuarioDto_WhenUsuarioExists() {
        Long id = 1L;
        Usuario usuario = new Usuario();
        UsuarioDto usuarioDto = new UsuarioDto();
        when(repository.findById(id)).thenReturn(Optional.of(usuario));
        when(modelMapper.map(usuario, UsuarioDto.class)).thenReturn(usuarioDto);

        UsuarioDto result = usuarioService.GetPorId(id);

        assertThat(result).isNotNull();
        verify(repository, times(1)).findById(id);
        verify(modelMapper, times(1)).map(usuario, UsuarioDto.class);
    }

    @Test
    void GetPorId_ShouldThrowEntityNotFoundException_WhenUsuarioDoesNotExist() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> usuarioService.GetPorId(id));
        verify(repository, times(1)).findById(id);
    }

    @Test
    void PostUsuario_ShouldSaveAndReturnUsuarioDto() {
        UsuarioDto usuarioDto = new UsuarioDto();
        Usuario usuario = new Usuario();
        when(modelMapper.map(usuarioDto, Usuario.class)).thenReturn(usuario);
        when(repository.save(usuario)).thenReturn(usuario);
        when(modelMapper.map(usuario, UsuarioDto.class)).thenReturn(usuarioDto);

        UsuarioDto result = usuarioService.PostUsuario(usuarioDto);

        assertThat(result).isNotNull();
        verify(repository, times(1)).save(usuario);
        verify(modelMapper, times(1)).map(usuarioDto, Usuario.class);
        verify(modelMapper, times(1)).map(usuario, UsuarioDto.class);
    }

    @Test
    void PutUsuario_ShouldUpdateAndReturnUsuarioDto() {
        Long id = 1L;
        UsuarioDto usuarioDto = new UsuarioDto();
        Usuario usuario = new Usuario();
        when(modelMapper.map(usuarioDto, Usuario.class)).thenReturn(usuario);
        when(repository.save(usuario)).thenReturn(usuario);
        when(modelMapper.map(usuario, UsuarioDto.class)).thenReturn(usuarioDto);

        UsuarioDto result = usuarioService.PutUsuario(id, usuarioDto);

        assertThat(result).isNotNull();
        assertThat(usuario.getId()).isEqualTo(id);
        verify(repository, times(1)).save(usuario);
        verify(modelMapper, times(1)).map(usuarioDto, Usuario.class);
        verify(modelMapper, times(1)).map(usuario, UsuarioDto.class);
    }

    @Test
    void DeleteUsuario_ShouldCallRepositoryDeleteById() {
        Long id = 1L;

        usuarioService.DeleteUsuario(id);

        verify(repository, times(1)).deleteById(id);
    }
}
