package com.computervision.mushrooms.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@ControllerAdvice
public class HandlingErrorController {

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ModelAndView handle404Exception(HttpClientErrorException.BadRequest badRequest) {
        ModelAndView maw = new ModelAndView("home");
        maw.addObject("error", "true");
        String[] messages = badRequest.getMessage().split(":");
        maw.addObject("errorStatus", messages[0]);
        String message = String.join(":", Arrays.copyOfRange(messages, 1, messages.length));
        maw.addObject("errorMessage", message);
        return maw;
    }

}
