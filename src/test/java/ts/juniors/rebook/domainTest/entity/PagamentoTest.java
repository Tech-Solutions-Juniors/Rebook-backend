package ts.juniors.rebook.domainTest.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ts.juniors.rebook.domain.entity.Pagamento;
import ts.juniors.rebook.domain.entity.Transacao;
import ts.juniors.rebook.domain.enums.FormaPagamento;

import java.time.LocalDateTime;

public class PagamentoTest {

    public Pagamento pagamento;
    public Transacao transacao;

    @BeforeEach
    public void setUp(){
        pagamento = new Pagamento();
        transacao = new Transacao();

        pagamento.setId(7l);
        pagamento.setTransacao(transacao);
        pagamento.setFormaPagamento(FormaPagamento.DEBITO);
        pagamento.setValor(55.78F);
        pagamento.setData(LocalDateTime.of(2023,3,25,6,55));
        pagamento.setDataConfirmacao(LocalDateTime.of(2023,3,23,12,0));

    }

    @Test
    public void SalvarPagamento(){
        Assertions.assertEquals(7l,pagamento.getId());
        Assertions.assertEquals(55.78F,pagamento.getValor());
        Assertions.assertEquals(FormaPagamento.DEBITO,pagamento.getFormaPagamento());
        Assertions.assertEquals(LocalDateTime.of(2023,3,25,6,55),pagamento.getData());
        Assertions.assertEquals(LocalDateTime.of(2023,3,23,12,0),pagamento.getDataConfirmacao());
    }

}
