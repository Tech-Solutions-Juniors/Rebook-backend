package ts.juniors.rebook.application.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ts.juniors.rebook.application.service.TrocaServiceImpl;
import ts.juniors.rebook.domain.dto.TrocaDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/troca")
public class TrocaController{

    private final TrocaServiceImpl service;

    @GetMapping
    public Page<TrocaDto> listarTodasTrocas(@PageableDefault(size = 10)Pageable page){
        return service.getTodaTrocas(page);
    }

    @GetMapping("{id}")
    public ResponseEntity<TrocaDto> detalharTroca(@PathVariable @NotNull long id){
       TrocaDto dto = service.getTrocaPorID(id);
       return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<TrocaDto> cadarstrarNovaTroca(@RequestBody @Valid TrocaDto trocaDto){
        TrocaDto dto = service.postTroca(trocaDto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("{id}")
    public ResponseEntity<TrocaDto> atualizarTroca(@RequestBody @Valid TrocaDto trocaDto
            ,@PathVariable  @NotNull long id){
        TrocaDto atualizado = service.putTroca(trocaDto, id);
        return  ResponseEntity.ok().body(atualizado);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TrocaDto> removerTroca(@PathVariable long id){
        service.deleteTroca(id);
        return ResponseEntity.noContent().build();
    }
    
}
















