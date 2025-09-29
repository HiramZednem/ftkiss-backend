package com.codqueto.ftkiss.web.dtos.request.habit;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateHabitRequest {
    @NotNull @NotBlank
    private String name;

    @NotNull
    private String description;
}
