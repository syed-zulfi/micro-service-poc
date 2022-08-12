package com.royalfood.user.model.dto;

import lombok.*;

import java.util.*;

@Data
public class UserDto<T> {
    private String email;
    private String firstName;
    private String lastName;
    @Getter(AccessLevel.NONE)
    private String fullName;
    private String mobile;
    private List<T> departments;


    public String getFullName(){
        return this.firstName+" "+this.lastName;
    }
}
