package TripAmi.backend.app.member.service;

import TripAmi.backend.app.util.Image;

public interface MemberService {
    void createMember(String nickName, String imgUrl, Boolean agreedMailNotification, Boolean agreedPushNotification);
}
