package springboottodoappapis.models;

import org.springframework.security.core.GrantedAuthority;

public class AuthorityModel implements GrantedAuthority {
    private String authorityName;

    public AuthorityModel(String authorityName) {
        this.authorityName = authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    @Override
    public String getAuthority() {
        return this.authorityName;
    }
}
