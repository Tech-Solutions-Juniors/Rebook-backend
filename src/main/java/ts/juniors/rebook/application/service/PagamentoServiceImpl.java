package ts.juniors.rebook.application.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ts.juniors.rebook.domain.dto.PagamentoDto;
import ts.juniors.rebook.domain.entity.Pagamento;
import ts.juniors.rebook.domain.repository.PagamentoRepository;
import ts.juniors.rebook.domain.service.PagamentoService;

@Service
@RequiredArgsConstructor
public class PagamentoServiceImpl implements PagamentoService {

    private final PagamentoRepository repository;
    private final ModelMapper mapper;


    @Override
    public Page<PagamentoDto> getTodosPagamentos(Pageable paginacao) {
        return repository.findAll(paginacao)
                .map(pag -> mapper.map(pag, PagamentoDto.class)) ;
    }

    @Override
    public PagamentoDto getPagamentoporID(long id) {
        Pagamento pagamento = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        return  mapper.map(pagamento,PagamentoDto.class);
    }

    @Override
    public PagamentoDto postPagamento(PagamentoDto dto) {
        Pagamento pagamento = mapper.map(dto,Pagamento.class);
        repository.save(pagamento);

        return mapper.map(pagamento,PagamentoDto.class);
    }

    @Override
    public PagamentoDto putPagamento(PagamentoDto dto, long id) {
        Pagamento pagamento = mapper.map(dto, Pagamento.class);
        pagamento.setId(id);
        pagamento = repository.save(pagamento);
        return mapper.map(pagamento,PagamentoDto.class);
    }

    @Override
    public void deletePagamento(long id) {
        repository.deleteById(id);
    }
}
