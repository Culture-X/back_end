package TripAmi.backend.auth.security.infra;

import TripAmi.backend.auth.authmember.domain.AuthMember;
import TripAmi.backend.auth.authmember.domain.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class CustomUserDetails extends AuthMember implements UserDetails {
    private Long id;
    private String email;
    private Set<Role> roles = new TreeSet<>();
    private String password;

    private CustomUserDetails(AuthMember authMember) {
        this.id = authMember.getId();
        this.email = authMember.getEmail();
        this.password = authMember.getPassword();
        this.roles = authMember.getRoles();
    }

    private CustomUserDetails(String email, Set<Role> roles) {
        this.email = email;
        this.roles = roles;
    }

    private CustomUserDetails(String email, String password, Set<Role> roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public static CustomUserDetails of(AuthMember authMember) {
        return new CustomUserDetails(authMember);
    }

    public static CustomUserDetails of(String email, Set<Role> roles) {
        return new CustomUserDetails(email, roles);
    }

    public static CustomUserDetails of(String email, String password, Set<Role> roles) {
        return new CustomUserDetails(email, password, roles);
    }

    private Collection<GrantedAuthority> createAuthorities(Set<Role> roles) {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.toString()));
        }

        return authorities;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.createAuthorities(roles);
    }

    @Override
    public String getUsername() {
        return this.email;
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
