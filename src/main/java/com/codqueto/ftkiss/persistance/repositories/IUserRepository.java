package com.codqueto.ftkiss.persistance.repositories;

import com.codqueto.ftkiss.persistance.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {
}
