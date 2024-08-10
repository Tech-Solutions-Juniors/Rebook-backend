package ts.juniors.rebook.service;

import ts.juniors.rebook.model.Endereco;

import java.util.List;

public interface EnderecoServiceInterface {
    Endereco save(Endereco endereco);
    Endereco findById(Long id);
    List<Endereco> findAll();
    Endereco update(Long id, Endereco newEndereco);
    void deleteById(Long id);
    List<Endereco> findByCidade(String cidade);
    List<Endereco> findByEstado(String estado);

}
