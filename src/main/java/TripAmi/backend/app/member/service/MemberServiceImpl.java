package TripAmi.backend.app.member.service;

import TripAmi.backend.app.member.domain.Member;
import TripAmi.backend.app.member.domain.MemberRepository;
import TripAmi.backend.app.tourist.domain.Tourist;
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
     * 사용자를 생성하고 저장
     *
     * @param username username
     * @return 생성된 사용자 UUID
     */
    @Override
    public UUID createMember(String username) {
        UUID uuid = UUID.randomUUID();

        Tourist tourist = Tourist.builder()
                              .uuid(uuid)
                              .build();
        memberRepository.save(tourist);
        return tourist.getUserSeq();
    }
}
