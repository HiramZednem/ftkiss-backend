package com.codqueto.ftkiss.web.dtos.response.user;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateUserResponse {
    private Integer userId;
    private String name;
    private String email;
    private LocalDate birthdate;
}
