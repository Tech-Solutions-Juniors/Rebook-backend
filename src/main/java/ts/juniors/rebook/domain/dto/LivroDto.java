package ts.juniors.rebook.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;
    private BigDecimal preco;
    private List<Generos> generos;
    private List<Estados> estados;
    @Size(max = 3, message = "Você pode adicionar no máximo 3 imagens")
    private Set<String> imagemUrls;
    private String autor;
    private Long usuarioId;
}
