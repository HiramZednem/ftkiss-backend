package com.codqueto.ftkiss.services.impl;

import com.codqueto.ftkiss.persistance.entities.Habit;
import com.codqueto.ftkiss.persistance.repositories.IHabitRespository;
import com.codqueto.ftkiss.services.IHabitService;
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

    @Autowired
    public HabitServiceImpl(IHabitRespository repository) {
        this.repository = repository;
    }

    @Override
    public List<GetHabitResponse> list() {
        return repository.findAll()
                .stream()
                .map(HabitMapper::toGetHabitResponse)
                .toList();
    }

    @Override
    public GetHabitResponse get(Long id) {
        Habit habit = repository.findById(id)
                .orElseThrow(() -> new HabitNotFoundException("Habit with id: " + id + " not found"));
        return HabitMapper.toGetHabitResponse(habit);
    }

    @Override
    public CreateHabitResponse create(CreateHabitRequest habitRequest) {
        // TODO: preguntar a conejo
        Habit habit = HabitMapper.map(habitRequest);

//        habit.set
        return null;
    }

    @Override
    public UpdateHabitResponse update(UpdateHabitRequest updateHabitRequest, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
