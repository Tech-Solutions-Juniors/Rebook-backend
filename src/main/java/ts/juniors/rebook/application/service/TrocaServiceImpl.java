package ts.juniors.rebook.application.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ts.juniors.rebook.domain.dto.TrocaDto;
import ts.juniors.rebook.domain.entity.Troca;
import ts.juniors.rebook.domain.repository.TrocaRepository;
import ts.juniors.rebook.domain.service.TrocaService;

@Service
@RequiredArgsConstructor
public class TrocaServiceImpl implements TrocaService {

    private final TrocaRepository resp;
    private final ModelMapper mapper;


    @Override
    public Page<TrocaDto> getTodaTrocas(Pageable page) {
        return resp.findAll(page).map(pag -> mapper.map(pag, TrocaDto.class));
    }

    @Override
    public TrocaDto getTrocaPorID(long id) {
        Troca troca  = resp.findById(id).orElseThrow(()-> new EntityNotFoundException());
        return mapper.map(troca, TrocaDto.class);
    }

    @Override
    public TrocaDto postTroca(TrocaDto dto) {
        Troca troca = mapper.map(dto,Troca.class);
        resp.save(troca);

        return  mapper.map(troca, TrocaDto.class);
    }

    @Override
    public TrocaDto putTroca(TrocaDto dto, long id) {
        Troca troca = mapper.map(dto,Troca.class);
        troca.setId(id);
        resp.save(troca);

        return  mapper.map(troca, TrocaDto.class);
    }

    @Override
    public void deleteTroca(long id) {
        resp.deleteById(id);
    }
}
