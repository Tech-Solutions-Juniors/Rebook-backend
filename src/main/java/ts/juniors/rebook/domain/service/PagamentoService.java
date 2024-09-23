package ts.juniors.rebook.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ts.juniors.rebook.domain.dto.PagamentoDto;

public interface PagamentoService {
    Page<PagamentoDto> getTodosPagamentos(Pageable paginacao);
    PagamentoDto getPagamentoporID(long id);
    PagamentoDto postPagamento(PagamentoDto dto);
    PagamentoDto putPagamento(PagamentoDto dto,long id);
    void deletePagamento(long id);
}
