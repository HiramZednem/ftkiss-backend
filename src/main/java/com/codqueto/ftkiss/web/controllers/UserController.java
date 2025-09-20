package com.codqueto.ftkiss.web.controllers;

import com.codqueto.ftkiss.entities.User;
import com.codqueto.ftkiss.web.dtos.request.CreateUserRequest;
import com.codqueto.ftkiss.web.dtos.response.CreateUserResponse;
import com.codqueto.ftkiss.web.dtos.response.GetUserResponse;
import com.codqueto.ftkiss.web.dtos.response.UpdateUserResponse;
import com.codqueto.ftkiss.web.mappers.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    private final Map<Long,User> users;

    public UserController() {
        this.users = new HashMap<>();

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

    @GetMapping
    public ResponseEntity<List<GetUserResponse>> list() {
        List<GetUserResponse> responses = this.users.values().stream().map(
                UserMapper::toGetUserResponse
        ).toList();

        return new ResponseEntity(responses, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetUserResponse> get(@PathVariable("id") Long id) {
        User user = this.users.get(id);

        if(user == null) {
            // TODO: throw error;
        }

        return new ResponseEntity(UserMapper.toGetUserResponse(user), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CreateUserResponse> create(@RequestBody CreateUserRequest createUserRequest) {
        User user = UserMapper.map(createUserRequest, null);
        this.users.put(user.getUserId(), user);

        return new ResponseEntity(UserMapper.toCreateUserResponse(user), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateUserResponse> update(@RequestBody CreateUserRequest userRequest, @PathVariable("id") Long id) {
        User user = this.users.get(id);

        if(user == null) {
            // TODO: throw error;
        }

        UserMapper.map(userRequest, user);

        return new ResponseEntity(UserMapper.toUpdateUserResponse(user), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.users.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping()
    public String updateSpecific() {
        return "update specific";
    }

}
