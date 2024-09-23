package ts.juniors.rebook.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ts.juniors.rebook.domain.entity.Carrinho;

public interface CarrinhoRepository extends JpaRepository<Carrinho,Long> {
}
