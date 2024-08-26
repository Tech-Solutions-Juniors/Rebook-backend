package ts.juniors.rebook.dto;

import lombok.Getter;
import lombok.Setter;
import ts.juniors.rebook.model.Livro;
import ts.juniors.rebook.model.Usuario;

import java.util.Date;

@Getter
@Setter
public class TransacaoDto {
    private long id;
    private Livro livro;
    private Usuario comprador;
    private String status;
    private Date data;

}
