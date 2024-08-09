package ts.juniors.rebook.mapper;

import ts.juniors.rebook.dto.EnderecoDTO;
import ts.juniors.rebook.model.Endereco;

public class EnderecoMapper {


    public static Endereco convertToEntity(EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco();
        endereco.setId(enderecoDTO.getId());
        endereco.setRua(enderecoDTO.getRua());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setEstado(enderecoDTO.getEstado());
        endereco.setCep(enderecoDTO.getCep());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setComplemento(enderecoDTO.getComplemento());

        return endereco;
    }

    public static EnderecoDTO convertToDTO(Endereco endereco) {
        return new EnderecoDTO(
                endereco.getId(),
                endereco.getRua(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep(),
                endereco.getNumero(),
                endereco.getComplemento()
        );
    }
}
