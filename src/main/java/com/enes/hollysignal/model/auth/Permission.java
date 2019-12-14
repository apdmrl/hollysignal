package com.enes.hollysignal.model.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class Permission{

    @Getter
    @Setter
    private String target;

    @Getter
    @Setter
    private String action;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Permission) {
            Permission p = (Permission) obj;
            if (getTarget().equals(p.getTarget()) && getAction().equals(p.getAction())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (target + action).hashCode();
    }
}
