package com.codqueto.ftkiss.web.mappers;

import com.codqueto.ftkiss.persistance.entities.Habit;
import com.codqueto.ftkiss.web.dtos.request.habit.CreateHabitRequest;
import com.codqueto.ftkiss.web.dtos.response.habit.GetHabitResponse;

public class HabitMapper {
     public static Habit map(CreateHabitRequest habitRequest) {
         Habit habit = new Habit();
         habit.setName(habit.getName());
         habit.setDescription(habit.getDescription());
         habit.setHidden(false);

         return habit;
     }

     public static GetHabitResponse toGetHabitResponse(Habit habit) {
         GetHabitResponse habitResponse = new GetHabitResponse();
         habitResponse.setId(habit.getId());
         habitResponse.setName(habit.getName());
         habitResponse.setDescription(habit.getDescription());
         habitResponse.setHidden(habit.getHidden());
         return habitResponse;
     }
}
