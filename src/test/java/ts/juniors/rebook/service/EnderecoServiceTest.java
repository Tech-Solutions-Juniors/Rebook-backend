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
import ts.juniors.rebook.dto.EnderecoDTO;
import ts.juniors.rebook.model.Endereco;
import ts.juniors.rebook.repository.EnderecoReposiotry;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EnderecoServiceTest {

    @Mock
    private EnderecoReposiotry repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private EnderecoService enderecoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getTodosEnderecos_ShouldReturnEnderecoDTOPage() {

        Pageable pageable = PageRequest.of(0, 10);
        Endereco endereco = new Endereco();
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        Page<Endereco> enderecoPage = new PageImpl<>(List.of(endereco));
        when(repository.findAll(pageable)).thenReturn(enderecoPage);
        when(modelMapper.map(any(Endereco.class), eq(EnderecoDTO.class))).thenReturn(enderecoDTO);


        Page<EnderecoDTO> result = enderecoService.getTodosEnderecos(pageable);


        assertThat(result.getContent()).hasSize(1);
        verify(repository, times(1)).findAll(pageable);
        verify(modelMapper, times(1)).map(endereco, EnderecoDTO.class);
    }

    @Test
    void getPorId_ShouldReturnEnderecoDTO_WhenEnderecoExists() {

        Long id = 1L;
        Endereco endereco = new Endereco();
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        when(repository.findById(id)).thenReturn(Optional.of(endereco));
        when(modelMapper.map(endereco, EnderecoDTO.class)).thenReturn(enderecoDTO);


        EnderecoDTO result = enderecoService.getPorId(id);


        assertThat(result).isNotNull();
        verify(repository, times(1)).findById(id);
        verify(modelMapper, times(1)).map(endereco, EnderecoDTO.class);
    }

    @Test
    void getPorId_ShouldThrowEntityNotFoundException_WhenEnderecoDoesNotExist() {

        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());


        assertThrows(EntityNotFoundException.class, () -> enderecoService.getPorId(id));
        verify(repository, times(1)).findById(id);
    }

    @Test
    void getPorCidade_ShouldReturnEnderecoList() {

        String cidade = "São Paulo";
        List<Endereco> enderecos = Arrays.asList(new Endereco(), new Endereco());
        when(repository.findByCidade(cidade)).thenReturn(enderecos);


        List<Endereco> result = enderecoService.getPorCidade(cidade);


        assertThat(result).hasSize(2);
        verify(repository, times(1)).findByCidade(cidade);
    }

    @Test
    void getPorEstado_ShouldReturnEnderecoList() {

        String estado = "SP";
        List<Endereco> enderecos = Arrays.asList(new Endereco(), new Endereco());
        when(repository.findByEstado(estado)).thenReturn(enderecos);


        List<Endereco> result = enderecoService.getPorEstado(estado);


        assertThat(result).hasSize(2);
        verify(repository, times(1)).findByEstado(estado);
    }

    @Test
    void postEndereco_ShouldSaveAndReturnEnderecoDTO() {

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        Endereco endereco = new Endereco();
        when(modelMapper.map(enderecoDTO, Endereco.class)).thenReturn(endereco);
        when(repository.save(endereco)).thenReturn(endereco);
        when(modelMapper.map(endereco, EnderecoDTO.class)).thenReturn(enderecoDTO);


        EnderecoDTO result = enderecoService.postEndereco(enderecoDTO);


        assertThat(result).isNotNull();
        verify(repository, times(1)).save(endereco);
        verify(modelMapper, times(1)).map(enderecoDTO, Endereco.class);
        verify(modelMapper, times(1)).map(endereco, EnderecoDTO.class);
    }

    @Test
    void putEndereco_ShouldUpdateAndReturnEnderecoDTO() {

        Long id = 1L;
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        Endereco endereco = new Endereco();
        when(modelMapper.map(enderecoDTO, Endereco.class)).thenReturn(endereco);
        when(repository.save(endereco)).thenReturn(endereco);
        when(modelMapper.map(endereco, EnderecoDTO.class)).thenReturn(enderecoDTO);


        EnderecoDTO result = enderecoService.putEndereco(id, enderecoDTO);


        assertThat(result).isNotNull();
        assertThat(endereco.getId()).isEqualTo(id);
        verify(repository, times(1)).save(endereco);
        verify(modelMapper, times(1)).map(enderecoDTO, Endereco.class);
        verify(modelMapper, times(1)).map(endereco, EnderecoDTO.class);
    }

    @Test
    void deleteEndereco_ShouldCallRepositoryDeleteById() {

        Long id = 1L;


        enderecoService.deleteEndereco(id);


        verify(repository, times(1)).deleteById(id);
    }
}
