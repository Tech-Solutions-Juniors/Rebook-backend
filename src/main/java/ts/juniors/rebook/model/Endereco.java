package ts.juniors.rebook.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ENDERECO")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rua;
    private String cidade;
    private String estado;
    private String cep;
    private String numero;
    private String complemento;


}
