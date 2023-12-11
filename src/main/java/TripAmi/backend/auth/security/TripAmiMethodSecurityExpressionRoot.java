package TripAmi.backend.auth.security;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

/**
 * 스프링 시큐리티 커스텀 표현식 클래스.
 * SecurityExpressionRoot 를 확장하여 미리 만들어져있는 표현식 또한 사용 가능하도록 구성함.
 */
public class TripAmiMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {
    private Object filterObject;
    private Object returnObject;

    /**
     * Creates a new instance
     *
     * @param authentication the {@link Authentication} to use. Cannot be null.
     */
    public TripAmiMethodSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }

    //todo 모르겠다
    public boolean idMatches(Long memberId) {
        TripAmiAuthentication tripAmiAuthentication = (TripAmiAuthentication) getAuthentication();
        return tripAmiAuthentication.getMemberId().equals(memberId);
    }

    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    @Override
    public Object getFilterObject() {
        return this.filterObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    @Override
    public Object getReturnObject() {
        return this.returnObject;
    }

    @Override
    public Object getThis() {
        return this;
    }
}
