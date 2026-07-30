package PhelipeProject.Integracao_Pagamento.service.JWT;

import PhelipeProject.Integracao_Pagamento.entity.UserEntity;
import PhelipeProject.Integracao_Pagamento.repository.UserRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class UserDetailsImpl implements UserDetails {

    private UUID id;
    private String username;
    private String password;
    private Boolean isActive;
    private Collection<? extends GrantedAuthority> roles;

    public UserDetailsImpl(UUID id, String username, String password,Boolean isActive, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isActive = isActive;
        this.roles = roles;
    }



    public static UserDetails build(UserEntity user) {

        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getEnumRoles().name()));

        return new UserDetailsImpl(user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getIsActive(),
                authorities);
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public @Nullable String getPassword() {
        return this.password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isActive;
    }
}
