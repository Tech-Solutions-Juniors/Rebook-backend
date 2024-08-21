package ts.juniors.rebook.repository;

import ts.juniors.rebook.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}
