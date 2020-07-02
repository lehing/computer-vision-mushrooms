package com.computervision.mushrooms.service.computervision;

import com.computervision.mushrooms.service.computervision.azure.model.CVAzureResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

public interface ComputerVisionService {

    CVAzureResult uploadPicture(final MultipartFile multipartFile) throws IOException;

}
