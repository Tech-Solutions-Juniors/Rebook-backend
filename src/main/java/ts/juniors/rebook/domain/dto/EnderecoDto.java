package ts.juniors.rebook.domain.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
public class EnderecoDto {
    private Long id;
    @NotBlank
    private String rua;
    @NotBlank
    private String cidade;
    @NotBlank
    private String estado;
    @NotBlank
    private String cep;
    @NotBlank
    private String numero;
    private String complemento;
}
