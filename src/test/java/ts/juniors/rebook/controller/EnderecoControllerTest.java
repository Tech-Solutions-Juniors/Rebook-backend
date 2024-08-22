package ts.juniors.rebook.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ts.juniors.rebook.dto.EnderecoDTO;
import ts.juniors.rebook.model.Endereco;
import ts.juniors.rebook.service.EnderecoService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EnderecoController.class)
public class EnderecoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnderecoService enderecoService;

    @Test
    public void testListarEnderecos() throws Exception {
        Page<EnderecoDTO> enderecos = new PageImpl<>(Arrays.asList(new EnderecoDTO(), new EnderecoDTO()));
        when(enderecoService.getTodosEnderecos(any(PageRequest.class))).thenReturn(enderecos);

        mockMvc.perform(get("/Endereco")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(2));
    }

    @Test
    public void testDetalharEnderecoPorId() throws Exception {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        when(enderecoService.getPorId(anyLong())).thenReturn(enderecoDTO);

        mockMvc.perform(get("/Endereco/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").doesNotExist());  // Assuming the DTO doesn't have an ID field in the example
    }

    @Test
    public void testCadastrarEndereco() throws Exception {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        when(enderecoService.postEndereco(any(EnderecoDTO.class))).thenReturn(enderecoDTO);

        mockMvc.perform(post("/Endereco")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"titulo\": \"Endereco Teste\" }"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testAtualizarEndereco() throws Exception {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        when(enderecoService.putEndereco(anyLong(), any(EnderecoDTO.class))).thenReturn(enderecoDTO);

        mockMvc.perform(put("/Endereco/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"titulo\": \"Endereco Teste Atualizado\" }"))
                .andExpect(status().isOk());
    }

    @Test
    public void testRemoverDeletar() throws Exception {
        mockMvc.perform(delete("/Endereco/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetEnderecosByCidade() throws Exception {
        List<Endereco> enderecos = Arrays.asList(new Endereco(), new Endereco());
        when(enderecoService.getPorCidade(anyString())).thenReturn(enderecos);

        mockMvc.perform(get("/Endereco/cidade/{cidade}", "São Paulo")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testGetEnderecosByEstado() throws Exception {
        List<Endereco> enderecos = Arrays.asList(new Endereco(), new Endereco());
        when(enderecoService.getPorEstado(anyString())).thenReturn(enderecos);

        mockMvc.perform(get("/Endereco/estado/{estado}", "SP")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
}
