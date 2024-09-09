package ts.juniors.rebook.domain.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
public class EnderecoDto {

    private long id;

    @NotBlank(message = "Rua é obrigatória")
    @Size(min = 2, max = 100, message = "A rua deve ter entre 2 e 100 caracteres")
    private String rua;

    @NotBlank(message = "Cidade é obrigatória")
    @Size(max = 50, message = "A cidade não pode ter mais que 50 caracteres")
    private String cidade;

    @NotBlank(message = "Estado é obrigatório")
    @Size(max = 50, message = "O estado não pode ter mais que 50 caracteres")
    private String estado;

    @NotBlank(message = "CEP é obrigatório")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "Formato de CEP inválido")
    private String cep;

    @NotBlank
    private String numero;

    private String complemento;
}
