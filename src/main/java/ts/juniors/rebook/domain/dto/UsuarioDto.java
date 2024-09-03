package ts.juniors.rebook.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class UsuarioDto {

    private long id;

    @NotBlank(message = "Insira um nome")
    private String nome;

    @NotBlank(message = "Favor entrar um email válido")
    @Email(message = "Favor entrar um email válido")
    private String email;

    private List<LivroDto> livros;

    private List<EnderecoDto> enderecos;

    private List<TransacaoDto> transacao;
}
