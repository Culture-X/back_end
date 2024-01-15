package TripAmi.backend.app.reservation.service;

import TripAmi.backend.app.reservation.domain.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> findFixedDays(Long programId);

}
