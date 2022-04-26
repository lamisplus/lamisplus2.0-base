package org.lamisplus.modules.base.configurer;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"org.lamisplus.modules.base.repositories"})
public class DomainConfiguration {
}
