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
import ts.juniors.rebook.dto.LivroDto;
import ts.juniors.rebook.service.LivroService;

import java.net.URI;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class LivroControllerTest {

    @InjectMocks
    private LivroController controller;

    @Mock
    private LivroService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarLivros() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<LivroDto> page = new PageImpl<>(Collections.emptyList());
        when(service.GetTodosLivros(pageable)).thenReturn(page);

        Page<LivroDto> result = controller.listarLivros(pageable);

        assertEquals(page, result);
        verify(service, times(1)).GetTodosLivros(pageable);
    }

    @Test
    void detalharLivroPorId() {
        LivroDto dto = new LivroDto();
        when(service.GetPorId(anyLong())).thenReturn(dto);

        ResponseEntity<LivroDto> response = controller.detalharLivroPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
        verify(service, times(1)).GetPorId(1L);
    }



    @Test
    void atualizarLivro() {
        LivroDto dto = new LivroDto();
        LivroDto updatedDto = new LivroDto();
        when(service.PutLivro(anyLong(), any(LivroDto.class))).thenReturn(updatedDto);

        ResponseEntity<LivroDto> response = controller.atualizarLivro(1L, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedDto, response.getBody());
        verify(service, times(1)).PutLivro(1L, dto);
    }

    @Test
    void removerDeletar() {
        doNothing().when(service).DeleteLivro(anyLong());

        ResponseEntity<LivroDto> response = controller.removerDeletar(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(service, times(1)).DeleteLivro(1L);
    }
}
