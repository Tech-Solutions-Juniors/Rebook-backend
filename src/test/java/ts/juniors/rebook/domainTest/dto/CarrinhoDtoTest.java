package ts.juniors.rebook.domainTest.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ts.juniors.rebook.domain.dto.CarrinhoDto;
import ts.juniors.rebook.domain.dto.LivroDto;
import ts.juniors.rebook.domain.dto.UsuarioDto;

import java.util.HashSet;

public class CarrinhoDtoTest {

    public UsuarioDto usuarioDto;
    public LivroDto livroDto;
    public CarrinhoDto carrinhoDto;

    @BeforeEach
    public void setUp(){
        usuarioDto = new UsuarioDto();
        livroDto = new LivroDto();
        carrinhoDto = new CarrinhoDto();

        carrinhoDto.setId(1L);
        carrinhoDto.setLivros(new HashSet<>());
        carrinhoDto.getLivros().add(livroDto);
        carrinhoDto.setUsuario(usuarioDto);
    }

    @Test
    public void CarrinhoDtoUP(){
        Assertions.assertEquals(1L,carrinhoDto.getId());
    }

    @Test
    public void CarrinhoDtoLivroDto(){
        Assertions.assertNotNull(carrinhoDto.getLivros());
    }

    @Test
    public void CarrinhoDtoUsuarioDto(){
        Assertions.assertNotNull(carrinhoDto.getUsuario());
    }

}
