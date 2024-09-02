package ts.juniors.rebook.domain.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "endereco")
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
