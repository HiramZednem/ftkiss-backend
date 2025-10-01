package com.codqueto.ftkiss.persistance.repositories;

import com.codqueto.ftkiss.persistance.entities.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IHabitRespository extends JpaRepository<Habit,Long> {
    List<Habit> getHabitsByUserId(Long id);
}
