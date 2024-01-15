package TripAmi.backend.app.member.service;

import TripAmi.backend.app.member.service.dto.TravelerDto;

public interface TravelerService {
    Long save();
    TravelerDto findById(Long travelerId);
}
