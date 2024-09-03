package ts.juniors.rebook.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ts.juniors.rebook.domain.entity.Pagamento;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento,Long> {

}
