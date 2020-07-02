package com.computervision.mushrooms;

import com.computervision.mushrooms.service.computervision.azure.CVAzureServiceProperties;
import com.computervision.mushrooms.service.source.WikipediaSourceProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableConfigurationProperties({CVAzureServiceProperties.class, WikipediaSourceProperties.class})
public class MushroomsApplication {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}


	public static void main(String[] args) {
		SpringApplication.run(MushroomsApplication.class, args);
	}

}
