package TripAmi.backend.app.util.service;

import TripAmi.backend.app.util.service.exception.SendMailException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender emailSender;

    public void sendEmail(String toEmail,
                          String title,
                          String text) {
        SimpleMailMessage emailForm = createEmailForm(toEmail, title, text);
        try {
            emailSender.send(emailForm);
        } catch (RuntimeException e) {
            log.debug("MailService.sendEmail exception occur toEmail: {}, " +
                          "title: {}, text: {}", toEmail, title, text);
            throw new SendMailException();
        }
    }

    public void sendEmail(String toEmail,
                          String fromEmail,
                          String title,
                          String text) {
        SimpleMailMessage emailForm = createEmailForm(toEmail, fromEmail, title, text);
        try {
            emailSender.send(emailForm);
        } catch (RuntimeException e) {
            log.debug("MailService.sendEmail exception occur toEmail: {}, " +
                          "title: {}, text: {}", toEmail, title, text);
            throw new SendMailException();
        }
    }

    /**
     * 인증코드를 이메일로 발송함
     *
     * @param toEmail  수신 메일
     */
    public void sendEmail(String toEmail, String authCode) {
        SimpleMailMessage emailForm = createAuthCodeText(toEmail, authCode);
        try {
            emailSender.send(emailForm);
        } catch (RuntimeException e) {
            log.debug("MailService.sendEmail exception occur toEmail: {}, " +
                          "title: {}, text: {}", toEmail, emailForm.getSubject(), emailForm.getText());
            throw new SendMailException();
        }
    }

    public void sendPasswordToEmail(String toEmail, String tempPassword) {
        SimpleMailMessage emailForm = createTempPasswordText(toEmail, tempPassword);
        try {
            emailSender.send(emailForm);
        } catch (RuntimeException e) {
            log.debug("MailService.sendEmail exception occur toEmail: {}, " +
                          "title: {}, text: {}", toEmail, emailForm.getSubject(), emailForm.getText());
            throw new SendMailException();
        }
    }

    // 발신할 이메일 데이터 세팅
    private SimpleMailMessage createEmailForm(String toEmail,
                                              String title,
                                              String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(title);
        message.setText(text);

        return message;
    }

    private SimpleMailMessage createEmailForm(String toEmail,
                                              String fromEmail,
                                              String title,
                                              String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setFrom(fromEmail);
        message.setSubject(title);
        message.setText(text);

        return message;
    }

    //todo thymeleaf로 수정하기
    private SimpleMailMessage createAuthCodeText(String toEmail,
                                                 String authCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("[TRIPAMI] Authentication code for join");
        message.setText("Authentication Code: " + authCode);
        return message;
    }

    private SimpleMailMessage createTempPasswordText(String toEmail, String tempPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("[TRIPAMI] Temporary password set");
        message.setText("\n\nTemporary Password: " + tempPassword + "\nA temporary password has been set. Please reset the password.");
        return message;
    }
}
