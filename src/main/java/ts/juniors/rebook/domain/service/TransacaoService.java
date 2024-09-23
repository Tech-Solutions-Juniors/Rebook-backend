package ts.juniors.rebook.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ts.juniors.rebook.domain.dto.TransacaoDto;

public interface TransacaoService {
    Page<TransacaoDto> getTodasTransacao(Pageable paginacao);
    TransacaoDto getTransacaoporID(long id);
    TransacaoDto postTransacao(TransacaoDto dto,String tokenJWT);
    TransacaoDto putTransacao(TransacaoDto dto,long id);
    void deleteTransacao(long id);
}
