package com.codqueto.ftkiss.web.advices;

import com.codqueto.ftkiss.web.controllers.UserController;
import com.codqueto.ftkiss.web.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice( assignableTypes = UserController.class)
public class UserAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public String UserNotFoundException(UserNotFoundException exception) {
        return  exception.getMessage();
    }
}
