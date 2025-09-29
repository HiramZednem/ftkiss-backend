package com.codqueto.ftkiss.web.controllers;

import com.codqueto.ftkiss.services.IHabitService;
import com.codqueto.ftkiss.web.dtos.request.habit.CreateHabitRequest;
import com.codqueto.ftkiss.web.dtos.response.habit.CreateHabitResponse;
import com.codqueto.ftkiss.web.dtos.response.habit.GetHabitResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user/{userId}/habit")
public class HabitController {

    private final IHabitService habitService;

    @Autowired
    public HabitController(IHabitService habitService) {
        this.habitService = habitService;
    }
    @GetMapping
    public List<GetHabitResponse> list(@PathVariable Long userId) {
        return this.habitService.list(userId);
    }

    @PostMapping
    public ResponseEntity<CreateHabitResponse> create(@RequestBody @Validated CreateHabitRequest userRequest, @PathVariable Long userId) {
        return new ResponseEntity<CreateHabitResponse>(this.habitService.create(userRequest, userId), HttpStatus.CREATED);
    }




}
