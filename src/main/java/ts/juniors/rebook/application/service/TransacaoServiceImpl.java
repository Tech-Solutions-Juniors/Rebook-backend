package ts.juniors.rebook.application.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ts.juniors.rebook.domain.dto.TransacaoDto;
import ts.juniors.rebook.domain.entity.Transacao;
import ts.juniors.rebook.domain.entity.Usuario;
import ts.juniors.rebook.domain.repository.LoginRepository;
import ts.juniors.rebook.domain.repository.TransacaoRepository;
import ts.juniors.rebook.domain.repository.UsuarioRepository;
import ts.juniors.rebook.domain.service.TransacaoService;
import ts.juniors.rebook.domain.service.UsuarioService;
import ts.juniors.rebook.infra.security.TokenService;

@Service
@RequiredArgsConstructor
public class TransacaoServiceImpl implements TransacaoService {

    private final ModelMapper mapper;
    private final TokenService tokenService;
    private final TransacaoRepository repository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public Page<TransacaoDto> getTodasTransacao(Pageable paginacao) {
        return repository.findAll(paginacao).map(pag -> mapper.map(pag,TransacaoDto.class));
    }

    @Override
    public TransacaoDto getTransacaoporID(long id) {
        Transacao transacao = repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException());

        return mapper.map(transacao,TransacaoDto.class) ;
    }

    @Override
    public TransacaoDto postTransacao(TransacaoDto dto ,String tokenJWT) {
        long idUserFromID = tokenService.getUserIdFromToken(tokenJWT);

        Usuario usuario = usuarioRepository.findByLoginId(idUserFromID)
                .orElseThrow(()-> new EntityNotFoundException("Usuario não encontrado"));

        Transacao transacao = mapper.map(dto,Transacao.class);
        transacao.setUsuario(usuario);

        repository.save(transacao);
        return mapper.map(transacao,TransacaoDto.class);
    }

    @Override
    public TransacaoDto putTransacao(TransacaoDto dto, long id) {
        Transacao transacao = mapper.map(dto,Transacao.class);
        transacao.setId(id);
        transacao = repository.save(transacao);
        return mapper.map(transacao,TransacaoDto.class);
    }

    @Override
    public void deleteTransacao(long id) {
        repository.deleteById(id);
    }
}
