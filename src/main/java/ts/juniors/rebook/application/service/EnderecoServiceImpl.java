package ts.juniors.rebook.application.service;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ts.juniors.rebook.domain.dto.EnderecoDto;
import ts.juniors.rebook.domain.entity.Endereco;

import ts.juniors.rebook.domain.repository.EnderecoReposiotry;
import ts.juniors.rebook.domain.service.EnderecoService;


import java.util.List;

@Service
@RequiredArgsConstructor
public class EnderecoServiceImpl implements EnderecoService {


    private final EnderecoReposiotry repository;

    private final ModelMapper modelMapper;

    @Override
    public Page<EnderecoDto> getTodosEnderecos(Pageable paginacao) {
        return repository
                .findAll(paginacao)
                .map(p -> modelMapper.map(p, EnderecoDto.class));
    }

    @Override
    public EnderecoDto getPorId(Long id) {
        Endereco endereco = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        return modelMapper.map(endereco, EnderecoDto.class);
    }

    @Override
    public List<Endereco> getPorCidade(String cidade) {
        return repository.findByCidade(cidade);
    }

    @Override
    public List<Endereco> getPorEstado(String estado) {
        return repository.findByEstado(estado);
    }

    @Override
    public EnderecoDto postEndereco(EnderecoDto dto) {
        Endereco endereco = modelMapper.map(dto, Endereco.class);
        repository.save(endereco);
        return modelMapper.map(endereco, EnderecoDto.class);
    }

    @Override
    public EnderecoDto putEndereco(Long id, EnderecoDto dto) {
        Endereco endereco = modelMapper.map(dto, Endereco.class);
        endereco.setId(id);
        endereco = repository.save(endereco);
        return modelMapper.map(endereco, EnderecoDto.class);
    }

    @Override
    public void deleteEndereco(Long id) {
        repository.deleteById(id);
    }
}
