package com.codqueto.ftkiss.web.controllers;

import com.codqueto.ftkiss.persistance.entities.Habit;
import com.codqueto.ftkiss.services.IHabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user/{id}/habit")
public class HabitController {

    private final IHabitService habitService;

    @Autowired
    public HabitController(IHabitService habitService) {
        this.habitService = habitService;
    }
    @GetMapping
    public List<Habit> list() {
        return this.habitService.list();
    }
}
