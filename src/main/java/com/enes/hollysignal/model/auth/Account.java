package com.enes.hollysignal.model.auth;

import com.enes.hollysignal.model.Base;
import com.enes.hollysignal.model.Status;
import com.enes.hollysignal.model.prediction.Predict;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Accessors(chain = true)
@Document(collection = "Account")
@TypeAlias("Account")
@JsonIgnoreProperties({"password", "accountNonExpired",
        "accountNonLocked", "credentialsNonExpired"})
public class Account extends Base implements UserDetails {
    @Indexed
    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private Role role;

    @Getter
    @Setter
    private Profile profile;

    //kendi oluşturduğu ve satın aldığı predictleri ayırmak için
    //accountId yi kullanabilirsin.
    @Getter
    @Setter
    private List<Predict> predictList;

    @Getter
    @Setter
    private Status status;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new Role[]{getRole()});
    }

    @Getter
    @Setter
    private Date created;

    @Override
    public boolean isEnabled() {
        return status == Status.ENABLED;
    }

    @Getter
    @Setter
    private boolean accountNonExpired = true;

    @Getter
    @Setter
    private boolean accountNonLocked = true;

    @Getter
    @Setter
    private boolean credentialsNonExpired = true;


    public boolean hasRole(Role role) {
        return getAuthorities().contains(role);
    }

    public boolean hasPermission(String target, String action) {
        return role.getPermissions().contains(new Permission()
                .setAction(action)
                .setTarget(target));
    }
}
