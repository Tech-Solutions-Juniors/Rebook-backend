package ts.juniors.rebook.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ts.juniors.rebook.dto.UsuarioDto;
import ts.juniors.rebook.service.UsuarioService;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listarUsuarios() throws Exception {
        UsuarioDto dto = new UsuarioDto();
        dto.setId(1L);
        dto.setNome("João");
        dto.setEmail("joao@example.com");
        dto.setSenha("senha123");

        Page<UsuarioDto> page = new PageImpl<>(Collections.singletonList(dto), PageRequest.of(0, 10), 1);


        when(service.GetTodosUsuarios(any(Pageable.class))).thenReturn(page);


        mockMvc.perform(MockMvcRequestBuilders.get("/Usuario")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(page)));
    }

    @Test
    void detalharUsuarioPorId() throws Exception {
        UsuarioDto dto = new UsuarioDto();
        dto.setId(1L);
        dto.setNome("João");
        dto.setEmail("joao@example.com");
        dto.setSenha("senha123");


        when(service.GetPorId(anyLong())).thenReturn(dto);


        mockMvc.perform(MockMvcRequestBuilders.get("/Usuario/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(dto)));
    }

    @Test
    void cadastrarUsuario() throws Exception {

        UsuarioDto dto = new UsuarioDto();
        dto.setNome("João");
        dto.setEmail("joao@example.com");
        dto.setSenha("senha123");


        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(1L);
        usuarioDto.setNome("João");
        usuarioDto.setEmail("joao@example.com");
        usuarioDto.setSenha("senha123");


        when(service.PostUsuario(any(UsuarioDto.class))).thenReturn(usuarioDto);


        mockMvc.perform(MockMvcRequestBuilders.post("/Usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/Usuario/1")) // Ajuste para verificar URL completa
                .andExpect(content().json(objectMapper.writeValueAsString(usuarioDto)));


        ArgumentCaptor<UsuarioDto> captor = ArgumentCaptor.forClass(UsuarioDto.class);
        verify(service).PostUsuario(captor.capture());


        UsuarioDto capturedDto = captor.getValue();
        assertEquals(dto.getNome(), capturedDto.getNome());
        assertEquals(dto.getEmail(), capturedDto.getEmail());
        assertEquals(dto.getSenha(), capturedDto.getSenha());
    }

    @Test
    void atualizarUsuario() throws Exception {

        UsuarioDto dto = new UsuarioDto();
        dto.setId(1L);
        dto.setNome("João Atualizado");
        dto.setEmail("joao.atualizado@example.com");
        dto.setSenha("senha123");


        when(service.PutUsuario(anyLong(), any(UsuarioDto.class))).thenReturn(dto);


        mockMvc.perform(MockMvcRequestBuilders.put("/Usuario/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(dto)));
    }

    @Test
    void removerUsuario() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/Usuario/{id}", 1L))
                .andExpect(status().isNoContent());


        verify(service).DeleteUsuario(1L);
    }
}
