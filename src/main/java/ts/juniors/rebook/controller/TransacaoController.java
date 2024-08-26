package ts.juniors.rebook.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ts.juniors.rebook.dto.TransacaoDto;
import ts.juniors.rebook.service.TransacaoService;

import java.net.URI;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService service;

    @GetMapping
    public Page<TransacaoDto> listarTransacoes(@PageableDefault(size = 10) Pageable paginacao){
        return service.GetTodasTransacoes(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransacaoDto> detalharTransacaoPorId(@PathVariable @NotNull Long id){
        TransacaoDto dto = service.GetPorID(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<TransacaoDto> cadastrarTransacao(@RequestBody @Valid TransacaoDto dto, UriComponentsBuilder uriBuilder){
        TransacaoDto transacao = service.PostTransacao(dto);
        URI uri = uriBuilder.path("/transacao/{id}").buildAndExpand(transacao.getId()).toUri();

        return ResponseEntity.created(uri).body(transacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransacaoDto> atualizarTransacao(@PathVariable @NotNull Long id,@RequestBody @Valid TransacaoDto dto){
        TransacaoDto atualizado = service.PutTransacao(id,dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TransacaoDto> deletarTransacao(@PathVariable @NotNull long id){
        service.DeleteTransacao(id);
        return ResponseEntity.noContent().build();
    }

}
