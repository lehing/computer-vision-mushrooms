package com.computervision.mushrooms.controller;

import com.computervision.mushrooms.service.ComputerVisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class MushroomsController {

    private final ComputerVisionService computerVisionService;

    @GetMapping
    public String getHome() {
        return "home";
    }

    @PostMapping
    public ModelAndView uploadMushroomsPicture(@RequestParam("mushroomPicture") MultipartFile multipart) throws IOException {
        return computerVisionService.uploadPicture(multipart);
    }

}
