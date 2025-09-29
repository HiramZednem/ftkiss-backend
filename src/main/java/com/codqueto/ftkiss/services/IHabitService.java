package com.codqueto.ftkiss.services;

import com.codqueto.ftkiss.persistance.entities.Habit;
import com.codqueto.ftkiss.web.dtos.request.habit.CreateHabitRequest;
import com.codqueto.ftkiss.web.dtos.request.habit.UpdateHabitRequest;
import com.codqueto.ftkiss.web.dtos.response.habit.CreateHabitResponse;
import com.codqueto.ftkiss.web.dtos.response.habit.GetHabitResponse;
import com.codqueto.ftkiss.web.dtos.response.habit.UpdateHabitResponse;

import java.util.List;

public interface IHabitService {
    List<GetHabitResponse> list(Long userId);

    GetHabitResponse get(Long id);

    CreateHabitResponse create(CreateHabitRequest habitRequest, Long userId);

    UpdateHabitResponse update(UpdateHabitRequest updateHabitRequest, Long id);

    void delete(Long id);

}
