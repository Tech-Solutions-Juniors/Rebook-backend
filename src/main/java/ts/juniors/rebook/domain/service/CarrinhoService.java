package ts.juniors.rebook.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ts.juniors.rebook.domain.dto.CarrinhoDto;
import ts.juniors.rebook.infra.security.JwtTokenDto;

public interface CarrinhoService{
    Page<CarrinhoDto> getTodosCarrinhos(Pageable page);
    CarrinhoDto getCarrinhoPorID(long id);
    CarrinhoDto postCarrinho(CarrinhoDto dto, String tokenJWT);
    CarrinhoDto putCarrinho(CarrinhoDto dto,long id);
    void delCarrinho(long id);
}
