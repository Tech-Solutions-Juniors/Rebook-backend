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
import ts.juniors.rebook.dto.EnderecoDTO;
import ts.juniors.rebook.model.Endereco;
import ts.juniors.rebook.service.EnderecoService;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class EnderecoControllerTest {

    @InjectMocks
    private EnderecoController controller;

    @Mock
    private EnderecoService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarEnderecos() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<EnderecoDTO> page = new PageImpl<>(Collections.emptyList());
        when(service.getTodosEnderecos(pageable)).thenReturn(page);

        Page<EnderecoDTO> result = controller.listarEnderecos(pageable);

        assertNotNull(result);
        assertEquals(0, result.getTotalElements());
        verify(service, times(1)).getTodosEnderecos(pageable);
    }

    @Test
    void detalharEnderecoPorId() {
        EnderecoDTO dto = new EnderecoDTO();
        when(service.getPorId(anyLong())).thenReturn(dto);

        ResponseEntity<EnderecoDTO> response = controller.detalharEnderecoPorId(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
        verify(service, times(1)).getPorId(1L);
    }

    @Test
    void cadastrarEndereco() {
        EnderecoDTO dto = new EnderecoDTO();
        EnderecoDTO savedDto = new EnderecoDTO();
        savedDto.setId(1L);
        when(service.postEndereco(any(EnderecoDTO.class))).thenReturn(savedDto);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();
        ResponseEntity<EnderecoDTO> response = controller.cadastrarEndereco(dto, uriBuilder);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedDto, response.getBody());
        assertEquals("/endereco/1", response.getHeaders().getLocation().getPath());
        verify(service, times(1)).postEndereco(dto);
    }

    @Test
    void atualizarEndereco() {
        EnderecoDTO dto = new EnderecoDTO();
        EnderecoDTO updatedDto = new EnderecoDTO();
        when(service.putEndereco(anyLong(), any(EnderecoDTO.class))).thenReturn(updatedDto);

        ResponseEntity<EnderecoDTO> response = controller.atualizarEndereco(1L, dto);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedDto, response.getBody());
        verify(service, times(1)).putEndereco(1L, dto);
    }

    @Test
    void removerDeletar() {
        doNothing().when(service).deleteEndereco(anyLong());

        ResponseEntity<EnderecoDTO> response = controller.removerDeletar(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(service, times(1)).deleteEndereco(1L);
    }

    @Test
    void getEnderecosByCidade() {
        List<Endereco> enderecos = Collections.emptyList();
        when(service.getPorCidade(anyString())).thenReturn(enderecos);

        List<Endereco> result = controller.getEnderecosByCidade("cidade");

        assertNotNull(result);
        assertEquals(0, result.size());
        verify(service, times(1)).getPorCidade("cidade");
    }

    @Test
    void getEnderecosByEstado() {
        List<Endereco> enderecos = Collections.emptyList();
        when(service.getPorEstado(anyString())).thenReturn(enderecos);

        List<Endereco> result = controller.getEnderecosByEstado("estado");

        assertNotNull(result);
        assertEquals(0, result.size());
        verify(service, times(1)).getPorEstado("estado");
    }
}
