package com.codqueto.ftkiss.services;

import com.codqueto.ftkiss.persistance.entities.User;
import com.codqueto.ftkiss.web.dtos.request.user.CreateUserRequest;
import com.codqueto.ftkiss.web.dtos.request.user.UpdateUserRequest;
import com.codqueto.ftkiss.web.dtos.response.user.CreateUserResponse;
import com.codqueto.ftkiss.web.dtos.response.user.GetUserResponse;
import com.codqueto.ftkiss.web.dtos.response.user.UpdateUserResponse;

import java.util.List;

public interface IUserService {

    List<GetUserResponse> list();

    GetUserResponse get(Long id);

    CreateUserResponse create(CreateUserRequest createUserRequest);

    UpdateUserResponse update(UpdateUserRequest updateUserRequest, Long id);

    void delete(Long id);

    User getUserById(Long id);
}
