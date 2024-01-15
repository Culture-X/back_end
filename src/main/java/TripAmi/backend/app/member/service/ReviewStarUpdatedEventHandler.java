package TripAmi.backend.app.member.service;

import TripAmi.backend.app.member.domain.Ami;
import TripAmi.backend.app.member.domain.AmiRepository;
import TripAmi.backend.app.member.service.exception.MemberNotFound;
import TripAmi.backend.app.review.domain.event.ReviewStarUpdatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor
public class ReviewStarUpdatedEventHandler {
    private final AmiRepository amiRepository;

    @EventListener
    public void updateAmiRating(ReviewStarUpdatedEvent event) {
        Ami ami = amiRepository.findAmiById(event.getAmiId()).orElseThrow(MemberNotFound::new);
        ami.updateRating(event.getStar());
    }
}
