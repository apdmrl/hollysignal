package com.enes.hollysignal.model.auth;

import com.enes.hollysignal.model.Base;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Document(collection = "Role")
@TypeAlias("Role")
@Accessors(chain = true)
public class Role extends Base implements GrantedAuthority {

    @Getter
    @Setter
    private String authority;

    @Getter
    @Setter
    private Set<Permission> permissions;

    public static Role admin() {
        return new Role()
                .setAuthority("ROLE_ADMIN");
    }

    public static Role user() {
        return new Role()
                .setAuthority("ROLE_USER");
    }

    public static Role predicter() {
        return new Role()
                .setAuthority("ROLE_PREDICTER");
    }

    @Override
    public int hashCode() {
        return authority.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Role) {
            return getAuthority().equals(((Role) obj).getAuthority());
        } else {
            return false;
        }
    }

}