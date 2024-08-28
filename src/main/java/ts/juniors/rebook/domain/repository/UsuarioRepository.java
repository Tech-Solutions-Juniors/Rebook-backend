package ts.juniors.rebook.domain.repository;

import ts.juniors.rebook.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}
