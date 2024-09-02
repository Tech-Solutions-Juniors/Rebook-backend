package ts.juniors.rebook.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;




@Getter
@Setter
public class UsuarioInsertDto extends UsuarioDto {

    @NotBlank(message = "digite uma senha")
    private String senha;

}
