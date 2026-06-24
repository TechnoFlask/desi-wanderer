package in.co.technoflask.projects.desiwanderer.application.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationPropertiesScan(basePackages = "in.co.technoflask.projects.desiwanderer")
public class PropertiesConfig {}
