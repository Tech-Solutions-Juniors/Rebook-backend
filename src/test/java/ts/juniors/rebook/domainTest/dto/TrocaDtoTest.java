package ts.juniors.rebook.domainTest.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ts.juniors.rebook.domain.dto.LivroDto;
import ts.juniors.rebook.domain.dto.TrocaDto;
import ts.juniors.rebook.domain.enums.Status;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class TrocaDtoTest {

    private TrocaDto trocaDto;
    private LivroDto livro1;
    private LivroDto livro2;

    @BeforeEach
    public void setUp() {
        // Criando objetos LivroDto para teste
        livro1 = new LivroDto();
        livro1.setId(1L);
        livro1.setTitulo("Livro 1");

        livro2 = new LivroDto();
        livro2.setId(2L);
        livro2.setTitulo("Livro 2");

        trocaDto = new TrocaDto();
        trocaDto.setId(100L);
        trocaDto.setLivro1(livro1);
        trocaDto.setLivro2(livro2);
        trocaDto.setStatus(Status.PENDENTE);
        trocaDto.setData(LocalDateTime.of(2024, 9, 16, 12, 0));
    }

    @Test
    public void testTrocaDtoFields() {
        assertEquals(100L, trocaDto.getId());
        assertEquals(livro1, trocaDto.getLivro1());
        assertEquals(livro2, trocaDto.getLivro2());
        assertEquals(Status.PENDENTE, trocaDto.getStatus());
        assertEquals(LocalDateTime.of(2024, 9, 16, 12, 0), trocaDto.getData());
    }

    @Test
    public void testLivro1NotNull() {
        assertNotNull(trocaDto.getLivro1());
    }

    @Test
    public void testLivro2NotNull() {
        assertNotNull(trocaDto.getLivro2());
    }

    @Test
    public void testStatusNotNull() {
        assertNotNull(trocaDto.getStatus());
    }

    @Test
    public void testDataNotNull() {
        assertNotNull(trocaDto.getData());
    }
}

