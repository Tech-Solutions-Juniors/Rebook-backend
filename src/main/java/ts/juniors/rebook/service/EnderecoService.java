package ts.juniors.rebook.service;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ts.juniors.rebook.dto.EnderecoDTO;
import ts.juniors.rebook.model.Endereco;

import ts.juniors.rebook.repository.EnderecoReposiotry;


import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoReposiotry repository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<EnderecoDTO> getTodosEnderecos(Pageable paginacao) {
        return repository
                .findAll(paginacao)
                .map(p -> modelMapper.map(p, EnderecoDTO.class));
    }

    public EnderecoDTO getPorId(Long id) {
        Endereco endereco = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        return modelMapper.map(endereco, EnderecoDTO.class);
    }


    public List<Endereco> getPorCidade(String cidade) {
        return repository.findByCidade(cidade);
    }


    public List<Endereco> getPorEstado(String estado) {
        return repository.findByEstado(estado);
    }


    public EnderecoDTO postEndereco(EnderecoDTO dto) {
        Endereco endereco = modelMapper.map(dto, Endereco.class);
        repository.save(endereco);
        return modelMapper.map(endereco, EnderecoDTO.class);
    }

    public EnderecoDTO putEndereco(Long id, EnderecoDTO dto) {
        Endereco endereco = modelMapper.map(dto, Endereco.class);
        endereco.setId(id);
        endereco = repository.save(endereco);
        return modelMapper.map(endereco, EnderecoDTO.class);
    }

    public void deleteEndereco(Long id) {
        repository.deleteById(id);
    }
}
