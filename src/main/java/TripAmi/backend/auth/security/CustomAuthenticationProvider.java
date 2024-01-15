package TripAmi.backend.auth.security;

import TripAmi.backend.auth.authmember.domain.Role;
import TripAmi.backend.auth.security.infra.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final CustomUserDetailsService customUserDetailsService;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.debug("CustomAuthenticationProvider.authenticate");
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails user = customUserDetailsService.loadUserByUsername(email);
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new BadCredentialsException("BadCredentialsException");
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(Role.ROLE_MEMBER.name()));
        return new UsernamePasswordAuthenticationToken(token.getName(), token.getCredentials(), authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
