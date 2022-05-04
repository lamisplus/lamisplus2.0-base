package org.lamisplus.modules.base;

import com.foreach.across.core.AcrossModule;
import com.foreach.across.core.context.configurer.ComponentScanConfigurer;
import com.foreach.across.core.transformers.BeanPrefixingTransformer;
import com.foreach.across.modules.hibernate.jpa.AcrossHibernateJpaModule;
import com.foreach.across.modules.web.AcrossWebModule;

import com.foreach.across.config.AcrossApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;

@AcrossApplication(
        modules = {
                AcrossWebModule.NAME
        })
@Slf4j
@EnableSwagger2
@EnableAsync
@EnableScheduling
public class BaseModule extends AcrossModule {
    public final static String NAME = "BaseModule";

    public static void main(String[] args) {
        SpringApplication.run(BaseModule.class, args);
    }

    public BaseModule() {
        super();
        addApplicationContextConfigurer(new ComponentScanConfigurer(getClass().getPackage().getName() + ".module",
                getClass().getPackage().getName() + ".configurer", getClass().getPackage().getName() + ".controller",
                getClass().getPackage().getName() + ".domain", getClass().getPackage().getName() + ".repository",
                getClass().getPackage().getName() + ".service", getClass().getPackage().getName() + ".yml",
                getClass().getPackage().getName() + ".util", getClass().getPackage().getName()+ ".security",
                getClass().getPackage().getName() + ".interceptor", getClass().getPackage().getName() + ".extensions", "org.springframework.web.socket"));
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return "Module containing LAMISPlus";    }
}
