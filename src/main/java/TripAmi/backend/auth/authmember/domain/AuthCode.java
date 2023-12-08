package TripAmi.backend.auth.authmember.domain;

import java.util.Random;

public class AuthCode {

    static Random random = new Random();

    /**
     * 6자리의 랜덤한 코드를 생성
     *
     * @return 문자, 숫자로 이루어진 6자리 랜덤 코드
     */
    public static String generate() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++)
            code.append(random.nextInt(10));
        return code.toString();
    }
}
