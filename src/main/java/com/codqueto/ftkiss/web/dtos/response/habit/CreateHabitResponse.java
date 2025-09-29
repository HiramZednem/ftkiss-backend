package com.codqueto.ftkiss.web.dtos.response.habit;

import lombok.Setter;

@Setter
public class CreateHabitResponse {
    private Long id;
    private String name;
    private String description;
    private Boolean hidden;
}
