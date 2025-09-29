package com.codqueto.ftkiss.services.impl;

import com.codqueto.ftkiss.persistance.entities.User;
import com.codqueto.ftkiss.persistance.repositories.IUserRepository;
import com.codqueto.ftkiss.services.IUserService;
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
public class UserServiceImpl implements IUserService {

    private final IUserRepository repository;

    @Autowired
    public UserServiceImpl(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<GetUserResponse> list() {
        return this.repository.findAll()
                .stream()
                .map(UserMapper::toGetUserResponse)
                .toList();
    }

    @Override
    public GetUserResponse get(Long id) {
        User user = this.repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found"));

        return UserMapper.toGetUserResponse(user);
    }

    @Override
    public CreateUserResponse create(CreateUserRequest createUserRequest) {
        User user = UserMapper.map(createUserRequest);
        return UserMapper.toCreateUserResponse(this.repository.save(user));
    }

    @Override
    public UpdateUserResponse update(UpdateUserRequest updateUserRequest, Long id) {
        User user = this.repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found"));

        User userSaved = this.repository.save(UserMapper.map(updateUserRequest, user));
        return UserMapper.toUpdateUserResponse(userSaved);
    }

    @Override
    public void delete(Long id) {
        User user = this.repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found"));

        this.repository.delete(user);
    }

    @Override
    public User getUserById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found"));
    }
}
