package ts.juniors.rebook.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ts.juniors.rebook.domain.enums.FormaPagamento;
import ts.juniors.rebook.domain.enums.Status;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "pagamento")
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //ExternalId pagamentoApiID;

    @Column(name = "valor" ,nullable = false)
    private Float valor;

    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    @OneToOne
    @JoinColumn(name = "transacao_id", nullable = false)
    private Transacao transacao;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "data",nullable = false)
    private LocalDateTime data;

    @Column(name = "dataConfirmacao",nullable = false)
    private LocalDateTime dataConfirmacao;

}
