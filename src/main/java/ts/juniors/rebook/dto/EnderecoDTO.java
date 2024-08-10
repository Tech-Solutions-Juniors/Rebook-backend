package ts.juniors.rebook.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {
    private Long id;
    private String rua;
    private String cidade;
    private String estado;
    private String cep;
    private String numero;
    private String complemento;
}
