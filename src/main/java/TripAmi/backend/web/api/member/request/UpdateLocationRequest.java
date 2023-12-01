package TripAmi.backend.web.api.member.request;

public record UpdateLocationRequest(String lat, String lon, Long memberId) {
}
