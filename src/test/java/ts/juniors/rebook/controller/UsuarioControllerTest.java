package ts.juniors.rebook.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import ts.juniors.rebook.dto.UsuarioDto;
import ts.juniors.rebook.dto.UsuarioInsertDto;
import ts.juniors.rebook.service.UsuarioService;

import java.net.URI;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class UsuarioControllerTest {

    @Mock
    private UsuarioService service;

    @InjectMocks
    private UsuarioController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarUsuarios() {
        Pageable pageable = PageRequest.of(0, 10);
        UsuarioDto usuarioDto = new UsuarioDto();
        Page<UsuarioDto> page = new PageImpl<>(Collections.singletonList(usuarioDto));
        when(service.GetTodosUsuarios(pageable)).thenReturn(page);

        Page<UsuarioDto> result = controller.listarUsuarios(pageable);

        assertEquals(1, result.getTotalElements());
        verify(service, times(1)).GetTodosUsuarios(pageable);
    }

    @Test
    void detalharUsuarioPorId() {
        UsuarioDto usuarioDto = new UsuarioDto();
        when(service.GetPorId(anyLong())).thenReturn(usuarioDto);

        ResponseEntity<UsuarioDto> response = controller.detalharUsuarioPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuarioDto, response.getBody());
        verify(service, times(1)).GetPorId(1L);
    }




    @Test
    void atualizarUsuario() {
        UsuarioDto usuarioDto = new UsuarioDto();
        when(service.PutUsuario(anyLong(), any(UsuarioDto.class))).thenReturn(usuarioDto);

        ResponseEntity<UsuarioDto> response = controller.atualizarUsuario(1L, usuarioDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuarioDto, response.getBody());
        verify(service, times(1)).PutUsuario(1L, usuarioDto);
    }

    @Test
    void removerDeletar() {
        doNothing().when(service).DeleteUsuario(anyLong());

        ResponseEntity<UsuarioDto> response = controller.removerDeletar(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(service, times(1)).DeleteUsuario(1L);
    }
}
