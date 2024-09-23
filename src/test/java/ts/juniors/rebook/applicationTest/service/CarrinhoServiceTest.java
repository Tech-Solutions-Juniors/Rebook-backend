package ts.juniors.rebook.applicationTest.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ts.juniors.rebook.domain.entity.Carrinho;
import ts.juniors.rebook.domain.entity.Livro;
import ts.juniors.rebook.domain.entity.Usuario;
import ts.juniors.rebook.domain.repository.CarrinhoRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
public class CarrinhoServiceTest {
    @MockBean
    public CarrinhoRepository repository;

    public Carrinho carrinho;
    public Livro livro;
    public Usuario usuario;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        carrinho = new Carrinho();

        carrinho.setId(5L);
        carrinho.getLivros().add(livro);
        carrinho.setUsuario(usuario);

    }

    @Test
    public void SalvarTransacaoNoBanco(){
        when(repository.save(any(Carrinho.class))).thenReturn(carrinho);

        Carrinho CarrinhoSalvo = repository.save(carrinho);

        Assertions.assertNotNull(CarrinhoSalvo);
        Assertions.assertEquals(CarrinhoSalvo,carrinho);
        Assertions.assertEquals(5L,CarrinhoSalvo.getId());
        verify(repository,times(1)).save(CarrinhoSalvo);
    }

    @Test
    public void TestBuscarTransacaoPorId() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(carrinho));
        Optional<Carrinho> buscaCarrinho = repository.findById(5L);

        Assertions.assertTrue(buscaCarrinho.isPresent());
        Assertions.assertEquals(5L,buscaCarrinho.get().getId());
        verify(repository,times(1)).findById(buscaCarrinho.get().getId());
    }

    @Test
    public void TestAlterarTransacaoDoBanco(){
        Carrinho alterarCarrinho = new Carrinho();

        alterarCarrinho.setId(8L);


        when(repository.save(any(Carrinho.class))).thenReturn(alterarCarrinho);
        Carrinho CarrinhoSalvo = repository.save(alterarCarrinho);

        Assertions.assertNotNull(CarrinhoSalvo);
        Assertions.assertEquals(8L,CarrinhoSalvo.getId());

    }

    @Test
    public void TestDeletarTransacaoDoBanco(){
        doNothing().when(repository).deleteById(carrinho.getId());

        repository.deleteById(carrinho.getId());

        verify(repository,times(1)).deleteById(carrinho.getId());
    }


}
