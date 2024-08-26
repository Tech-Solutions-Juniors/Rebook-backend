package ts.juniors.rebook.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioInsertDtoTest {

    @Test
    public void testUsuarioInsertDtoCreation() {
        UsuarioInsertDto usuarioInsertDto = new UsuarioInsertDto();
        usuarioInsertDto.setNome("Nome do Usuário");
        usuarioInsertDto.setEmail("email@exemplo.com");
        usuarioInsertDto.setSenha("senha123");

        assertNotNull(usuarioInsertDto);
        assertEquals("Nome do Usuário", usuarioInsertDto.getNome());
        assertEquals("email@exemplo.com", usuarioInsertDto.getEmail());
        assertEquals("senha123", usuarioInsertDto.getSenha());
    }

    @Test
    public void testUsuarioInsertDtoInheritance() {
        UsuarioInsertDto usuarioInsertDto = new UsuarioInsertDto();
        usuarioInsertDto.setNome("Nome do Usuário");
        usuarioInsertDto.setEmail("email@exemplo.com");
        usuarioInsertDto.setSenha("senha123");

        assertTrue(usuarioInsertDto instanceof UsuarioDto);
    }
}
