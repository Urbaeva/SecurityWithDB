package com.manas.entity;

import com.manas.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "auth_infos")
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthInfo implements UserDetails {

    @Id
    @SequenceGenerator(
            name = "auth_info_id_gen",
            sequenceName = "auth_info_id_seq",
            allocationSize = 1)
    @GeneratedValue(
            generator = "auth_info_id_gen",
            strategy = GenerationType.SEQUENCE)
    private Long id;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }
    @Override
    public String getPassword() {
        return this.password;
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
