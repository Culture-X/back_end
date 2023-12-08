package TripAmi.backend.app.member.service;

import TripAmi.backend.app.member.domain.Member;
import TripAmi.backend.app.member.domain.MemberRepository;
import TripAmi.backend.app.util.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    /**
     *
     * @param nickName
     * @param imgUrl
     * @param agreedMailNotification
     * @param agreedPushNotification
     */
    @Override
    public void createMember(String nickName, String imgUrl, Boolean agreedMailNotification, Boolean agreedPushNotification) {
        Member member = Member.builder()
                            .nickName(nickName)
                            .imgUrl(imgUrl)
                            .agreedMailNotification(agreedMailNotification)
                            .agreedPushNotification(agreedPushNotification)
                            .build();
        memberRepository.save(member);
    }
}
