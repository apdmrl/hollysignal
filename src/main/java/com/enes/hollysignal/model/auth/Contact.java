package com.enes.hollysignal.model.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Contact {

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String mobile;

    @Getter
    @Setter
    private String landline;

    public String getFullname() {
        return firstName + " " + lastName;
    }
}