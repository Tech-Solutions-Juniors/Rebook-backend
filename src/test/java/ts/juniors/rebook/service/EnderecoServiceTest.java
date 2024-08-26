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
import ts.juniors.rebook.dto.EnderecoDTO;
import ts.juniors.rebook.model.Endereco;
import ts.juniors.rebook.repository.EnderecoReposiotry;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EnderecoServiceTest {

    @Mock
    private EnderecoReposiotry repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private EnderecoService service;

    private Endereco endereco;
    private EnderecoDTO enderecoDTO;

    @BeforeEach
    void setUp() {
        endereco = new Endereco();
        endereco.setId(1L);
        endereco.setCidade("Cidade Teste");
        endereco.setEstado("Estado Teste");

        enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(1L);
        enderecoDTO.setCidade("Cidade Teste");
        enderecoDTO.setEstado("Estado Teste");
    }

    @Test
    void testGetTodosEnderecos() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Endereco> enderecoPage = new PageImpl<>(Collections.singletonList(endereco));
        when(repository.findAll(pageable)).thenReturn(enderecoPage);
        when(modelMapper.map(any(Endereco.class), eq(EnderecoDTO.class))).thenReturn(enderecoDTO);

        Page<EnderecoDTO> result = service.getTodosEnderecos(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(repository, times(1)).findAll(pageable);
    }

    @Test
    void testGetPorId() {
        when(repository.findById(1L)).thenReturn(Optional.of(endereco));
        when(modelMapper.map(any(Endereco.class), eq(EnderecoDTO.class))).thenReturn(enderecoDTO);

        EnderecoDTO result = service.getPorId(1L);

        assertNotNull(result);
        assertEquals("Cidade Teste", result.getCidade());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testGetPorIdNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.getPorId(1L));
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testGetPorCidade() {
        when(repository.findByCidade("Cidade Teste")).thenReturn(Collections.singletonList(endereco));

        List<Endereco> result = service.getPorCidade("Cidade Teste");

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository, times(1)).findByCidade("Cidade Teste");
    }

    @Test
    void testGetPorEstado() {
        when(repository.findByEstado("Estado Teste")).thenReturn(Collections.singletonList(endereco));

        List<Endereco> result = service.getPorEstado("Estado Teste");

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository, times(1)).findByEstado("Estado Teste");
    }

    @Test
    void testPostEndereco() {
        when(modelMapper.map(any(EnderecoDTO.class), eq(Endereco.class))).thenReturn(endereco);
        when(repository.save(any(Endereco.class))).thenReturn(endereco);
        when(modelMapper.map(any(Endereco.class), eq(EnderecoDTO.class))).thenReturn(enderecoDTO);

        EnderecoDTO result = service.postEndereco(enderecoDTO);

        assertNotNull(result);
        assertEquals("Cidade Teste", result.getCidade());
        verify(repository, times(1)).save(any(Endereco.class));
    }

    @Test
    void testPutEndereco() {
        when(modelMapper.map(any(EnderecoDTO.class), eq(Endereco.class))).thenReturn(endereco);
        when(repository.save(any(Endereco.class))).thenReturn(endereco);
        when(modelMapper.map(any(Endereco.class), eq(EnderecoDTO.class))).thenReturn(enderecoDTO);

        EnderecoDTO result = service.putEndereco(1L, enderecoDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository, times(1)).save(any(Endereco.class));
    }

    @Test
    void testDeleteEndereco() {
        doNothing().when(repository).deleteById(1L);

        service.deleteEndereco(1L);

        verify(repository, times(1)).deleteById(1L);
    }
}
