package ts.juniors.rebook.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ts.juniors.rebook.domain.dto.TransacaoDto;

public interface PagamentoService {
    Page<TransacaoDto> getTodasPagamento(Pageable paginacao);
    TransacaoDto getPagamentoporID(long id);
    TransacaoDto postPagamento(TransacaoDto dto,String tokenJWT);
    TransacaoDto putPagamento(TransacaoDto dto,long id);
    void deletePagamento(long id);
}
