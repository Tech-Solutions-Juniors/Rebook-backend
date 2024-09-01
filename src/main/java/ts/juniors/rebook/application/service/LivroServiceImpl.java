package ts.juniors.rebook.application.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ts.juniors.rebook.domain.dto.LivroDto;
import ts.juniors.rebook.domain.entity.Livro;
import ts.juniors.rebook.domain.entity.Usuario;
import ts.juniors.rebook.domain.repository.LivroRepository;
import ts.juniors.rebook.domain.repository.UsuarioRepository;
import ts.juniors.rebook.domain.service.LivroService;
import ts.juniors.rebook.infra.security.TokenService;

import java.util.Collections;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class LivroServiceImpl implements LivroService {


    private final LivroRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;
    private final TokenService tokenService;

    @Override
    public Page<LivroDto> getTodosLivros(Pageable paginacao) {
        return repository
                .findAll(paginacao)
                .map(p -> modelMapper.map(p, LivroDto.class));
    }

    @Override
    public LivroDto getPorId(Long id) {
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        return modelMapper.map(livro, LivroDto.class);
    }

    @Override
    public LivroDto postLivro(LivroDto dto, String tokenJWT) {
        Long userIdFromToken = tokenService.getUserIdFromToken(tokenJWT);

        Usuario usuario = usuarioRepository.findById(userIdFromToken)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Livro livro = modelMapper.map(dto, Livro.class);
        livro.setUsuario(usuario);

        // Ensure imagemUrls is not null
        if (livro.getImagemUrls() == null || livro.getImagemUrls().isEmpty()) {
            livro.setImagemUrls(new HashSet<>(Collections.singletonList("default_image_url")));
        }

        if (livro.getImagemUrls() == null) {
            livro.setImagemUrls(new HashSet<>());
        }
        if (!dto.getImagemUrls().isEmpty()) {
            livro.getImagemUrls().addAll(dto.getImagemUrls());
        }
        repository.save(livro);
        return modelMapper.map(livro, LivroDto.class);
    }

    @Override
    public LivroDto putLivro(Long id, LivroDto dto) {
        Livro livro = modelMapper.map(dto, Livro.class);
        livro.setId(id);
        livro = repository.save(livro);
        return modelMapper.map(livro, LivroDto.class);
    }

    @Override
    public void deleteLivro(Long id) {
        repository.deleteById(id);
    }
}
