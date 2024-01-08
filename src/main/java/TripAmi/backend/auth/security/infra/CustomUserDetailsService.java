package TripAmi.backend.auth.security.infra;

import TripAmi.backend.app.member.service.exception.MemberNotFound;
import TripAmi.backend.auth.authmember.domain.AuthMember;
import TripAmi.backend.auth.authmember.domain.AuthMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final AuthMemberRepository authMemberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return authMemberRepository.findByEmail(username)
                   .map(this::createUserDetails)
                   .orElseThrow(MemberNotFound::new);
    }

    private UserDetails createUserDetails(AuthMember authMember) {
        return CustomUserDetails.of(authMember);
    }
}
