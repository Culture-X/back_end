package TripAmi.backend.app.member.service;

import TripAmi.backend.app.member.domain.Ami;
import TripAmi.backend.app.member.service.dto.AmiDto;

import java.util.List;

public interface AmiService {
    Long save();
    AmiDto findAmiByEmail(String email);
    AmiDto findAmiById(Long amiId);
    Ami findNonWithdrawalAmiById(Long amiId);
    List<AmiDto> findAmis();

}
