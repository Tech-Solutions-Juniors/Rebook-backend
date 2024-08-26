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
import ts.juniors.rebook.dto.LivroDto;
import ts.juniors.rebook.model.Livro;
import ts.juniors.rebook.repository.LivroRepository;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LivroServiceTest {

    @Mock
    private LivroRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private LivroService service;

    private Livro livro;
    private LivroDto livroDto;

    @BeforeEach
    void setUp() {
        livro = new Livro();
        livro.setId(1L);
        livro.setTitulo("Título Teste");

        livroDto = new LivroDto();
        livroDto.setId(1L);
        livroDto.setTitulo("Título Teste");
    }

    @Test
    void testGetTodosLivros() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Livro> livroPage = new PageImpl<>(Collections.singletonList(livro));
        when(repository.findAll(pageable)).thenReturn(livroPage);
        when(modelMapper.map(any(Livro.class), eq(LivroDto.class))).thenReturn(livroDto);

        Page<LivroDto> result = service.GetTodosLivros(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(repository, times(1)).findAll(pageable);
    }

    @Test
    void testGetPorId() {
        when(repository.findById(1L)).thenReturn(Optional.of(livro));
        when(modelMapper.map(any(Livro.class), eq(LivroDto.class))).thenReturn(livroDto);

        LivroDto result = service.GetPorId(1L);

        assertNotNull(result);
        assertEquals("Título Teste", result.getTitulo());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testGetPorIdNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.GetPorId(1L));
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testPostLivro() {
        when(modelMapper.map(any(LivroDto.class), eq(Livro.class))).thenReturn(livro);
        when(repository.save(any(Livro.class))).thenReturn(livro);
        when(modelMapper.map(any(Livro.class), eq(LivroDto.class))).thenReturn(livroDto);

        LivroDto result = service.PostLivro(livroDto);

        assertNotNull(result);
        assertEquals("Título Teste", result.getTitulo());
        verify(repository, times(1)).save(any(Livro.class));
    }

    @Test
    void testPutLivro() {
        when(modelMapper.map(any(LivroDto.class), eq(Livro.class))).thenReturn(livro);
        when(repository.save(any(Livro.class))).thenReturn(livro);
        when(modelMapper.map(any(Livro.class), eq(LivroDto.class))).thenReturn(livroDto);

        LivroDto result = service.PutLivro(1L, livroDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository, times(1)).save(any(Livro.class));
    }

    @Test
    void testDeleteLivro() {
        doNothing().when(repository).deleteById(1L);

        service.DeleteLivro(1L);

        verify(repository, times(1)).deleteById(1L);
    }
}
