package ts.juniors.rebook.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDto {

    private long id;
    private String nome;
    private String email;
    private String senha;

}
