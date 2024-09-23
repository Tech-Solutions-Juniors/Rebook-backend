package ts.juniors.rebook.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
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

    @NotBlank(message = "Título é obrigatório")
    @Size(min = 2, max = 100, message = "O título deve ter entre 2 e 100 caracteres")
    private String titulo;

    @Size(max = 1000, message = "A descrição não pode ter mais que 1000 caracteres")
    private String descricao;

    @Positive(message = "O preço deve ser um valor positivo")
    private BigDecimal preco;

    private List<Generos> generos;
    private List<Estados> estados;
    @Size(max = 3, message = "Você pode adicionar no máximo 3 imagens")
    private Set<String> imagemUrls;
    private String autor;
    private TransacaoDto transacao;
    private Long usuarioId;
}
