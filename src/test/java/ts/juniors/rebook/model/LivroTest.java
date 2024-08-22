package ts.juniors.rebook.model;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ts.juniors.rebook.enums.Estados;
import ts.juniors.rebook.enums.Generos;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LivroTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidLivro() {
        Livro livro = new Livro(
                1L,
                "Título do Livro",
                "Descrição do livro.",
                Arrays.asList(Generos.FICCAO, Generos.MISTERIO),
                Arrays.asList(Estados.NOVO),
                Arrays.asList("http://example.com/imagem1.jpg"),
                "Autor do Livro",
                null
        );

        var violations = validator.validate(livro);

        assertTrue(violations.isEmpty());
    }

    @Test
    void testLivroWithInvalidFields() {
        Livro livro = new Livro(
                1L,
                "",
                "",
                Arrays.asList(Generos.FICCAO),
                Arrays.asList(Estados.NOVO),
                Arrays.asList("http://example.com/imagem1.jpg", "http://example.com/imagem2.jpg", "http://example.com/imagem3.jpg", "http://example.com/imagem4.jpg"),
                "",
                null
        );

        var violations = validator.validate(livro);

        assertFalse(violations.isEmpty());
        assertEquals(4, violations.size());
    }

    @Test
    void testLivroWithTooManyGeneros() {
        Livro livro = new Livro(
                1L,
                "Título do Livro",
                "Descrição do livro.",
                Arrays.asList(
                        Generos.FICCAO, Generos.FANTASIA, Generos.ROMANCE,
                        Generos.MISTERIO, Generos.TERROR, Generos.BIOGRAFIA,
                        Generos.HISTORIA, Generos.CIENCIA, Generos.RELIGIOSO,
                        Generos.SUSPENSE, Generos.COMICO, Generos.FANTASIA_HISTORICA
                ),
                Arrays.asList(Estados.NOVO),
                Arrays.asList("http://example.com/imagem1.jpg"),
                "Autor do Livro",
                null
        );

        var violations = validator.validate(livro);

        assertFalse(violations.isEmpty());
    }

    @Test
    void testLivroWithTooManyImagens() {
        Livro livro = new Livro(
                1L,
                "Título do Livro",
                "Descrição do livro.",
                Arrays.asList(Generos.FICCAO),
                Arrays.asList(Estados.NOVO),
                Arrays.asList(
                        "http://example.com/imagem1.jpg", "http://example.com/imagem2.jpg",
                        "http://example.com/imagem3.jpg", "http://example.com/imagem4.jpg"
                ),
                "Autor do Livro",
                null
        );

        var violations = validator.validate(livro);

        assertFalse(violations.isEmpty());
    }
}
