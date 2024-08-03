package ts.juniors.rebook.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name= "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotNull
    @Email
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotNull
    @Column(name = "senha", nullable = false)
    private String senha;


}
