package com.codqueto.ftkiss.web.dtos.response.habit;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateHabitResponse {
    private Long id;
    private String name;
    private String description;
    private Boolean hidden;
    private Long userId;
}
