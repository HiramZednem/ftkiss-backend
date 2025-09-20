package com.codqueto.ftkiss.web.mappers;

import com.codqueto.ftkiss.entities.User;
import com.codqueto.ftkiss.web.dtos.request.CreateUserRequest;
import com.codqueto.ftkiss.web.dtos.response.CreateUserResponse;
import com.codqueto.ftkiss.web.dtos.response.GetUserResponse;
import com.codqueto.ftkiss.web.dtos.response.UpdateUserResponse;

import java.util.Random;

public class UserMapper {
    public static User map(CreateUserRequest userRequest, User user) {
        if (user == null) {
            user = new User();
            Random random = new Random();
            Long id = random.nextLong();

            if (id<0) {
                id = id*-1;
            }
            user.setUserId(id);
        }

        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setBirthdate(userRequest.getBirthdate());

        return user;
    }

    public static CreateUserResponse toCreateUserResponse(User user) {
        CreateUserResponse userResponse = new CreateUserResponse();

        userResponse.setUserId(user.getUserId());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setBirthdate(user.getBirthdate());

        return userResponse;
    }

    public static GetUserResponse toGetUserResponse(User user) {
        GetUserResponse userResponse = new GetUserResponse();

        userResponse.setUserId(user.getUserId());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setBirthdate(user.getBirthdate());

        return userResponse;
    }

    public static UpdateUserResponse toUpdateUserResponse(User user) {
        UpdateUserResponse userResponse = new UpdateUserResponse();

        userResponse.setUserId(user.getUserId());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setBirthdate(user.getBirthdate());

        return userResponse;
    }

}
