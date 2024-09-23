package ts.juniors.rebook.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ts.juniors.rebook.domain.enums.Status;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "troca")
public class Troca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "livro1_id")
    private Livro livro1;

    @OneToOne
    @JoinColumn(name = "livro2_id")
    private Livro livro2;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "data")
    private LocalDateTime data;

}
