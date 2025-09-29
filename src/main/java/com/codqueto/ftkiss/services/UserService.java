package com.codqueto.ftkiss.services;

import com.codqueto.ftkiss.persistance.entities.User;
import com.codqueto.ftkiss.persistance.repositories.IUserRepository;
import com.codqueto.ftkiss.web.dtos.request.user.CreateUserRequest;
import com.codqueto.ftkiss.web.dtos.request.user.UpdateUserRequest;
import com.codqueto.ftkiss.web.dtos.response.user.CreateUserResponse;
import com.codqueto.ftkiss.web.dtos.response.user.GetUserResponse;
import com.codqueto.ftkiss.web.dtos.response.user.UpdateUserResponse;
import com.codqueto.ftkiss.web.exceptions.UserNotFoundException;
import com.codqueto.ftkiss.web.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final IUserRepository repository;

    @Autowired
    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    public List<GetUserResponse> list() {
        return this.repository.findAll()
                .stream()
                .map(UserMapper::toGetUserResponse)
                .toList();
    }

    public GetUserResponse get(Integer id) {
        User user = this.repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found"));

        return UserMapper.toGetUserResponse(user);
    }

    public CreateUserResponse create(CreateUserRequest createUserRequest) {
        User user = UserMapper.map(createUserRequest);
        return UserMapper.toCreateUserResponse(this.repository.save(user));
    }

    public UpdateUserResponse update(UpdateUserRequest updateUserRequest, Integer id) {
        User user = this.repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found"));

        User userSaved = this.repository.save(UserMapper.map(updateUserRequest, user));
        return UserMapper.toUpdateUserResponse(userSaved);
    }

    public void delete(Integer id) {
        User user = this.repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found"));

        this.repository.delete(user);
    }
}
