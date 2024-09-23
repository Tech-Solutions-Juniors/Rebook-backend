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
import ts.juniors.rebook.application.service.TransacaoServiceImpl;
import ts.juniors.rebook.domain.dto.TransacaoDto;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoServiceImpl service;

    @GetMapping
    public Page<TransacaoDto> listarTransacoes(@PageableDefault(size = 10)Pageable paginacao){
        return service.getTodasTransacao(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransacaoDto> detalharTransacaoPorId(@PathVariable @NotNull Long id){
        TransacaoDto dto = service.getTransacaoporID(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<TransacaoDto> cadastrarTransacao(@RequestBody @Valid TransacaoDto dto, UriComponentsBuilder uriBui, @RequestHeader("Authorization") String authHeader){
        String tokenJWT = authHeader.replace("Bearer ","");
        TransacaoDto transacao = service.postTransacao(dto,tokenJWT);
        URI uri = uriBui.path("/transacao/{id}").buildAndExpand(transacao.getId()).toUri();

        return ResponseEntity.created(uri).body(transacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransacaoDto> atualizarTransacao(@PathVariable @NotNull Long id,@RequestBody @Valid TransacaoDto dto){
        TransacaoDto atualizado = service.putTransacao(dto,id);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerTransacao(@PathVariable @NotNull Long id){
        service.deleteTransacao(id);
        return ResponseEntity.noContent().build();
    }


}
