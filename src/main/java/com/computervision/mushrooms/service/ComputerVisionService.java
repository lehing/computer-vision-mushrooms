package com.computervision.mushrooms.service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

public interface ComputerVisionService {

    ModelAndView uploadPicture(MultipartFile multipartFile) throws IOException;

}
