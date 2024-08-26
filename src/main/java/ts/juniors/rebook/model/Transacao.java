package ts.juniors.rebook.model;

import jakarta.persistence.*;
import lombok.*;
import ts.juniors.rebook.enums.Status;

import java.util.Date;

@Entity
@Table(name= "transacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "livro", nullable = false)
    private Livro livro;

    @OneToOne
    @JoinColumn(name = "usuario", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "data", nullable = false)
    private Date data;
}
