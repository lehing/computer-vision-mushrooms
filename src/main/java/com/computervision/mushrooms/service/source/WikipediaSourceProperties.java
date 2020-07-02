package com.computervision.mushrooms.service.source;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

@Data
@ConfigurationPropertiesBinding
@ConfigurationProperties(prefix = "wikipedia")
public class WikipediaSourceProperties {
    private String host;
    private String queryAction;
    private String querySearch;
    private String queryLimit;
    private String queryNamespace;
    private String queryFormat;
}
