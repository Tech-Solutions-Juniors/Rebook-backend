package ts.juniors.rebook.domainTest.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ts.juniors.rebook.domain.entity.Carrinho;
import ts.juniors.rebook.domain.entity.Livro;
import ts.juniors.rebook.domain.entity.Usuario;

public class CarrinhoTest {

    public Carrinho carrinho;

    @BeforeEach
    public void setUP(){
        carrinho = new Carrinho();
        Livro livro = new Livro();
        Usuario usuario = new Usuario();

        carrinho.setId(1L);
        carrinho.setUsuario(usuario);
        carrinho.getLivros().add(livro);

    }

    @Test
    public void CarrinhoUP(){
        Assertions.assertEquals(1L,carrinho.getId());
    }

    @Test
    public void CarrinhoLivro(){
        Assertions.assertNotNull(carrinho.getLivros());
    }

    @Test
    public void CarrinhoUruario(){
        Assertions.assertNotNull(carrinho.getUsuario());
    }



}
