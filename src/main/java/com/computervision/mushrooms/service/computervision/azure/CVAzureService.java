package com.computervision.mushrooms.service.computervision.azure;

import com.computervision.mushrooms.service.computervision.ComputerVisionService;
import com.computervision.mushrooms.service.computervision.azure.model.CVAzurePrediction;
import com.computervision.mushrooms.service.computervision.azure.model.CVAzureResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
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
    public CVAzureResult uploadPicture(final MultipartFile multipartFile) throws IOException {
        String url = String.format("%s%s", properties.getHost(), properties.getPath());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.set("Prediction-Key", properties.getPredictionKey());
        byte[] bytes = multipartFile.getBytes();
        HttpEntity<byte[]> request = new HttpEntity<>(bytes, headers);

        CVAzureResult result = restTemplate.postForObject(url, request, CVAzureResult.class);

        return result;
    }

}
