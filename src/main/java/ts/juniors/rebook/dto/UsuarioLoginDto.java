package ts.juniors.rebook.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public record UsuarioLoginDto(String email, String senha) {
}
