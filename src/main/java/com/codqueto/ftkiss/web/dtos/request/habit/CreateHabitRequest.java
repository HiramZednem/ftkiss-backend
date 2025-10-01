package com.codqueto.ftkiss.web.dtos.request.habit;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateHabitRequest {
    @NotNull @NotBlank
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Long userId;
}
