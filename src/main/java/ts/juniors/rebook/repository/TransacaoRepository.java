package ts.juniors.rebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ts.juniors.rebook.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
