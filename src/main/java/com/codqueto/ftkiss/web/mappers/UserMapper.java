package com.codqueto.ftkiss.web.mappers;

import com.codqueto.ftkiss.persistance.entities.User;
import com.codqueto.ftkiss.web.dtos.request.user.CreateUserRequest;
import com.codqueto.ftkiss.web.dtos.request.user.UpdateUserRequest;
import com.codqueto.ftkiss.web.dtos.response.user.CreateUserResponse;
import com.codqueto.ftkiss.web.dtos.response.user.GetUserResponse;
import com.codqueto.ftkiss.web.dtos.response.user.UpdateUserResponse;


public class UserMapper {
    public static User map(CreateUserRequest userRequest) {
        User user = new User();

        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setBirthdate(userRequest.getBirthdate());

        return user;
    }

    public static User map(UpdateUserRequest userRequest, User user) {

        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setBirthdate(userRequest.getBirthdate());

        return user;
    }

    public static CreateUserResponse toCreateUserResponse(User user) {
        CreateUserResponse userResponse = new CreateUserResponse();

        userResponse.setUserId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setBirthdate(user.getBirthdate());

        return userResponse;
    }

    public static GetUserResponse toGetUserResponse(User user) {
        GetUserResponse userResponse = new GetUserResponse();

        userResponse.setUserId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setBirthdate(user.getBirthdate());

        return userResponse;
    }

    public static UpdateUserResponse toUpdateUserResponse(User user) {
        UpdateUserResponse userResponse = new UpdateUserResponse();

        userResponse.setUserId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setBirthdate(user.getBirthdate());

        return userResponse;
    }

}
