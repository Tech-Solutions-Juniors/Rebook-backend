package ts.juniors.rebook.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;
import java.util.Collections;

public class UsuarioTest {

    @Test
    public void testUsuarioCreation() {
        Usuario usuario = new Usuario(
                1L,
                "Nome do Usuário",
                "email@exemplo.com",
                "senha123",
                new HashSet<>(),
                new HashSet<>()
        );

        assertNotNull(usuario);
        assertEquals(1L, usuario.getId());
        assertEquals("Nome do Usuário", usuario.getNome());
        assertEquals("email@exemplo.com", usuario.getEmail());
        assertEquals("senha123", usuario.getSenha());
        assertTrue(usuario.getLivros().isEmpty());
        assertTrue(usuario.getEnderecos().isEmpty());
    }

    @Test
    public void testUsuarioAuthorities() {
        Usuario usuario = new Usuario();
        assertEquals(1, usuario.getAuthorities().size());
        assertTrue(usuario.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }

    @Test
    public void testUsuarioDetails() {
        Usuario usuario = new Usuario();
        usuario.setEmail("email@exemplo.com");
        usuario.setSenha("senha123");

        assertEquals("email@exemplo.com", usuario.getUsername());
        assertEquals("senha123", usuario.getPassword());
        assertTrue(usuario.isAccountNonExpired());
        assertTrue(usuario.isAccountNonLocked());
        assertTrue(usuario.isCredentialsNonExpired());
        assertTrue(usuario.isEnabled());
    }
}


