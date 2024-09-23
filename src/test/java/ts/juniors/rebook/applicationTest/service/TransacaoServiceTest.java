package ts.juniors.rebook.applicationTest.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ts.juniors.rebook.domain.entity.Livro;
import ts.juniors.rebook.domain.entity.Pagamento;
import ts.juniors.rebook.domain.entity.Transacao;
import ts.juniors.rebook.domain.entity.Usuario;
import ts.juniors.rebook.domain.enums.Status;
import ts.juniors.rebook.domain.repository.TransacaoRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
public class TransacaoServiceTest {
    @MockBean
    public TransacaoRepository repository;


    public Transacao transacao;
    public Livro livro;
    public Usuario usuario;
    public Pagamento pagamento;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        transacao = new Transacao();
        livro = new Livro();
        usuario = new Usuario();
        pagamento = new Pagamento();

        transacao.setId(5L);
        transacao.setUsuario(usuario);
        transacao.setLivro(livro);
        transacao.setPagamento(pagamento);
        transacao.setStatus(Status.CONCLUIDO);
        transacao.setData(LocalDateTime.of(2023,3,25,6,55));
    }

    @Test
    public void SalvarTransacaoNoBanco(){
        when(repository.save(any(Transacao.class))).thenReturn(transacao);

        Transacao TransacaoSalvo = repository.save(transacao);

        Assertions.assertNotNull(TransacaoSalvo);
        Assertions.assertEquals(TransacaoSalvo,transacao);
        Assertions.assertEquals(5L,TransacaoSalvo.getId());
        Assertions.assertEquals(LocalDateTime.of(2023,3,25,6,55),TransacaoSalvo.getData());
        verify(repository,times(1)).save(TransacaoSalvo);
    }

    @Test
    public void TestBuscarTransacaoPorId() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(transacao));
        Optional<Transacao> buscatransacao = repository.findById(5L);

        Assertions.assertTrue(buscatransacao.isPresent());
        Assertions.assertEquals(5L,buscatransacao.get().getId());
        verify(repository,times(1)).findById(buscatransacao.get().getId());
    }

    @Test
    public void TestAlterarTransacaoDoBanco(){
        Transacao pagamentoTansacao = new Transacao();

        pagamentoTansacao.setId(5L);
        pagamentoTansacao.setStatus(Status.PENDENTE);
        pagamentoTansacao.setData(LocalDateTime.of(2023,3,27,12,55));

        when(repository.save(any(Transacao.class))).thenReturn(pagamentoTansacao);

        Transacao TransacaoSalvo = repository.save(pagamentoTansacao);

        Assertions.assertNotNull(TransacaoSalvo);
        Assertions.assertEquals(Status.PENDENTE,TransacaoSalvo.getStatus());
        Assertions.assertEquals(5L,TransacaoSalvo.getId());

    }

    @Test
    public void TestDeletarTransacaoDoBanco(){
        doNothing().when(repository).deleteById(transacao.getId());

        repository.deleteById(transacao.getId());

        verify(repository,times(1)).deleteById(transacao.getId());
    }

}
