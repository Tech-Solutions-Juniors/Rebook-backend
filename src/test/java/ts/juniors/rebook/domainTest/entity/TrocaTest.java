package ts.juniors.rebook.domainTest.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ts.juniors.rebook.domain.entity.Livro;
import ts.juniors.rebook.domain.entity.Troca;
import ts.juniors.rebook.domain.enums.Status;

import java.time.LocalDateTime;

public class TrocaTest {

    public Troca troca;
    public Livro livro1;
    public Livro livro2;

    @BeforeEach
    public void setUp(){
        livro1 = new Livro();
        livro2 = new Livro();

        troca = new Troca();
        troca.setId(5L);
        troca.setLivro1(livro1);
        troca.setLivro2(livro2);
        troca.setStatus(Status.CONCLUIDO);
        troca.setData(LocalDateTime.of(2023,3,25,6,55));

    }
    @Test
    public void SalvarTroca(){
        Assertions.assertEquals(5L,troca.getId());
        Assertions.assertNotNull(troca.getLivro1());
        Assertions.assertNotNull(troca.getLivro2());
        Assertions.assertEquals(LocalDateTime.of(2023,3,25,6,55),troca.getData());
    }

}
