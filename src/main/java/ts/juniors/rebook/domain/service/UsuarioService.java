package ts.juniors.rebook.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import ts.juniors.rebook.domain.dto.UsuarioDto;
import ts.juniors.rebook.domain.dto.UsuarioInsertDto;

public interface UsuarioService {

    ResponseEntity<Page<UsuarioDto>> getTodosUsuarios(Pageable paginacao);

    ResponseEntity<UsuarioDto> getPorId(Long id);

    ResponseEntity<UsuarioInsertDto> postUsuario(UsuarioInsertDto dto);

    ResponseEntity<UsuarioDto> putUsuario(Long id, UsuarioDto dto, String tokenJWT);

    ResponseEntity<Void> deleteUsuario(Long id, String token);


}

