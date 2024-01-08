package TripAmi.backend.app.notification.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface NotificationService {
    SseEmitter subscribe(Long memberId, String lastEventId);

    String makeTimeIncludeId(Long memberId);
}
