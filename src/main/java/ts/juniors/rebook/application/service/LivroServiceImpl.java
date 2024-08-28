package ts.juniors.rebook.application.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ts.juniors.rebook.domain.dto.LivroDto;
import ts.juniors.rebook.domain.entity.Livro;
import ts.juniors.rebook.domain.repository.LivroRepository;
import ts.juniors.rebook.domain.service.LivroService;

@Service
@RequiredArgsConstructor
public class LivroServiceImpl implements LivroService {


    private final LivroRepository repository;


    private final ModelMapper modelMapper;

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
    public LivroDto postLivro(LivroDto dto) {
        Livro livro = modelMapper.map(dto, Livro.class);
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
