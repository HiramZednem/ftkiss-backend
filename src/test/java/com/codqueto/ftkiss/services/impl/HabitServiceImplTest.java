package com.codqueto.ftkiss.services.impl;

import com.codqueto.ftkiss.persistance.entities.Habit;
import com.codqueto.ftkiss.persistance.repositories.IHabitRespository;
import com.codqueto.ftkiss.web.dtos.request.habit.CreateHabitRequest;
import com.codqueto.ftkiss.web.dtos.response.habit.GetHabitResponse;
import com.codqueto.ftkiss.web.dtos.response.user.GetUserResponse;
import com.codqueto.ftkiss.web.exceptions.HabitNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class HabitServiceImplTest {

    @InjectMocks
    private HabitServiceImpl habitService;

    @Mock
    private IHabitRespository habitRespository;

    @Test
    public void givenIdLessThan0_whenGetHabit_thenThrowsIllegalArgumentException() {
        Long id = -1L;

        assertThrows(IllegalArgumentException.class, () -> habitService.get(id));
    }

    @Test
    public void givenIdGreaterThan0_whenGetHabitAndHabitFound_thenGetHabitResponse() {
        // data preparation
        Long id  = 1L;

        Habit habit = new Habit();
        habit.setId(id);
        habit.setName("test name");
        habit.setDescription("test description");
        habit.setHidden(false);
        habit.setUser(null);

        when(habitRespository.findById(anyLong())).thenReturn(Optional.of(habit));

        GetHabitResponse habitResponse = habitService.get(id);
        // asserts
        assertNotNull(habitResponse);
        assertEquals(habit.getId(), habitResponse.getId());
        assertEquals(habit.getName(), habitResponse.getName());
        assertEquals(habit.getHidden(), habitResponse.getHidden());
        assertEquals(habit.getDescription(), habitResponse.getDescription());
    }

    @Test
    public void givenIdGreaterThan0_whenGetHabitAndHabitNotFound_theThrowsHabitNotFoundException() {
        Long id  = 1L;

        when(habitRespository.findById(anyLong())).thenReturn(Optional.ofNullable(null));

        assertThrows(HabitNotFoundException.class, () -> habitService.get(id));
    }

    @Test
    public void givenValidHabitRequest_whenCreateHabit_thenCreateHabitResponse() {
        CreateHabitRequest createHabitRequest = new CreateHabitRequest();
        createHabitRequest.setName("do unit test");
        createHabitRequest.setDescription("a simple description");


    }
}