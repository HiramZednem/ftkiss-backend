package com.codqueto.ftkiss.web.mappers;

import com.codqueto.ftkiss.persistance.entities.Habit;
import com.codqueto.ftkiss.web.dtos.request.habit.CreateHabitRequest;
import com.codqueto.ftkiss.web.dtos.request.habit.UpdateHabitRequest;
import com.codqueto.ftkiss.web.dtos.response.habit.CreateHabitResponse;
import com.codqueto.ftkiss.web.dtos.response.habit.GetHabitResponse;
import com.codqueto.ftkiss.web.dtos.response.habit.UpdateHabitResponse;

public class HabitMapper {
     public static Habit map(CreateHabitRequest habitRequest) {
         Habit habit = new Habit();
         habit.setName(habitRequest.getName());
         habit.setDescription(habitRequest.getDescription());
         habit.setHidden(false);

         return habit;
     }

    public static Habit map(UpdateHabitRequest habitRequest, Habit habit) {
        habit.setName(habitRequest.getName());
        habit.setDescription(habitRequest.getDescription());
        habit.setHidden(habitRequest.getHidden());

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

     public static CreateHabitResponse toCreateHabitResponse(Habit habit) {
         CreateHabitResponse habitResponse = new CreateHabitResponse();
         habitResponse.setId(habit.getId());
         habitResponse.setName(habit.getName());
         habitResponse.setDescription(habit.getDescription());
         habitResponse.setHidden(habit.getHidden());
         habitResponse.setUserId(habit.getUser().getId());
         return habitResponse;
     }

     public static UpdateHabitResponse toUpdateHabitResponse(Habit habit) {
         UpdateHabitResponse habitResponse = new UpdateHabitResponse();
         habitResponse.setId(habit.getId());
         habitResponse.setName(habit.getName());
         habitResponse.setDescription(habit.getDescription());
         habitResponse.setHidden(habit.getHidden());
         return habitResponse;
     }
}
