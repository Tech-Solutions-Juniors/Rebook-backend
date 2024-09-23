package ts.juniors.rebook.domainTest.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ts.juniors.rebook.domain.dto.LivroDto;
import ts.juniors.rebook.domain.dto.PagamentoDto;
import ts.juniors.rebook.domain.dto.TransacaoDto;
import ts.juniors.rebook.domain.dto.UsuarioDto;
import ts.juniors.rebook.domain.enums.FormaPagamento;
import ts.juniors.rebook.domain.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class TransacaoDtoTest {

    private TransacaoDto transacaoDto;
    private LivroDto livroDto;
    private UsuarioDto usuarioDto;
    private PagamentoDto pagamentoDto;

    @BeforeEach
    public void setUp(){
        livroDto = new LivroDto();
        transacaoDto = new TransacaoDto();
        pagamentoDto = new PagamentoDto();
        usuarioDto = new UsuarioDto();

        livroDto.setId(1L);
        livroDto.setTitulo("livro 1");
        livroDto.setPreco(BigDecimal.valueOf(88));

        usuarioDto.setId(1L);
        usuarioDto.setNome("user1");
        usuarioDto.setEmail("email@email.com");
        usuarioDto.setLivros(List.of(livroDto));
        usuarioDto.setEnderecos(new ArrayList<>());
        usuarioDto.setTransacao(List.of(transacaoDto));

        pagamentoDto.setId(1L);
        pagamentoDto.setValor(55.30F);
        pagamentoDto.setFormaPagamento(FormaPagamento.BOLETO);
        pagamentoDto.setTransacao(transacaoDto);
        pagamentoDto.setStatus(Status.CONCLUIDO);
        pagamentoDto.setData(LocalDateTime.now());
        pagamentoDto.setDataConfirmacao(LocalDateTime.of(2023,3,25,6,55));

        //Transacao- SetUp
        transacaoDto.setId(1L);
        transacaoDto.setLivro(livroDto);
        transacaoDto.setUsuario(usuarioDto);
        transacaoDto.setPagamentoDto(pagamentoDto);
        transacaoDto.setStatus(Status.CANCELADO);
        transacaoDto.setData(LocalDateTime.of(2023,3,25,6,55));

    }

    @Test
    public void TransacaoUp(){
        Assertions.assertEquals(1L,transacaoDto.getId());
        Assertions.assertEquals(Status.CANCELADO,transacaoDto.getStatus());
        Assertions.assertEquals(LocalDateTime.of(2023,3,25,6,55),transacaoDto.getData());
    }

    @Test
    public void TransacaoUsuario(){
        Assertions.assertNotNull(transacaoDto.getUsuario());
    }

    @Test
    public void TrasnsacaoLivros(){
        Assertions.assertNotNull(transacaoDto.getLivro());
    }

    @Test
    public void TransacaoPagamento(){
        Assertions.assertNotNull(transacaoDto.getPagamentoDto());
    }

}
