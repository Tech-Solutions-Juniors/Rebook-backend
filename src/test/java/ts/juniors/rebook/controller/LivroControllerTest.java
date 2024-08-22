package ts.juniors.rebook.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ts.juniors.rebook.dto.LivroDto;
import ts.juniors.rebook.service.LivroService;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LivroController.class)
public class LivroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LivroService livroService;

    @Test
    public void testListarLivros() throws Exception {
        Page<LivroDto> livros = new PageImpl<>(Arrays.asList(new LivroDto(), new LivroDto()));
        when(livroService.GetTodosLivros(any(PageRequest.class))).thenReturn(livros);

        mockMvc.perform(get("/Livro")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(2));
    }

    @Test
    public void testDetalharLivroPorId() throws Exception {
        LivroDto livroDto = new LivroDto();
        livroDto.setId(1L); // Defina um ID de teste
        livroDto.setTitulo("Livro Teste");

        when(livroService.GetPorId(anyLong())).thenReturn(livroDto);

        mockMvc.perform(get("/Livro/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L)) // Verifique o valor do ID
                .andExpect(jsonPath("$.titulo").value("Livro Teste")); // Verifique outros campos, se necessário
    }


    @Test
    public void testCadastrarLivro() throws Exception {
        LivroDto livroDto = new LivroDto();
        when(livroService.PostLivro(any(LivroDto.class))).thenReturn(livroDto);

        mockMvc.perform(post("/Livro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"titulo\": \"Livro Teste\" }"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testAtualizarLivro() throws Exception {
        LivroDto livroDto = new LivroDto();
        when(livroService.PutLivro(anyLong(), any(LivroDto.class))).thenReturn(livroDto);

        mockMvc.perform(put("/Livro/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"titulo\": \"Livro Teste Atualizado\" }"))
                .andExpect(status().isOk());
    }

    @Test
    public void testRemoverDeletar() throws Exception {
        mockMvc.perform(delete("/Livro/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
