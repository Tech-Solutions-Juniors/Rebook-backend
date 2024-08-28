package ts.juniors.rebook.domain.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ts.juniors.rebook.domain.entity.Endereco;

import java.util.List;

public interface EnderecoReposiotry extends JpaRepository<Endereco, Long> {
    List<Endereco> findByCidade(String cidade);
    List<Endereco> findByEstado(String estado);
}
