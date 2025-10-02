package com.codqueto.ftkiss.services.impl;

import com.codqueto.ftkiss.persistance.entities.Habit;
import com.codqueto.ftkiss.persistance.entities.User;
import com.codqueto.ftkiss.persistance.repositories.IHabitRespository;
import com.codqueto.ftkiss.services.IUserService;
import com.codqueto.ftkiss.web.dtos.request.habit.CreateHabitRequest;
import com.codqueto.ftkiss.web.dtos.request.habit.UpdateHabitRequest;
import com.codqueto.ftkiss.web.dtos.response.habit.CreateHabitResponse;
import com.codqueto.ftkiss.web.dtos.response.habit.GetHabitResponse;
import com.codqueto.ftkiss.web.dtos.response.habit.UpdateHabitResponse;
import com.codqueto.ftkiss.web.dtos.response.user.GetUserResponse;
import com.codqueto.ftkiss.web.exceptions.HabitNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class HabitServiceImplTest {

    @InjectMocks
    private HabitServiceImpl habitService;

    @Mock
    private IHabitRespository habitRespository;

    @Mock
    private IUserService userService;


    // Get
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


    // Create
    @Test
    public void givenValidHabitRequest_whenCreateHabit_thenCreateHabitResponse() {
        CreateHabitRequest createHabitRequest = new CreateHabitRequest();
        createHabitRequest.setUserId(1L);
        createHabitRequest.setName("do unit test");
        createHabitRequest.setDescription("a simple description");

        User user = new User();
        user.setId(1L);
        user.setName("Hiram");
        user.setBirthdate(LocalDate.now());
        user.setPassword("adminadmin");
        user.setEmail("hirammendez@gmail.com");

        Habit habit = new Habit();
        habit.setId(1L);
        habit.setName(createHabitRequest.getName());
        habit.setDescription(createHabitRequest.getDescription());
        habit.setHidden(false);
        habit.setUser(user);

        when(userService.getUserById(anyLong())).thenReturn(user);
        when(habitRespository.save(any(Habit.class))).thenReturn(habit);

        CreateHabitResponse createHabitResponse = this.habitService.create(createHabitRequest);

        assertEquals( habit.getId(), createHabitResponse.getId());
        assertEquals( habit.getName(), createHabitResponse.getName());
        assertEquals(habit.getDescription(), createHabitResponse.getDescription());
        assertEquals(false, createHabitResponse.getHidden());
        assertEquals(habit.getUser().getId(), createHabitResponse.getUserId());
    }

    // Update
    @Test
    public void givenValidUpdateUserRequestAndIdLessThan0_whenUpdateHabit_thenThrowIllegalArgumentException() {
        UpdateHabitRequest updateHabitRequest = new UpdateHabitRequest();
        updateHabitRequest.setName("do update");
        updateHabitRequest.setDescription("do update description");
        updateHabitRequest.setHidden(true);

        assertThrows( IllegalArgumentException.class, () -> this.habitService.update(updateHabitRequest,0L));
    }

    @Test
    public void givenValidUpdateUserRequestAndIdGreaterThan0_whenUpdateHabitAndHabitNotfound_thenHabitNotFoundException() {
        UpdateHabitRequest updateHabitRequest = new UpdateHabitRequest();
        updateHabitRequest.setName("do update");
        updateHabitRequest.setDescription("do update description");
        updateHabitRequest.setHidden(true);

        when(this.habitRespository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(HabitNotFoundException.class, () -> this.habitService.update(updateHabitRequest, 1L));
    }

    @Test
    public void givenValidUpdateUserRequestAndIdGreaterThan0_whenUpdateHabitAndHabitFound_thenUpdateHabitResponse() {
        UpdateHabitRequest updateHabitRequest = new UpdateHabitRequest();
        updateHabitRequest.setName("do update");
        updateHabitRequest.setDescription("do update description");
        updateHabitRequest.setHidden(true);

        User user = new User();
        user.setId(1L);
        user.setName("Hiram");
        user.setBirthdate(LocalDate.now());
        user.setPassword("adminadmin");
        user.setEmail("hirammendez@gmail.com");

        Habit habit = new Habit();
        habit.setId(1L);
        habit.setName("habit");
        habit.setDescription("simple descripton");
        habit.setHidden(false);
        habit.setUser(user);

        when(this.habitRespository.findById(anyLong())).thenReturn(Optional.of(habit));
        when(this.habitRespository.save(any(Habit.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UpdateHabitResponse updateHabitResponse = this.habitService.update(updateHabitRequest, 1L);

        assertEquals(updateHabitRequest.getName(), updateHabitResponse.getName());
        assertEquals(updateHabitRequest.getDescription(), updateHabitResponse.getDescription());
        assertEquals(updateHabitRequest.getHidden(), updateHabitResponse.getHidden());




    }


    // Delete
    @Test
    public void givenIdLessThan0_whenDeleteHabit_thenThrowIllegalArgumentException() {
        assertThrows( IllegalArgumentException.class, () -> this.habitService.delete(0L));
    }

    @Test
    public void givenValidId_whenDeleteHabitAndHabitNotfound_thenHabitNotFoundException() {

        when( habitRespository.findById( anyLong() ) )
                .thenReturn(Optional.empty());

        assertThrows( HabitNotFoundException.class, () -> this.habitService.delete(1L));
    }

}