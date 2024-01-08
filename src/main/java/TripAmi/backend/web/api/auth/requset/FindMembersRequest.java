package TripAmi.backend.web.api.auth.requset;

import TripAmi.backend.app.util.FilterBy;
import TripAmi.backend.app.util.OrderBy;

public record FindMembersRequest(
    FilterBy filterby,
    OrderBy orderBy,
    Boolean desc
) {
}
