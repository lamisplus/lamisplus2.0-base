package org.lamisplus.modules.base.configurer;


import com.foreach.across.modules.hibernate.jpa.repositories.config.EnableAcrossJpaRepositories;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableAcrossJpaRepositories(transactionManagerRef = "baseJpaTransactionManager",
        entityManagerFactoryRef = "baseJpaEntityManagerFactory",
        basePackages = {"org.lamisplus.modules.base.repository"})
public class BaseDomainConfiguration {
}
