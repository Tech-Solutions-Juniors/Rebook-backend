package ts.juniors.rebook.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ts.juniors.rebook.domain.dto.LivroDto;

public interface LivroService {
    Page<LivroDto> getTodosLivros(Pageable paginacao);
    LivroDto getPorId(Long id);
    LivroDto postLivro(LivroDto dto);
    LivroDto putLivro(Long id, LivroDto dto);
    void deleteLivro(Long id);
}

