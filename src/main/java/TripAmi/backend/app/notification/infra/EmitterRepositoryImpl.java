package TripAmi.backend.app.notification.infra;

import TripAmi.backend.app.notification.domain.EmitterRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmitterRepositoryImpl implements EmitterRepository {
    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
    private final Map<String, Object> eventCache = new ConcurrentHashMap<>();

    @Override
    public SseEmitter save(String emitterId, SseEmitter sseEmitter) {
        emitters.put(emitterId, sseEmitter);
        return sseEmitter;
    }

    @Override
    public void saveEventCache(String emitterId, Object event) {
        eventCache.put(emitterId, event);
    }


    @Override
    public Map<String, SseEmitter> findAllEmitterStartWithMemberId(String memberId) {
        return emitters.entrySet().stream()
                   .filter(entry -> entry.getKey().startsWith(memberId))
                   .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public Map<String, Object> findAllEventCacheStartWithByMemberId(String memberId) {
        return eventCache.entrySet().stream()
                   .filter(entry -> entry.getKey().startsWith(memberId))
                   .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public void deleteById(String id) {
        emitters.remove(id);
    }

    @Override
    public void deleteAllEmitterStartWithId(String memberId) {
        emitters.forEach(
            (key, emitter) -> {
                if (key.startsWith(memberId)) {
                    emitters.remove(key);
                }
            }
        );
    }

    @Override
    public void deleteAllEventCacheStartWithId(String memberId) {
        eventCache.forEach(
            (key, emitter) -> {
                if (key.startsWith(memberId)) {
                    eventCache.remove(key);
                }
            }
        );
    }
}
