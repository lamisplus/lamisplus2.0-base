package org.lamisplus.modules.base.configurer;


import org.lamisplus.modules.base.domain.entity.BaseDomain;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(transactionManagerRef = "baseJpaTransactionManager",
        entityManagerFactoryRef = "baseEntityManagerFactory",
        basePackages = {"org.lamisplus.modules.base.repository"})
public class BaseDomainConfiguration {
}
