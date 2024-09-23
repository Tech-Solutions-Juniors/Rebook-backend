package ts.juniors.rebook.domainTest.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ts.juniors.rebook.domain.entity.*;
import ts.juniors.rebook.domain.enums.Status;

import java.time.LocalDateTime;

public class TransacaoTest {

    public Transacao transacao;
    public Livro livro;
    public Usuario usuario;
    public Pagamento pagamento;

    @BeforeEach
    public void setUp(){
        transacao = new Transacao();
        livro = new Livro();
        usuario = new Usuario();
        pagamento = new Pagamento();

        transacao.setId(6L);
        transacao.setUsuario(usuario);
        transacao.setLivro(livro);
        transacao.setStatus(Status.PENDENTE);
        transacao.setData(LocalDateTime.of(2023,3,25,6,55));
        transacao.setPagamento(pagamento);
    }

    @Test
    public void SalvarTransacao(){
        Assertions.assertEquals(6L,transacao.getId());
        Assertions.assertEquals(Status.PENDENTE,transacao.getStatus());
        Assertions.assertEquals(LocalDateTime.of(2023,3,25,6,55),transacao.getData());

        Assertions.assertNotNull(transacao.getLivro());
        Assertions.assertNotNull(transacao.getUsuario());
        Assertions.assertNotNull(transacao.getPagamento());
    }

}
