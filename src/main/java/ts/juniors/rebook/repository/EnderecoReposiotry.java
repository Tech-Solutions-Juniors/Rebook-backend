package ts.juniors.rebook.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ts.juniors.rebook.model.Endereco;

import java.util.List;

public interface EnderecoReposiotry extends JpaRepository<Endereco, Long> {
    List<Endereco> findByCidade(String cidade);
    List<Endereco> findByEstado(String estado);
}
