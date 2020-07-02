package com.computervision.mushrooms.service.source;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class WikipediaSourceService {

    private final RestTemplate restTemplate;
    private final WikipediaSourceProperties properties;

    public String getUrlSource(final String name) {
        UriComponents uri = UriComponentsBuilder
                .fromHttpUrl(properties.getHost())
                .queryParam(properties.getQuerySearch(), name)
                .queryParam(properties.getQueryAction(), "opensearch")
                .queryParam(properties.getQueryLimit(), "1")
                .queryParam(properties.getQueryNamespace(), "0")
                .queryParam(properties.getQueryFormat(), "json")
                .build();
        String result = restTemplate.getForObject(uri.toUri(), String.class);
        String init = String.format("[\"%s\",", name);
        String end = "]";
        String[] parts = result.replace(init, "").split(",");
        String url = parts[2].replace("[\"", "").replace("\"]]","");
        return "[]]".equals(url) ? null : url;
    }

}
