package com.codqueto.ftkiss.web.controllers;

import com.codqueto.ftkiss.entities.User;
import com.codqueto.ftkiss.services.UserService;
import com.codqueto.ftkiss.web.dtos.request.CreateUserRequest;
import com.codqueto.ftkiss.web.dtos.request.UpdateUserRequest;
import com.codqueto.ftkiss.web.dtos.response.CreateUserResponse;
import com.codqueto.ftkiss.web.dtos.response.GetUserResponse;
import com.codqueto.ftkiss.web.dtos.response.UpdateUserResponse;
import com.codqueto.ftkiss.web.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<GetUserResponse>> list() {
        return new ResponseEntity<>(this.userService.list(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetUserResponse> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.userService.get(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CreateUserResponse> create(@RequestBody @Validated CreateUserRequest createUserRequest) {
        return new ResponseEntity<>(this.userService.create(createUserRequest), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateUserResponse> update(@RequestBody @Validated UpdateUserRequest updateUserRequest, @PathVariable("id") Long id) {
        return new ResponseEntity<>(this.userService.update(updateUserRequest, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping()
    public String updateSpecific() {
        return "update specific";
    }

}
