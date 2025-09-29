package com.codqueto.ftkiss.web.dtos.request.habit;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateHabitRequest {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String hidden;
}
