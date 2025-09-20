package com.codqueto.ftkiss.services;

import com.codqueto.ftkiss.entities.User;
import com.codqueto.ftkiss.web.dtos.request.CreateUserRequest;
import com.codqueto.ftkiss.web.dtos.request.UpdateUserRequest;
import com.codqueto.ftkiss.web.dtos.response.CreateUserResponse;
import com.codqueto.ftkiss.web.dtos.response.GetUserResponse;
import com.codqueto.ftkiss.web.dtos.response.UpdateUserResponse;
import com.codqueto.ftkiss.web.mappers.UserMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final Map<Long, User> users;

    public UserService() {
        this.users = new HashMap<>();
    }

    @PostConstruct
    private void init() {
        User user1 = new User();
        user1.setUserId(1L);
        user1.setName("Hiram");
        user1.setEmail("hirammendez000@gmail.com");
        user1.setPassword("adminadmin");
        user1.setBirthdate(LocalDate.of(2003,5,7));

        User user2 = new User();
        user2.setUserId(2L);
        user2.setName("Mara");
        user2.setEmail("mararobles04@gmail.com");
        user2.setPassword("adminadmin");
        user2.setBirthdate(LocalDate.of(2002,4,2));

        this.users.put(1L,user1);
        this.users.put(2L,user2);
    }

    public List<GetUserResponse> list() {
        return this.users.values()
                .stream()
                .map(UserMapper::toGetUserResponse)
                .toList();
    }

    public GetUserResponse get(Long id) {
        User user = this.users.get(id);

        if(user == null) {
            // TODO: throw error;
        }

        return UserMapper.toGetUserResponse(user);
    }

    public CreateUserResponse create(CreateUserRequest createUserRequest) {
        User user = UserMapper.map(createUserRequest);
        this.users.put(user.getUserId(), user);

        return UserMapper.toCreateUserResponse(user);
    }

    public UpdateUserResponse update(UpdateUserRequest updateUserRequest, Long id) {
        User user = this.users.get(id);

        if(user == null) {
            // TODO: throw error;
        }

        UserMapper.map(updateUserRequest, user);
        return UserMapper.toUpdateUserResponse(user);
    }

    public void delete(Long id) {
        this.users.remove(id);
    }
}
