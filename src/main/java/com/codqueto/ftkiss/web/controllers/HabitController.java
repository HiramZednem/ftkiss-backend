package com.codqueto.ftkiss.web.controllers;

import com.codqueto.ftkiss.services.IHabitService;
import com.codqueto.ftkiss.web.dtos.request.habit.CreateHabitRequest;
import com.codqueto.ftkiss.web.dtos.request.habit.UpdateHabitRequest;
import com.codqueto.ftkiss.web.dtos.response.habit.CreateHabitResponse;
import com.codqueto.ftkiss.web.dtos.response.habit.GetHabitResponse;
import com.codqueto.ftkiss.web.dtos.response.habit.UpdateHabitResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("habit")
public class HabitController {

    private final IHabitService habitService;

    @Autowired()
    public HabitController(IHabitService habitService) {
        this.habitService = habitService;
    }

    @GetMapping("{habitId}")
    public ResponseEntity<GetHabitResponse> get(@PathVariable Long habitId) {
        return new ResponseEntity<GetHabitResponse> (this.habitService.get(habitId), HttpStatus.OK);
    }

    @PutMapping("{habitId}")
    public ResponseEntity<UpdateHabitResponse> update(@RequestBody @Validated UpdateHabitRequest updateHabitRequest, @PathVariable Long habitId) {
        return new ResponseEntity<>(this.habitService.update(updateHabitRequest, habitId), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CreateHabitResponse> create(@RequestBody @Validated CreateHabitRequest createHabitRequest) {
        return new ResponseEntity<CreateHabitResponse>(this.habitService.create(createHabitRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("{habitId}")
    public ResponseEntity<Void> delete(@PathVariable Long habitId) {
        this.habitService.delete(habitId);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
