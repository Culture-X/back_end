package TripAmi.backend.app.notification.infra;

import TripAmi.backend.app.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotificationServiceImpl implements NotificationService {

    @Override
    public SseEmitter subscribe(Long memberId, String lastEventId) {
        return null;
    }

    @Override
    public String makeTimeIncludeId(Long memberId) {
        return memberId + "_" + System.currentTimeMillis();
    }
}
