package com.computervision.mushrooms.service.azure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

@Data
@ConfigurationPropertiesBinding
@ConfigurationProperties(prefix = "azure")
public class CVAzureServiceProperties {

    private String predictionKey;
    private String host;
    private String path;

}
