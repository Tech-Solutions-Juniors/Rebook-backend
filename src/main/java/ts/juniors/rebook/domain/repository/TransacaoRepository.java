package ts.juniors.rebook.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ts.juniors.rebook.domain.entity.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
