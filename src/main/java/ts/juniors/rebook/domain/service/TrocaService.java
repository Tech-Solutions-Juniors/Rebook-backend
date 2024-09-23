package ts.juniors.rebook.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ts.juniors.rebook.domain.dto.TrocaDto;

public interface TrocaService {
    Page<TrocaDto> getTodaTrocas(Pageable page);
    TrocaDto getTrocaPorID(long id);
    TrocaDto postTroca(TrocaDto dto);
    TrocaDto putTroca(TrocaDto dto,long id);
    void deleteTroca(long id);
}
