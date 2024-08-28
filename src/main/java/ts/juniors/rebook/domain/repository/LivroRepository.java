package ts.juniors.rebook.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ts.juniors.rebook.domain.entity.Livro;


public interface LivroRepository extends JpaRepository<Livro, Long> {
}
