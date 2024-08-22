package ts.juniors.rebook.model;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;

public class UsuarioTest {

    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        usuario = new Usuario();
        usuario.setNome("João da Silva");
        usuario.setEmail("joao.silva@example.com");
        usuario.setSenha("senha123");
    }

    @Test
    public void testUsuarioCreation() {

        assertThat(usuario.getNome()).isEqualTo("João da Silva");
        assertThat(usuario.getEmail()).isEqualTo("joao.silva@example.com");
        assertThat(usuario.getSenha()).isEqualTo("senha123");
    }

    @Test
    public void testUsuarioLivrosList() {
        // Inicializa e adiciona livros ao usuário
        Livro livro1 = new Livro();
        livro1.setTitulo("Livro 1");
        Livro livro2 = new Livro();
        livro2.setTitulo("Livro 2");

        List<Livro> livros = new ArrayList<>();
        livros.add(livro1);
        livros.add(livro2);

        usuario.setLivros(livros);


        assertThat(usuario.getLivros()).hasSize(2);
        assertThat(usuario.getLivros()).containsExactlyInAnyOrder(livro1, livro2);
    }
}
