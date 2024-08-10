package ts.juniors.rebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ts.juniors.rebook.model.Livro;


public interface LivroRepository extends JpaRepository<Livro, Long> {
}
