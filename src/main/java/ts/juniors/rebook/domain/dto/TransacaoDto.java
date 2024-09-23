package ts.juniors.rebook.domain.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import ts.juniors.rebook.domain.entity.Livro;
import lombok.Getter;
import lombok.Setter;
import ts.juniors.rebook.domain.entity.Usuario;
import ts.juniors.rebook.domain.enums.Status;

import java.time.LocalDateTime;

@Getter
@Setter
public class TransacaoDto {

    private long id;

    private LivroDto livro;

    private UsuarioDto usuario;

    private PagamentoDto pagamentoDto;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime data;

}
