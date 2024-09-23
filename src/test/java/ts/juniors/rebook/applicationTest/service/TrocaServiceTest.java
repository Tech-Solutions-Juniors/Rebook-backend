package ts.juniors.rebook.applicationTest.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ts.juniors.rebook.domain.entity.Livro;
import ts.juniors.rebook.domain.entity.Troca;
import ts.juniors.rebook.domain.enums.Status;
import ts.juniors.rebook.domain.repository.TrocaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
public class TrocaServiceTest {

    @MockBean
    public TrocaRepository repository;

    public Troca troca;
    public Livro livro1;
    public Livro livro2;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
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
    public void SalvarTrocaNoBanco(){
        when(repository.save(any(Troca.class))).thenReturn(troca);
        Troca trocasalva = repository.save(troca);

        Assertions.assertNotNull(trocasalva);
        Assertions.assertEquals(trocasalva,troca);
        Assertions.assertEquals(5L,trocasalva.getId());
        Assertions.assertEquals(LocalDateTime.of(2023,3,25,6,55),trocasalva.getData());
        verify(repository,times(1)).save(trocasalva);
    }

    @Test
    public void TestBuscarTrocaPorId() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(troca));
        Optional<Troca> buscaTroca = repository.findById(5L);

        Assertions.assertTrue(buscaTroca.isPresent());
        Assertions.assertEquals(5L,buscaTroca.get().getId());
        verify(repository,times(1)).findById(buscaTroca.get().getId());
    }

    @Test
    public void TestAlterarDadosDoBanco(){
        Troca trocaAlterada = new Troca();

        trocaAlterada.setId(5L);
        trocaAlterada.setStatus(Status.PENDENTE);
        trocaAlterada.setData(LocalDateTime.of(2023,3,27,12,55));

        when(repository.save(any(Troca.class))).thenReturn(trocaAlterada);

        Troca trocasalva = repository.save(trocaAlterada);

        Assertions.assertNotNull(trocasalva);
        Assertions.assertEquals(Status.PENDENTE,trocasalva.getStatus());
        Assertions.assertEquals(5L,trocasalva.getId());

    }

    @Test
    public void TestDeletarDadosDoBando(){

        doNothing().when(repository).deleteById(troca.getId());

        repository.deleteById(troca.getId());

        verify(repository,times(1)).deleteById(troca.getId());
    }

}
