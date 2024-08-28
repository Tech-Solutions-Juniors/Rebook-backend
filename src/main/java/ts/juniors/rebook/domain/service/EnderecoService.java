package ts.juniors.rebook.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ts.juniors.rebook.domain.dto.EnderecoDto;
import ts.juniors.rebook.domain.entity.Endereco;

import java.util.List;

public interface EnderecoService {
    Page<EnderecoDto> getTodosEnderecos(Pageable paginacao);
    EnderecoDto getPorId(Long id);
    List<Endereco> getPorCidade(String cidade);
    List<Endereco> getPorEstado(String estado);
    EnderecoDto postEndereco(EnderecoDto dto);
    EnderecoDto putEndereco(Long id, EnderecoDto dto);
    void deleteEndereco(Long id);
}

