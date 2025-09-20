package com.codqueto.ftkiss.web.advices;

import com.codqueto.ftkiss.web.controllers.UserController;
import com.codqueto.ftkiss.web.exceptions.UserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice( assignableTypes = UserController.class)
public class UserAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFound.class)
    public String UserNotFoundException(UserNotFound exception) {
        return  exception.getMessage();
    }
}
