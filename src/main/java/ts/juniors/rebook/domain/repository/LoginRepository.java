package ts.juniors.rebook.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ts.juniors.rebook.domain.entity.Login;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, Long> {
    Login findByEmail(String email);
}
