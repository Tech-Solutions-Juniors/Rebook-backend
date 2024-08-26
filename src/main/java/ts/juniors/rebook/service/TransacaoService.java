package ts.juniors.rebook.service;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ts.juniors.rebook.dto.TransacaoDto;
import ts.juniors.rebook.model.Transacao;
import ts.juniors.rebook.repository.TransacaoRepository;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public Page<TransacaoDto> GetTodasTransacoes(Pageable paginacao){
        return repository.findAll(paginacao)
                .map(page -> modelMapper.map(page, TransacaoDto.class));
    }

    @Transactional
    public TransacaoDto GetPorID(Long id){
        Transacao transacao = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        return modelMapper.map(transacao, TransacaoDto.class);
    }

    @Transactional
    public TransacaoDto PostTransacao(TransacaoDto dto){
        Transacao transacao = modelMapper.map(dto, Transacao.class);
        repository.save(transacao);
        return  modelMapper.map(transacao, TransacaoDto.class);
    }

    @Transactional
    public TransacaoDto PutTransacao(Long id, TransacaoDto dto){
        Transacao transacao = modelMapper.map(dto, Transacao.class);
        transacao.setId(id);
        transacao = repository.save(transacao);
        return modelMapper.map(transacao, TransacaoDto.class);
    }

    @Transactional
    public void DeleteTransacao(Long id){
        repository.deleteById(id);
    }

}
