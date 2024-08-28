package ts.juniors.rebook.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class UsuarioDto {

    private long id;
    private String nome;
    private String email;
    private List<LivroDto> livros;
}
