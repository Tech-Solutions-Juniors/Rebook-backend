package ts.juniors.rebook.applicationTest.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ts.juniors.rebook.domain.entity.Pagamento;
import ts.juniors.rebook.domain.entity.Transacao;
import ts.juniors.rebook.domain.enums.Status;
import ts.juniors.rebook.domain.repository.PagamentoRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
public class PagamentoServiceTest {

    @MockBean
    public PagamentoRepository repository;

    public Pagamento pagamento;
    public Transacao transacao;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        pagamento = new Pagamento();
        transacao = new Transacao();

        pagamento.setId(5L);
        pagamento.setStatus(Status.CONCLUIDO);
        pagamento.setTransacao(transacao);
        pagamento.setData(LocalDateTime.of(2023,3,25,6,55));
        pagamento.setDataConfirmacao(LocalDateTime.of(2023,3,22,12,30));
    }

    @Test
    public void SalvarPagamentoNoBanco(){
        when(repository.save(any(Pagamento.class))).thenReturn(pagamento);
        Pagamento pagamentoSalvo = repository.save(pagamento);

        Assertions.assertNotNull(pagamentoSalvo);
        Assertions.assertEquals(pagamentoSalvo,pagamento);
        Assertions.assertEquals(5L,pagamentoSalvo.getId());
        Assertions.assertEquals(LocalDateTime.of(2023,3,25,6,55),pagamentoSalvo.getData());
        Assertions.assertEquals(LocalDateTime.of(2023,3,22,12,30),pagamentoSalvo.getDataConfirmacao());
        verify(repository,times(1)).save(pagamentoSalvo);
    }

    @Test
    public void TestBuscarPagamentoPorId() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(pagamento));
        Optional<Pagamento> buscaPagamento = repository.findById(5L);

        Assertions.assertTrue(buscaPagamento.isPresent());
        Assertions.assertEquals(5L,buscaPagamento.get().getId());
        verify(repository,times(1)).findById(buscaPagamento.get().getId());
    }

    @Test
    public void TestAlterarPagamentoDoBanco(){
        Pagamento pagamentoAlterado = new Pagamento();

        pagamentoAlterado.setId(5L);
        pagamentoAlterado.setStatus(Status.PENDENTE);
        pagamentoAlterado.setData(LocalDateTime.of(2023,3,27,12,55));

        when(repository.save(any(Pagamento.class))).thenReturn(pagamentoAlterado);

        Pagamento PagamentoSalvo = repository.save(pagamentoAlterado);

        Assertions.assertNotNull(PagamentoSalvo);
        Assertions.assertEquals(Status.PENDENTE,PagamentoSalvo.getStatus());
        Assertions.assertEquals(5L,PagamentoSalvo.getId());

    }

    @Test
    public void TestDeletarPagamentoDoBanco(){
        doNothing().when(repository).deleteById(pagamento.getId());

        repository.deleteById(pagamento.getId());

        verify(repository,times(1)).deleteById(pagamento.getId());
    }


}
