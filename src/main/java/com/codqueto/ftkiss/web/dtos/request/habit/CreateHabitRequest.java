package com.codqueto.ftkiss.web.dtos.request.habit;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateHabitRequest {
    @NotNull @NotBlank
    private String name;

    private String description;
}
