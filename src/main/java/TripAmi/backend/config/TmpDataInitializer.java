package TripAmi.backend.config;

import TripAmi.backend.app.banner.domain.Banner;
import TripAmi.backend.app.banner.domain.BannerRepository;
import TripAmi.backend.app.member.domain.Ami;
import TripAmi.backend.app.member.domain.Traveler;
import TripAmi.backend.app.product.ProgramTheme;
import TripAmi.backend.app.product.domain.Program;
import TripAmi.backend.app.product.domain.ProgramRepository;
import TripAmi.backend.app.product.domain.Spot;
import TripAmi.backend.app.product.domain.TransportCode;
import TripAmi.backend.app.report.domain.Report;
import TripAmi.backend.app.report.domain.ReportRepository;
import TripAmi.backend.app.report.domain.ReportStatus;
import TripAmi.backend.app.report.domain.ReportType;
import TripAmi.backend.app.util.Star;
import TripAmi.backend.auth.authmember.domain.AuthMember;
import TripAmi.backend.auth.authmember.domain.AuthMemberRepository;
import TripAmi.backend.auth.authmember.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TmpDataInitializer {

    private final ProgramRepository programRepository;
    private final AuthMemberRepository authMemberRepository;
    private final PasswordEncoder passwordEncoder;
    private final BannerRepository bannerRepository;
    private final ReportRepository reportRepository;

    @PostConstruct
    private void createBanner() {
        Banner banner = Banner.builder()
                            .title("test data")
                            .imgUrl("https://geographical.co.uk/wp-content/uploads/panda1200-1.jpg")
                            .build();

        bannerRepository.save(banner);
    }

    @PostConstruct
    private void createReport() {
        Report report = Report.builder()
                            .authorId(1L)
                            .type(ReportType.INCORRECT)
                            .content("test report test reporttest reporttest reporttest reporttest report")
                            .status(ReportStatus.OPEN)
                            .build();

        reportRepository.save(report);

        Report report2 = Report.builder()
                             .authorId(2L)
                             .type(ReportType.ECT)
                             .content("test report test reporttest reporttest reporttest reporttest report")
                             .status(ReportStatus.READ)
                             .build();

        reportRepository.save(report2);

        Report report3 = Report.builder()
                             .authorId(3L)
                             .type(ReportType.AMI_PROBLEM)
                             .content("test report test reporttest reporttest reporttest reporttest report")
                             .status(ReportStatus.PROCESSED)
                             .build();

        reportRepository.save(report3);

    }

    @PostConstruct
    private void createMembersAndPrograms() {
        LocalDateTime time = LocalDateTime.now();

        for (Long i = 1L; i <= 3L; i++) {
            time = time.plusDays(1L);
            Traveler traveler = new Traveler();
            Ami ami = new Ami();
            ami.updateRating(Star.FIVE);
            ami.updateRating(Star.THREE);
            ami.updateRating(Star.TWO);

            AuthMember authMember = AuthMember.builder()
                                        .email("user" + i + "@gmail.com")
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
                createProgram("Program" + j, ami, time);
                time = time.plusDays(30L);
            }
        }
    }

    private void createProgram(String title, Ami ami, LocalDateTime plusDay) {
        // Add two spots to the program
        Spot spot1 = Spot.builder()
                         .title("Spot 1 for " + title)
                         .imgUrl("https://geographical.co.uk/wp-content/uploads/panda1200-1.jpg")
                         .content("Spot 1 content for " + title)
                         .requiredTime(LocalTime.of(1, 30))
                         .distance("1km")
                         .transportWithTimes(Map.of(
                             TransportCode.WALK, LocalTime.of(0, 30),
                             TransportCode.BUS, LocalTime.of(1, 0),
                             TransportCode.TRAIN, LocalTime.of(1, 30),
                             TransportCode.CAR, LocalTime.of(2, 0)
                         ))
                         .build();

        Spot spot2 = Spot.builder()
                         .title("Spot 2 for " + title)
                         .imgUrl("https://geographical.co.uk/wp-content/uploads/panda1200-1.jpg")
                         .content("Spot 2 content for " + title)
                         .requiredTime(LocalTime.of(2, 0))
                         .distance("3km")
                         .transportWithTimes(Map.of(
                             TransportCode.WALK, LocalTime.of(0, 30),
                             TransportCode.BUS, LocalTime.of(1, 0),
                             TransportCode.TRAIN, LocalTime.of(1, 30),
                             TransportCode.CAR, LocalTime.of(2, 0)
                         ))
                         .build();

        Program program = Program.builder()
                              .title(title)
                              .subTitle("sub title" + title)
                              .images(Arrays.asList("https://geographical.co.uk/wp-content/uploads/panda1200-1.jpg", "https://geographical.co.uk/wp-content/uploads/panda1200-1.jpg"))
                              .content("Program content for " + title)
                              .price(100)
                              .ami(ami)
                              .totalPeople(10)
                              .theme(ProgramTheme.ACTIVITY)
                              .keywords(Arrays.asList("keyword1", "keyword2"))
                              .location("Program location for " + title)
                              .spots(Arrays.asList(spot1, spot2))
                              .startTime(plusDay)
                              .build();

        spot1.setProgram(program);
        spot2.setProgram(program);
        programRepository.save(program);
    }
}