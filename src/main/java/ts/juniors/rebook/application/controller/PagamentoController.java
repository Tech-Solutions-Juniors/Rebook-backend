package ts.juniors.rebook.application.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ts.juniors.rebook.application.service.PagamentoServiceImpl;
import ts.juniors.rebook.domain.dto.PagamentoDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pagamento")
public class PagamentoController {

    private final PagamentoServiceImpl service;

    @GetMapping
    private Page<PagamentoDto> listarPagamentos(@PageableDefault(size = 10)Pageable page){
        return service.getTodosPagamentos(page);
    }

    @GetMapping("{id}")
    private ResponseEntity<PagamentoDto> detalharPagamentoPorId(@PathVariable @NotNull long id){
        PagamentoDto dto  = service.getPagamentoporID(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    private ResponseEntity<PagamentoDto> cadastrarPagamento(@RequestBody PagamentoDto pagamentoDto){
       PagamentoDto pagamento = service.postPagamento(pagamentoDto);
        return ResponseEntity.ok().body(pagamento);
    }

    @PutMapping("{id}")
    private ResponseEntity<PagamentoDto> atualizarDadosPagamento(@PathVariable long id, @RequestBody @Valid PagamentoDto dto){
        PagamentoDto atualizado = service.putPagamento(dto,id);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("{id}")
    private ResponseEntity<PagamentoDto> removerPagamento(@PathVariable @NotNull long id){
        service.deletePagamento(id);
        return ResponseEntity.noContent().build();
    }
}
