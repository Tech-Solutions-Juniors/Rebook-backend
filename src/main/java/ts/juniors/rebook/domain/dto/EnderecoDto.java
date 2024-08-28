package ts.juniors.rebook.domain.dto;
import lombok.*;

@Getter
@Setter
public class EnderecoDto {
    private Long id;
    private String rua;
    private String cidade;
    private String estado;
    private String cep;
    private String numero;
    private String complemento;

}
