package TripAmi.backend.web.api.notification;

import TripAmi.backend.app.notification.service.NotificationService;
import TripAmi.backend.auth.authmember.domain.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    public SseEmitter subscribe(@AuthenticationPrincipal AuthMember member, @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId) {
        return notificationService.subscribe(member.getId(), lastEventId);
    }
}
