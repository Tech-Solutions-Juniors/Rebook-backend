package ts.juniors.rebook.domain.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInsertDto extends UsuarioDto {

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    private String senha;
    @NotBlank(message = "Confirmação de senha é obrigatória")
    private String confirmacaosenha;


    @AssertTrue(message = "As senhas não correspondem")
    public boolean isSenhasIguais() {
        return senha != null && senha.equals(confirmacaosenha);
    }
}
