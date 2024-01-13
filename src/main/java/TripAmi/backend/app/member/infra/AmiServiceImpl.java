package TripAmi.backend.app.member.infra;

import TripAmi.backend.app.member.domain.Ami;
import TripAmi.backend.app.member.domain.AmiRepository;
import TripAmi.backend.app.member.service.AmiService;
import TripAmi.backend.app.member.service.dto.AmiDto;
import TripAmi.backend.app.member.service.exception.MemberNotFound;
import TripAmi.backend.app.member.service.exception.WithdrawalMemberException;
import TripAmi.backend.auth.authmember.service.AuthMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AmiServiceImpl implements AmiService {

    private final AmiRepository amiRepository;
    private final AuthMemberService authMemberService;

    @Override
    @Transactional
    public Long save() {
        Ami ami = new Ami();
        amiRepository.save(ami);
        return ami.getId();
    }

    @Override
    public AmiDto findAmiByEmail(String email) {
        Ami ami = authMemberService.findAmiByEmail(email);
        return AmiDto.builder()
                   .email(ami.getAuthMember().getEmail())
                   .nickname(ami.getAuthMember().getNickname())
                   .imgUrl(ami.getAuthMember().getImgUrl())
                   .rating(ami.getRating())
                   .programs(ami.getPrograms())
                   .build();
    }

    @Override
    public AmiDto findAmiById(Long amiId) {
        Ami ami = amiRepository.findAmiById(amiId).orElseThrow(MemberNotFound::new);
        if (ami.getAuthMember().isWithdrawal())
            throw new WithdrawalMemberException();
        return AmiDto.builder()
                   .email(ami.getAuthMember().getEmail())
                   .nickname(ami.getAuthMember().getNickname())
                   .imgUrl(ami.getAuthMember().getImgUrl())
                   .rating(ami.getRating())
                   .programs(ami.getPrograms())
                   .build();
    }

    @Override
    public List<AmiDto> findAmis() {
        List<Ami> amis = amiRepository.findAll();
        List<AmiDto> amiDtos = new ArrayList<>();
        for (Ami ami : amis) {
            if (!ami.getAuthMember().isWithdrawal()) {
                amiDtos.add(AmiDto.builder()
                                .email(ami.getAuthMember().getEmail())
                                .nickname(ami.getAuthMember().getNickname())
                                .imgUrl(ami.getAuthMember().getImgUrl())
                                .rating(ami.getRating())
                                .programs(ami.getPrograms())
                                .build());
            }
        }
        return amiDtos;
    }

    @Override
    public Ami findNonWithdrawalAmiById(Long amiId) {
        Ami ami = amiRepository.findAmiById(amiId).orElseThrow(MemberNotFound::new);
        if (ami.getAuthMember().isWithdrawal())
            throw new WithdrawalMemberException();
        return ami;
    }
}
