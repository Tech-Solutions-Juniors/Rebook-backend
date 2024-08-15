package ts.juniors.rebook.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import ts.juniors.rebook.enums.Estados;
import ts.juniors.rebook.enums.Generos;
import java.util.List;
import java.util.Set;

@Entity
@Table(name= "livro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(name = "titulo", nullable = false)
    private String titulo;

    @NotBlank
    @Column(name = "descricao", nullable = false)
    private String descricao;

    @ElementCollection(targetClass = Generos.class)
    @CollectionTable(name = "Livro_Generos", joinColumns = @JoinColumn(name = "livro_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "genero")
    @Size(max = 3, message = "Você pode escolher no máximo 3 gêneros")
    private List<Generos> generos;

    @ElementCollection(targetClass = Estados.class)
    @CollectionTable(name = "Livro_Estados", joinColumns = @JoinColumn(name = "livro_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "estados")
    @Size(max = 1)
    private List<Estados> estados;


    @ElementCollection
    @CollectionTable(name = "Livro_Imagens", joinColumns = @JoinColumn(name = "livro_id"))
    @Column(name = "imagem_url")
    @Size(max = 3, message = "Você pode adicionar no máximo 3 imagens")
    private Set<String> imagemUrls;

    @NotBlank
    @Column(name = "autor", nullable = false)
    private String autor;

    @ManyToOne
    @JoinTable(
            name = "usuario_id_livro_id",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private Usuario usuario;
}
