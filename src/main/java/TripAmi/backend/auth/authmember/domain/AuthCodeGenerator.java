package TripAmi.backend.auth.authmember.domain;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class AuthCodeGenerator {

    Random random = new Random();

    /**
     * 6자리의 랜덤한 코드를 생성
     *
     * @return 문자, 숫자로 이루어진 6자리 랜덤 코드
     */
    public String generate() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++)
            code.append(random.nextInt(10));
        return code.toString();
    }
}
