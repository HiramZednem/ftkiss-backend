package com.codqueto.ftkiss.web.dtos.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateUserRequest {
    private String name;
    private String email;
    private String password;
    private LocalDate birthdate;
}
