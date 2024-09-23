package ts.juniors.rebook.application.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ts.juniors.rebook.domain.dto.CarrinhoDto;
import ts.juniors.rebook.domain.entity.Carrinho;
import ts.juniors.rebook.domain.entity.Transacao;
import ts.juniors.rebook.domain.entity.Usuario;
import ts.juniors.rebook.domain.repository.CarrinhoRepository;
import ts.juniors.rebook.domain.repository.UsuarioRepository;
import ts.juniors.rebook.domain.service.CarrinhoService;
import ts.juniors.rebook.infra.security.TokenService;

@Service
@RequiredArgsConstructor
public class CarrinhoServiceImpl implements CarrinhoService {

    private final CarrinhoRepository repository;
    private final ModelMapper mapper;
    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    @Override
    public Page<CarrinhoDto> getTodosCarrinhos(Pageable page) {
        return repository.findAll(page).map(pag -> mapper.map(pag, CarrinhoDto.class));
    }

    @Override
    public CarrinhoDto getCarrinhoPorID(long id) {
        Carrinho carrinho  =  repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return mapper.map(carrinho, CarrinhoDto.class);
    }

    @Override
    public CarrinhoDto postCarrinho(CarrinhoDto dto,String tokenJWT) {
        long idUserFromID = tokenService.getUserIdFromToken(tokenJWT);

        Usuario usuario = usuarioRepository.findByLoginId(idUserFromID).orElseThrow(()-> new EntityNotFoundException("Usuario não encontrado"));

        Carrinho carrinho = mapper.map(dto, Carrinho.class);
        repository.save(carrinho);

        return mapper.map(carrinho, CarrinhoDto.class);
    }

    @Override
    public CarrinhoDto putCarrinho(CarrinhoDto dto, long id) {
        Carrinho atualizado = mapper.map(dto, Carrinho.class);
        atualizado.setId(id);
        repository.save(atualizado);

        return mapper.map(atualizado, CarrinhoDto.class);
    }

    @Override
    public void delCarrinho(long id) {
        repository.deleteById(id);
    }
}
