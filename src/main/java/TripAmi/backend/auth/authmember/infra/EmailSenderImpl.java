package TripAmi.backend.auth.authmember.infra;


import TripAmi.backend.auth.authmember.domain.EmailSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmailSenderImpl implements EmailSender {

    @Override
    public void sendEmail(String from, String to, String Content) {

    }
}
