package ts.juniors.rebook.controller;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ts.juniors.rebook.dto.EnderecoDTO;
import ts.juniors.rebook.model.Endereco;
import ts.juniors.rebook.service.EnderecoService;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EnderecoControllerTest {

    @Mock
    private EnderecoService enderecoService;

    @InjectMocks
    private EnderecoController enderecoController;

    @Test
    public void testGetEndereco() {
        Endereco endereco = new Endereco();
        endereco.setId(1L);
        endereco.setRua("Rua A");

        when(enderecoService.findById(1L)).thenReturn(endereco);

        EnderecoDTO result = enderecoController.getEndereco(1L);

        assertEquals("Rua A", result.getRua());
        verify(enderecoService, times(1)).findById(1L);
    }

    @Test
    public void testCreateEndereco() {
        Endereco endereco = new Endereco();
        endereco.setRua("Rua B");

        when(enderecoService.save(any(Endereco.class))).thenReturn(endereco);

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setRua("Rua B");

        EnderecoDTO result = enderecoController.createEndereco(enderecoDTO);

        assertEquals("Rua B", result.getRua());
        verify(enderecoService, times(1)).save(any(Endereco.class));
    }

    @Test
    public void testUpdateEndereco() {
        Endereco endereco = new Endereco();
        endereco.setRua("Rua C");

        when(enderecoService.update(anyLong(), any(Endereco.class))).thenReturn(endereco);

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setRua("Rua C");

        EnderecoDTO result = enderecoController.updateEndereco(1L, enderecoDTO);

        assertEquals("Rua C", result.getRua());
        verify(enderecoService, times(1)).update(anyLong(), any(Endereco.class));
    }

    @Test
    public void testDeleteEndereco() {
        doNothing().when(enderecoService).deleteById(1L);

        enderecoController.deleteEndereco(1L);

        verify(enderecoService, times(1)).deleteById(1L);
    }

    @Test
    public void testGetAllEnderecos() {
        Endereco endereco = new Endereco();
        endereco.setRua("Rua D");

        when(enderecoService.findAll()).thenReturn(Collections.singletonList(endereco));

        List<EnderecoDTO> result = enderecoController.getAllEnderecos();

        assertEquals(1, result.size());
        assertEquals("Rua D", result.get(0).getRua());
        verify(enderecoService, times(1)).findAll();
    }

    @Test
    public void testGetEnderecosByCidade() {
        Endereco endereco = new Endereco();
        endereco.setCidade("Cidade A");

        when(enderecoService.findByCidade("Cidade A")).thenReturn(Collections.singletonList(endereco));

        List<EnderecoDTO> result = enderecoController.getEnderecosByCidade("Cidade A");

        assertEquals(1, result.size());
        assertEquals("Cidade A", result.get(0).getCidade());
        verify(enderecoService, times(1)).findByCidade("Cidade A");
    }

    @Test
    public void testGetEnderecosByEstado() {
        Endereco endereco = new Endereco();
        endereco.setEstado("Estado B");

        when(enderecoService.findByEstado("Estado B")).thenReturn(Collections.singletonList(endereco));

        List<EnderecoDTO> result = enderecoController.getEnderecosByEstado("Estado B");

        assertEquals(1, result.size());
        assertEquals("Estado B", result.get(0).getEstado());
        verify(enderecoService, times(1)).findByEstado("Estado B");
    }
}
