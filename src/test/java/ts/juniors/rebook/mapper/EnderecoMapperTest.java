package ts.juniors.rebook.mapper;

import org.junit.jupiter.api.Test;
import ts.juniors.rebook.dto.EnderecoDTO;
import ts.juniors.rebook.model.Endereco;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnderecoMapperTest {

    @Test
    public void testConvertToEntity() {
        EnderecoDTO dto = new EnderecoDTO(1L, "Rua A", "Cidade B", "Estado C", "12345-678", "100", "Apt 101");
        Endereco endereco = EnderecoMapper.convertToEntity(dto);

        assertEquals(dto.getId(), endereco.getId());
        assertEquals(dto.getRua(), endereco.getRua());
        assertEquals(dto.getCidade(), endereco.getCidade());
        assertEquals(dto.getEstado(), endereco.getEstado());
        assertEquals(dto.getCep(), endereco.getCep());
        assertEquals(dto.getNumero(), endereco.getNumero());
        assertEquals(dto.getComplemento(), endereco.getComplemento());
    }

    @Test
    public void testConvertToDTO() {
        Endereco endereco = new Endereco();
        endereco.setId(1L);
        endereco.setRua("Rua A");
        endereco.setCidade("Cidade B");
        endereco.setEstado("Estado C");
        endereco.setCep("12345-678");
        endereco.setNumero("100");
        endereco.setComplemento("Apt 101");

        EnderecoDTO dto = EnderecoMapper.convertToDTO(endereco);

        assertEquals(endereco.getId(), dto.getId());
        assertEquals(endereco.getRua(), dto.getRua());
        assertEquals(endereco.getCidade(), dto.getCidade());
        assertEquals(endereco.getEstado(), dto.getEstado());
        assertEquals(endereco.getCep(), dto.getCep());
        assertEquals(endereco.getNumero(), dto.getNumero());
        assertEquals(endereco.getComplemento(), dto.getComplemento());
    }
}
