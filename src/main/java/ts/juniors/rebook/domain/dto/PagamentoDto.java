package ts.juniors.rebook.domain.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import ts.juniors.rebook.domain.enums.FormaPagamento;
import ts.juniors.rebook.domain.enums.Status;
import java.time.LocalDateTime;

@Getter
@Setter
public class PagamentoDto {
    
    private long id;

    //private ExternalId pagamentoApiID;

    private Float valor;

    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    private TransacaoDto transacao;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime data;

    private LocalDateTime dataConfirmacao;

}
