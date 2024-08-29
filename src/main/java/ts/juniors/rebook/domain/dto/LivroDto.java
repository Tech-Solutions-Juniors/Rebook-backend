package ts.juniors.rebook.domain.dto;

import lombok.Getter;
import lombok.Setter;
import ts.juniors.rebook.domain.enums.Estados;
import ts.juniors.rebook.domain.enums.Generos;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class LivroDto {
    private long id;
    private String titulo;
    private String descricao;
    private BigDecimal preco;
    private List<Generos> generos;
    private List<Estados> estados;
    private Set<String> imagemUrls;
    private String autor;
    private Long usuarioId;
}
