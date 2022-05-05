package org.lamisplus.modules.base.configurer;


import com.blazebit.persistence.spring.data.impl.repository.BlazePersistenceRepositoryFactoryBean;
import lombok.extern.slf4j.Slf4j;
import org.lamisplus.modules.base.domain.BaseDomain;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = BaseDomain.class,
        repositoryFactoryBeanClass = BlazePersistenceRepositoryFactoryBean.class)
@Slf4j
public class DomainConfiguration {

}
