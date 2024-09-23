package ts.juniors.rebook.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CarrinhoDto {
    private long id;
    private Set<LivroDto> livros;
    private UsuarioDto usuario;
}
