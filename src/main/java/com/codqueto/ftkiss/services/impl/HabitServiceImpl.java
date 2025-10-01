package com.codqueto.ftkiss.services.impl;

import com.codqueto.ftkiss.persistance.entities.Habit;
import com.codqueto.ftkiss.persistance.entities.User;
import com.codqueto.ftkiss.persistance.repositories.IHabitRespository;
import com.codqueto.ftkiss.services.IHabitService;
import com.codqueto.ftkiss.services.IUserService;
import com.codqueto.ftkiss.web.dtos.request.habit.CreateHabitRequest;
import com.codqueto.ftkiss.web.dtos.request.habit.UpdateHabitRequest;
import com.codqueto.ftkiss.web.dtos.response.habit.CreateHabitResponse;
import com.codqueto.ftkiss.web.dtos.response.habit.GetHabitResponse;
import com.codqueto.ftkiss.web.dtos.response.habit.UpdateHabitResponse;
import com.codqueto.ftkiss.web.exceptions.HabitNotFoundException;
import com.codqueto.ftkiss.web.mappers.HabitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitServiceImpl implements IHabitService {

    private final IHabitRespository repository;
    private final IUserService userService;

    @Autowired
    public HabitServiceImpl(IHabitRespository repository, IUserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public List<GetHabitResponse> list(Long userId) {
        User user = this.userService.getUserById(userId);

        return repository.getHabitsByUserId(user.getId())
                .stream()
                .map(HabitMapper::toGetHabitResponse)
                .toList();
    }

    @Override
    public GetHabitResponse get(Long id) {
        if(id < 0) {
            throw new IllegalArgumentException("The id should be greater than 0");
        }

        Habit habit = repository.findById(id)
                .orElseThrow(() -> new HabitNotFoundException("Habit with id: " + id + " not found"));
        return HabitMapper.toGetHabitResponse(habit);
    }

    @Override
    public CreateHabitResponse create(CreateHabitRequest habitRequest) {
        Habit habit = HabitMapper.map(habitRequest);

        User user = userService.getUserById(habitRequest.getUserId());
        habit.setUser(user);

        Habit createdHabit = repository.save(habit);

        habit.setId(createdHabit.getId());
        return HabitMapper.toCreateHabitResponse(habit);
    }

    @Override
    public UpdateHabitResponse update(UpdateHabitRequest updateHabitRequest, Long habitId) {
        if(habitId < 0) {
            throw new IllegalArgumentException("The id should be greater than 0");
        }

        Habit habit = repository.findById(habitId)
                .orElseThrow( () -> new HabitNotFoundException("Habit with id: " + habitId + " not found"));

        Habit habitSaved = this.repository.save(HabitMapper.map(updateHabitRequest, habit));

        return HabitMapper.toUpdateHabitResponse(habitSaved);
    }

    @Override
    public void delete(Long habitId) {
        if(habitId < 0) {
            throw new IllegalArgumentException("The id should be greater than 0");
        }

        Habit habit = repository.findById(habitId)
                .orElseThrow( () -> new HabitNotFoundException("Habit with id: " + habitId + " not found"));

        this.repository.delete(habit);
    }
}
