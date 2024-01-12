package TripAmi.backend.config;

import TripAmi.backend.app.member.domain.Ami;
import TripAmi.backend.app.member.domain.Traveler;
import TripAmi.backend.app.product.ProgramTheme;
import TripAmi.backend.app.product.domain.Program;
import TripAmi.backend.app.product.domain.ProgramRepository;
import TripAmi.backend.app.product.domain.Spot;
import TripAmi.backend.auth.authmember.domain.AuthMember;
import TripAmi.backend.auth.authmember.domain.AuthMemberRepository;
import TripAmi.backend.auth.authmember.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalTime;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class TmpDataInitializer {

    private final ProgramRepository programRepository;
    private final AuthMemberRepository authMemberRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    private void createMembersAndPrograms() {
        for (Long i = 1L; i <= 3L; i++) {
            Traveler traveler = new Traveler();
            Ami ami = new Ami();

            AuthMember authMember = AuthMember.builder()
                                        .email("user@asd." + i)
                                        .raw("user password" + 1)
                                        .role(Role.ROLE_MEMBER)
                                        .nickname("user" + i)
                                        .passwordEncoder(passwordEncoder)
                                        .traveler(traveler)
                                        .agreedMarketing(true)
                                        .ami(ami)
                                        .build();
            authMemberRepository.save(authMember);


            // Create three programs for each member
            for (int j = 1; j <= 3; j++) {
                createProgram("Program" + j, ami);
            }
        }
    }

    private void createProgram(String title, Ami ami) {
        // Add two spots to the program
        Spot spot1 = Spot.builder()
                         .title("Spot 1 for " + title)
                         .imgUrl("spot1_image.jpg")
                         .content("Spot 1 content for " + title)
                         .requiredTime(LocalTime.of(1, 30))
                         .build();

        Spot spot2 = Spot.builder()
                         .title("Spot 2 for " + title)
                         .imgUrl("spot2_image.jpg")
                         .content("Spot 2 content for " + title)
                         .requiredTime(LocalTime.of(2, 0))
                         .build();

        Program program = Program.builder()
                              .title(title)
                              .images(Arrays.asList("image1.jpg", "image2.jpg"))
                              .content("Program content for " + title)
                              .price(100)
                              .ami(ami)
                              .totalPeople(10)
                              .theme(ProgramTheme.ACTIVITY)
                              .keywords(Arrays.asList("keyword1", "keyword2"))
                              .location("Program location for " + title)
                              .spots(Arrays.asList(spot1, spot2))
                              .build();

        programRepository.save(program);
    }
}