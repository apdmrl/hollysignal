package com.enes.hollysignal.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class PasswordRequest {

    private String oldPassword;
    private String newPassword;
    private String newPasswordA;
}