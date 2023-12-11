package TripAmi.backend.config;

import lombok.Builder;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Getter
@ConfigurationProperties(prefix = "trip-ami.jwt")
public class JwtProperties {
    private final long accessTokenLifespanInMinutes;
    private final long refreshTokenLifespanInMinutes;
    private final String issuer;
    private final String subject;

    @Builder
    public JwtProperties(
        @DefaultValue("1800") long accessTokenLifespanInMinutes,
        @DefaultValue("180000") long refreshTokenLifespanInMinutes,
        @DefaultValue("self") String issuer,
        @DefaultValue("TripAmi") String subject
    ) {
        this.accessTokenLifespanInMinutes = accessTokenLifespanInMinutes;
        this.refreshTokenLifespanInMinutes = refreshTokenLifespanInMinutes;
        this.issuer = issuer;
        this.subject = subject;
    }
}
