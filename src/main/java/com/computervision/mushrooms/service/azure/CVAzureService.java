package com.computervision.mushrooms.service.azure;

import com.computervision.mushrooms.service.ComputerVisionService;
import com.computervision.mushrooms.service.azure.model.CVAzurePrediction;
import com.computervision.mushrooms.service.azure.model.CVAzureResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CVAzureService implements ComputerVisionService {

    private final CVAzureServiceProperties properties;
    private final RestTemplate restTemplate;

    @Override
    public ModelAndView uploadPicture(MultipartFile multipartFile) throws IOException {
        String url = String.format("%s%s", properties.getHost(), properties.getPath());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.set("Prediction-Key", properties.getPredictionKey());
        byte[] bytes = multipartFile.getBytes();
        HttpEntity<byte[]> request = new HttpEntity<>(bytes, headers);

        CVAzureResult result = restTemplate.postForObject(url, request, CVAzureResult.class);

        ModelAndView mav = new ModelAndView("result");
        List<CVAzurePrediction> predictionList = Arrays.asList(result.getPredictions()).stream()
                .map(pre -> {
                    CVAzurePrediction prediction = new CVAzurePrediction();
                    prediction.setProbability(Math.round(pre.getProbability()*10000)/100.00);
                    prediction.setTagName(pre.getTagName());
                    return prediction;
                })
                .collect(Collectors.toList());
        mav.addObject("predictions", predictionList);
        mav.addObject("name", multipartFile.getOriginalFilename());
        mav.addObject("picture", Base64.getEncoder().encodeToString(bytes));

        return mav;
    }

}
