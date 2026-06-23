package in.co.technoflask.projects.desiwanderer.security.config;

import in.co.technoflask.projects.desiwanderer.security.service.SecurityService;
import org.springframework.context.annotation.Bean;

public class SecurityServiceConfig {

  @Bean
  SecurityService securityService() {
    return new SecurityService();
  }
}
