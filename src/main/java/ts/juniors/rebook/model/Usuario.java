package ts.juniors.rebook.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name= "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Insira um nome")
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotBlank(message = "Favor entrar um email válido")
    @Email(message = "Favor entrar um email válido")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotBlank(message = "digite uma senha")
    @Column(name = "senha", nullable = false)
    private String senha;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Livro> livros = new HashSet<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Endereco> enderecos = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
