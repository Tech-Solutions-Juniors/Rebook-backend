package ts.juniors.rebook.domainTest.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ts.juniors.rebook.domain.dto.LivroDto;
import ts.juniors.rebook.domain.dto.PagamentoDto;
import ts.juniors.rebook.domain.dto.TransacaoDto;
import ts.juniors.rebook.domain.enums.FormaPagamento;
import ts.juniors.rebook.domain.enums.Status;

import java.time.LocalDateTime;

@SpringBootTest
public class PagamentoDtoTest {

    private TransacaoDto transacaoDto;
    private PagamentoDto pagamentoDto;
    private LivroDto livroDto;

    @BeforeEach
    public void setUp(){
        transacaoDto = new TransacaoDto();
        pagamentoDto = new PagamentoDto();
        livroDto = new LivroDto();

        livroDto.setUsuarioId(1L);
        livroDto.setTitulo("livro 1");


        //Transacao- SetUp
        transacaoDto.setId(1L);
        transacaoDto.setLivro(livroDto);

        //Pagamento- SetUp
        pagamentoDto.setId(1L);
        pagamentoDto.setValor(55.30F);
        pagamentoDto.setFormaPagamento(FormaPagamento.BOLETO);
        pagamentoDto.setTransacao(transacaoDto);
        pagamentoDto.setStatus(Status.CONCLUIDO);
        pagamentoDto.setData(LocalDateTime.now());
        pagamentoDto.setDataConfirmacao(LocalDateTime.now());

    }
    @Test
    public void PagamentoUP(){
        Assertions.assertEquals(1L,pagamentoDto.getId());
        Assertions.assertEquals(55.30f,pagamentoDto.getValor());
        Assertions.assertEquals(Status.CONCLUIDO,pagamentoDto.getStatus());
    }

    @Test
    public void PagamentoTransacao(){
        Assertions.assertNotNull(pagamentoDto.getTransacao());
    }





}
