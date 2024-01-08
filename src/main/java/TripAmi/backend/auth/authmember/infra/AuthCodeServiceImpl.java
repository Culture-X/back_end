package TripAmi.backend.auth.authmember.infra;

import TripAmi.backend.auth.authmember.domain.AuthCode;
import TripAmi.backend.auth.authmember.domain.AuthCodeGenerator;
import TripAmi.backend.auth.authmember.domain.AuthCodeRepository;
import TripAmi.backend.auth.authmember.service.AuthCodeService;
import TripAmi.backend.auth.authmember.service.exception.EmailNotFoundException;
import TripAmi.backend.auth.authmember.service.exception.UnverifiedEmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthCodeServiceImpl implements AuthCodeService {

    private final AuthCodeRepository authCodeRepository;
    private final AuthCodeGenerator authCodeGenerator;

    @Override
    public String save(String email, LocalDateTime inputTime) {
        AuthCode authCode = AuthCode.builder()
                                .email(email)
                                .code(authCodeGenerator.generate())
                                .requestTime(inputTime)
                                .min(3L)
                                .build();
        authCodeRepository.save(authCode);
        return authCode.getCode();
    }

    @Override
    public String update(String email, LocalDateTime inputTime) {
        AuthCode authCode = authCodeRepository.findAuthCodeByEmail(email).orElseThrow(EmailNotFoundException::new);
        authCode.updateCode(authCodeGenerator.generate(), inputTime, 3L);
        return authCode.getCode();
    }

    @Override
    @Transactional
    public String getAuthCode(String email, LocalDateTime inputTime) {
        try {
            return update(email, inputTime);
        } catch (EmailNotFoundException e) {
            return save(email, inputTime);
        }
    }

    @Override
    @Transactional
    public void confirm(String email, String inputCode, LocalDateTime inputTime) {
        AuthCode authCode = authCodeRepository.findAuthCodeByEmail(email).orElseThrow(EmailNotFoundException::new);
        authCode.validateInputTime(inputTime);
        authCode.validateInputCode(inputCode);
        authCode.authCodeConfirm();
    }

    @Override
    public void verifyConfirmedEmail(String email) {
        AuthCode authCode = authCodeRepository.findAuthCodeByEmail(email).orElseThrow(UnverifiedEmailException::new);
        if (!authCode.isConfirmed())
            throw new UnverifiedEmailException();
    }
}
