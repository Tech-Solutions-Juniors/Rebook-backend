package ts.juniors.rebook.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.ArrayList;
<<<<<<< HEAD
import java.util.HashSet;
import java.util.List;
import java.util.Set;
=======
import java.util.List;
>>>>>>> 25c965f (Revert "Criação da entidade Transacao")

@Entity
@Table(name= "Usuario")
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

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Livro> livros = new ArrayList<>();

<<<<<<< HEAD
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Endereco> enderecos = new HashSet<>();
=======
>>>>>>> 25c965f (Revert "Criação da entidade Transacao")
}
