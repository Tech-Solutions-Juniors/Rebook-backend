package ts.juniors.rebook.service;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ts.juniors.rebook.dto.LivroDto;
import ts.juniors.rebook.model.Livro;
import ts.juniors.rebook.repository.LivroRepository;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<LivroDto> GetTodosLivros(Pageable paginacao) {
        return repository
                .findAll(paginacao)
                .map(p -> modelMapper.map(p, LivroDto.class));
    }

    public LivroDto GetPorId(Long id) {
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        return modelMapper.map(livro, LivroDto.class);
    }

    public LivroDto PostLivro(LivroDto dto) {
        Livro livro = modelMapper.map(dto, Livro.class);
        repository.save(livro);
        return modelMapper.map(livro, LivroDto.class);
    }

    public LivroDto PutLivro(Long id, LivroDto dto) {
        Livro livro = modelMapper.map(dto, Livro.class);
        livro.setId(id);
        livro = repository.save(livro);
        return modelMapper.map(livro, LivroDto.class);
    }

    public void DeleteLivro(Long id) {
        repository.deleteById(id);
    }
}
