package ts.juniors.rebook.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ts.juniors.rebook.dto.LivroDto;
import ts.juniors.rebook.model.Livro;
import ts.juniors.rebook.repository.LivroRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LivroServiceTest {

    @Mock
    private LivroRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private LivroService livroService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void GetTodosLivros_ShouldReturnLivroDtoPage() {
        Pageable pageable = PageRequest.of(0, 10);
        Livro livro = new Livro();
        LivroDto livroDto = new LivroDto();
        Page<Livro> livroPage = new PageImpl<>(List.of(livro));
        when(repository.findAll(pageable)).thenReturn(livroPage);
        when(modelMapper.map(any(Livro.class), eq(LivroDto.class))).thenReturn(livroDto);

        Page<LivroDto> result = livroService.GetTodosLivros(pageable);

        assertThat(result.getContent()).hasSize(1);
        verify(repository, times(1)).findAll(pageable);
        verify(modelMapper, times(1)).map(livro, LivroDto.class);
    }

    @Test
    void GetPorId_ShouldReturnLivroDto_WhenLivroExists() {
        Long id = 1L;
        Livro livro = new Livro();
        LivroDto livroDto = new LivroDto();
        when(repository.findById(id)).thenReturn(Optional.of(livro));
        when(modelMapper.map(livro, LivroDto.class)).thenReturn(livroDto);

        LivroDto result = livroService.GetPorId(id);

        assertThat(result).isNotNull();
        verify(repository, times(1)).findById(id);
        verify(modelMapper, times(1)).map(livro, LivroDto.class);
    }

    @Test
    void GetPorId_ShouldThrowEntityNotFoundException_WhenLivroDoesNotExist() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> livroService.GetPorId(id));
        verify(repository, times(1)).findById(id);
    }

    @Test
    void PostLivro_ShouldSaveAndReturnLivroDto() {
        LivroDto livroDto = new LivroDto();
        Livro livro = new Livro();
        when(modelMapper.map(livroDto, Livro.class)).thenReturn(livro);
        when(repository.save(livro)).thenReturn(livro);
        when(modelMapper.map(livro, LivroDto.class)).thenReturn(livroDto);

        LivroDto result = livroService.PostLivro(livroDto);

        assertThat(result).isNotNull();
        verify(repository, times(1)).save(livro);
        verify(modelMapper, times(1)).map(livroDto, Livro.class);
        verify(modelMapper, times(1)).map(livro, LivroDto.class);
    }

    @Test
    void PutLivro_ShouldUpdateAndReturnLivroDto() {
        Long id = 1L;
        LivroDto livroDto = new LivroDto();
        Livro livro = new Livro();
        when(modelMapper.map(livroDto, Livro.class)).thenReturn(livro);
        when(repository.save(livro)).thenReturn(livro);
        when(modelMapper.map(livro, LivroDto.class)).thenReturn(livroDto);

        LivroDto result = livroService.PutLivro(id, livroDto);

        assertThat(result).isNotNull();
        assertThat(livro.getId()).isEqualTo(id);
        verify(repository, times(1)).save(livro);
        verify(modelMapper, times(1)).map(livroDto, Livro.class);
        verify(modelMapper, times(1)).map(livro, LivroDto.class);
    }

    @Test
    void DeleteLivro_ShouldCallRepositoryDeleteById() {
        Long id = 1L;

        livroService.DeleteLivro(id);

        verify(repository, times(1)).deleteById(id);
    }
}
