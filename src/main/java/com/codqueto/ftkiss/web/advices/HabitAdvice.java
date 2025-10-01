package com.codqueto.ftkiss.web.advices;

import com.codqueto.ftkiss.web.controllers.HabitController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(assignableTypes = HabitController.class)
public class HabitAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public String IdNotValidException(IllegalArgumentException e) {
        return e.getMessage();
    }
}
