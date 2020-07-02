package com.computervision.mushrooms.controller;

import com.computervision.mushrooms.service.computervision.ComputerVisionService;
import com.computervision.mushrooms.service.computervision.azure.model.CVAzurePrediction;
import com.computervision.mushrooms.service.computervision.azure.model.CVAzureResult;
import com.computervision.mushrooms.service.source.WikipediaSourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MushroomsController {

    private final ComputerVisionService computerVisionService;
    private final WikipediaSourceService wikipediaSourceService;

    @GetMapping
    public String getHome() {
        return "home";
    }

    @PostMapping
    public ModelAndView uploadMushroomsPicture(@RequestParam("mushroomPicture") MultipartFile multipart) throws IOException {

        CVAzureResult result = computerVisionService.uploadPicture(multipart);

        ModelAndView mav = new ModelAndView("result");
        List<CVAzurePrediction> predictionList = Arrays.asList(result.getPredictions()).stream()
                .filter(prediction -> prediction.getProbability() > 0.0001)
                .map(pre -> {
                    CVAzurePrediction prediction = new CVAzurePrediction();
                    prediction.setProbability(Math.round(pre.getProbability()*10000)/100.00);
                    prediction.setTagName(pre.getTagName());
                    prediction.setWikiUrl(wikipediaSourceService.getUrlSource(pre.getTagName()));
                    return prediction;
                })
                .collect(Collectors.toList());
        mav.addObject("predictions", predictionList);
        mav.addObject("name", multipart.getOriginalFilename());
        mav.addObject("picture", Base64.getEncoder().encodeToString(multipart.getBytes()));
        return mav;
    }

}
