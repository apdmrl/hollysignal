package com.enes.hollysignal.model.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class Profile {

    @Getter
    @Setter
    private Contact contact;

    @Getter
    @Setter
    private boolean notify;


}