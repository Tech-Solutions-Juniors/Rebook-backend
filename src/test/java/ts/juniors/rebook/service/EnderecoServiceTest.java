package ts.juniors.rebook.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ts.juniors.rebook.model.Endereco;
import ts.juniors.rebook.repository.EnderecoReposiotry;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnderecoServiceTest {

    @Mock
    private EnderecoReposiotry enderecoRepository;

    @InjectMocks
    private EnderecoService enderecoService;

    private Endereco endereco;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        endereco = new Endereco();
        endereco.setId(1L);
        endereco.setRua("Rua A");
        endereco.setCidade("Cidade B");
        endereco.setEstado("Estado C");
        endereco.setCep("12345-678");
        endereco.setNumero("100");
    }

    @Test
    void testSaveEndereco() {
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(endereco);

        Endereco savedEndereco = enderecoService.save(endereco);

        assertNotNull(savedEndereco);
        assertEquals("Rua A", savedEndereco.getRua());
        verify(enderecoRepository, times(1)).save(any(Endereco.class));
    }

    @Test
    void testFindById() {
        when(enderecoRepository.findById(1L)).thenReturn(Optional.of(endereco));

        Endereco foundEndereco = enderecoService.findById(1L);

        assertNotNull(foundEndereco);
        assertEquals("Cidade B", foundEndereco.getCidade());
        verify(enderecoRepository, times(1)).findById(1L);
    }

    @Test
    void testFindAll() {
        when(enderecoRepository.findAll()).thenReturn(Arrays.asList(endereco));

        List<Endereco> enderecos = enderecoService.findAll();

        assertNotNull(enderecos);
        assertFalse(enderecos.isEmpty());
        assertEquals(1, enderecos.size());
        verify(enderecoRepository, times(1)).findAll();
    }

    @Test
    void testUpdateEndereco() {
        Endereco newEndereco = new Endereco();
        newEndereco.setRua("Rua Nova");

        when(enderecoRepository.findById(1L)).thenReturn(Optional.of(endereco));
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(endereco);

        Endereco updatedEndereco = enderecoService.update(1L, newEndereco);

        assertNotNull(updatedEndereco);
        assertEquals("Rua Nova", updatedEndereco.getRua());
        verify(enderecoRepository, times(1)).findById(1L);
        verify(enderecoRepository, times(1)).save(any(Endereco.class));
    }

    @Test
    void testDeleteById() {
        doNothing().when(enderecoRepository).deleteById(1L);

        enderecoService.deleteById(1L);

        verify(enderecoRepository, times(1)).deleteById(1L);
    }
    @Test
    void testFindByCidade() {
        Endereco endereco1 = new Endereco();
        endereco1.setCidade("Cidade X");

        Endereco endereco2 = new Endereco();
        endereco2.setCidade("Cidade X");

        List<Endereco> enderecos = Arrays.asList(endereco1, endereco2);

        when(enderecoRepository.findByCidade("Cidade X")).thenReturn(enderecos);

        List<Endereco> result = enderecoService.findByCidade("Cidade X");

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Cidade X", result.get(0).getCidade());
        assertEquals("Cidade X", result.get(1).getCidade());
        verify(enderecoRepository, times(1)).findByCidade("Cidade X");
    }

    @Test
    void testFindByEstado() {
        Endereco endereco1 = new Endereco();
        endereco1.setEstado("Estado Y");

        Endereco endereco2 = new Endereco();
        endereco2.setEstado("Estado Y");

        List<Endereco> enderecos = Arrays.asList(endereco1, endereco2);

        when(enderecoRepository.findByEstado("Estado Y")).thenReturn(enderecos);

        List<Endereco> result = enderecoService.findByEstado("Estado Y");

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Estado Y", result.get(0).getEstado());
        assertEquals("Estado Y", result.get(1).getEstado());
        verify(enderecoRepository, times(1)).findByEstado("Estado Y");
    }
}
