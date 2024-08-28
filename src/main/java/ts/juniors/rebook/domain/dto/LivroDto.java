package ts.juniors.rebook.domain.dto;

import lombok.Getter;
import lombok.Setter;
import ts.juniors.rebook.domain.enums.Estados;
import ts.juniors.rebook.domain.enums.Generos;

import java.util.List;

@Getter
@Setter
public class LivroDto {
    private long id;
    private String titulo;
    private String descricao;
    private List<Generos> generos;
    private List<Estados> estados;
    private List<String> imagemUrls;
    private String autor;
    private Long usuarioId;
}
