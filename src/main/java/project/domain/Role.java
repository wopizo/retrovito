package project.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, BLOCKED;

    @Override
    public String getAuthority() {
        return name();
    }
}
