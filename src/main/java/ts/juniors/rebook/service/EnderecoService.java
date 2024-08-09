package ts.juniors.rebook.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ts.juniors.rebook.model.Endereco;
import ts.juniors.rebook.repository.EnderecoReposiotry;

import java.util.List;

@Service
public class EnderecoService implements EnderecoServiceInterface {
    @Autowired
    private EnderecoReposiotry enderecoRepository;
    private Long id;
    private Endereco newEndereco;

    @Override
    public Endereco save(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    @Override
    public Endereco findById(Long id) {
        return enderecoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }

    @Override
    public Endereco update(Long id, Endereco newEndereco) {
        return enderecoRepository.findById(id)
                .map(endereco -> {
                    atualizarEndereco(endereco, newEndereco);
                    return enderecoRepository.save(endereco);
                }).orElse(null);
    }

    private void atualizarEndereco(Endereco enderecoExistente, Endereco novoEndereco) {
        enderecoExistente.setRua(novoEndereco.getRua());
        enderecoExistente.setCidade(novoEndereco.getCidade());
        enderecoExistente.setEstado(novoEndereco.getEstado());
        enderecoExistente.setNumero(novoEndereco.getNumero());
        enderecoExistente.setCep(novoEndereco.getCep());
    }

    @Override
    public void deleteById(Long id) {
        enderecoRepository.deleteById(id);
    }

    @Override
    public List<Endereco> findByCidade(String cidade) {
        return enderecoRepository.findByCidade(cidade);
    }

    @Override
    public List<Endereco> findByEstado(String estado) {
        return enderecoRepository.findByEstado(estado);
    }



}
