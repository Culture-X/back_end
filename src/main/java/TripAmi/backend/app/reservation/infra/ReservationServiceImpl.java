package TripAmi.backend.app.reservation.infra;

import TripAmi.backend.app.reservation.domain.Reservation;
import TripAmi.backend.app.reservation.domain.ReservationRepository;
import TripAmi.backend.app.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    @Override
    public List<Reservation> findFixedDays(Long programId) {
        return reservationRepository.findByProgramId(programId);
    }
}
