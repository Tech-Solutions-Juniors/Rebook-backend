package ts.juniors.rebook.application.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ts.juniors.rebook.application.service.CarrinhoServiceImpl;
import ts.juniors.rebook.domain.dto.CarrinhoDto;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carrinho")
public class CarrinhoControler {

    private final CarrinhoServiceImpl service;

    @GetMapping
    public Page<CarrinhoDto> listarCarrinho(@PageableDefault(size = 10)Pageable paginacao){
        return  service.getTodosCarrinhos(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarrinhoDto> detalharCarrinhoPorID(@PathVariable @NotNull long id){
        CarrinhoDto dto = service.getCarrinhoPorID(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<CarrinhoDto> cadastrarCarrinho(@RequestBody @Valid CarrinhoDto dto, UriComponentsBuilder uriBui, @RequestHeader("Authorization") String authHeader){
        String tokenJWT = authHeader.replace("Bearer ","");
        CarrinhoDto carrinho = service.postCarrinho(dto,tokenJWT);
        URI uri = uriBui.path("/carrinho/{id}").buildAndExpand(carrinho.getId()).toUri();

        return ResponseEntity.created(uri).body(carrinho);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarrinhoDto> atualizarTransacao(@PathVariable @NotNull Long id,@RequestBody @Valid CarrinhoDto dto){
        CarrinhoDto atualizado = service.putCarrinho(dto,id);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerTransacao(@PathVariable @NotNull Long id){
        service.delCarrinho(id);
        return ResponseEntity.noContent().build();
    }


}
