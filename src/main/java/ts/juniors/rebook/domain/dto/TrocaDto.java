package ts.juniors.rebook.domain.dto;

import lombok.Getter;
import lombok.Setter;
import ts.juniors.rebook.domain.enums.Status;

import java.time.LocalDateTime;

@Getter
@Setter
public class TrocaDto {
    private long id;
    private LivroDto livro1;
    private LivroDto livro2;
    private Status status;
    private LocalDateTime data;
}
