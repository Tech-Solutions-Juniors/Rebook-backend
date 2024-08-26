package ts.juniors.rebook.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import ts.juniors.rebook.enums.Estados;
import ts.juniors.rebook.enums.Generos;

import java.util.Set;

public class LivroTest {

    @Test
    public void testLivroCreation() {
        Livro livro = new Livro(
                1L,
                "Título do Livro",
                "Descrição do Livro",
                List.of(Generos.FICCAO, Generos.ROMANCE),
                List.of(Estados.NOVO),
                Set.of("imagem1.jpg", "imagem2.jpg"),
                "Autor do Livro",
                new BigDecimal("29.99"),
                new Usuario()
        );

        assertNotNull(livro);
        assertEquals(1L, livro.getId());
        assertEquals("Título do Livro", livro.getTitulo());
        assertEquals("Descrição do Livro", livro.getDescricao());
        assertEquals(2, livro.getGeneros().size());
        assertEquals(1, livro.getEstados().size());
        assertEquals(2, livro.getImagemUrls().size());
        assertEquals("Autor do Livro", livro.getAutor());
        assertEquals(new BigDecimal("29.99"), livro.getPreco());
        assertNotNull(livro.getUsuario());
    }
}



