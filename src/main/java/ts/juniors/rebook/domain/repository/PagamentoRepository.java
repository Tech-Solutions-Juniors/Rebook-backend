package ts.juniors.rebook.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ts.juniors.rebook.domain.entity.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento,Long> {

}
