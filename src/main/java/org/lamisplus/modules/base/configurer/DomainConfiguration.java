package org.lamisplus.modules.base.configurer;


import com.foreach.across.core.annotations.Exposed;
import lombok.extern.slf4j.Slf4j;
import org.lamisplus.modules.base.domain.BaseDomain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManager;
import java.util.List;

@Configuration
@EnableJpaRepositories(basePackageClasses = BaseDomain.class)
@Slf4j
public class DomainConfiguration {

}
