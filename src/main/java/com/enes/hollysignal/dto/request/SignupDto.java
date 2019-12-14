package com.enes.hollysignal.dto.request;

import com.enes.hollysignal.model.auth.Profile;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class SignupDto {

    @Getter
    @Setter
    private Profile profile;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String repassword;
}
