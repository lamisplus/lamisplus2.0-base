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
public class BaseApplication extends AcrossHibernateJpaModule {
    public final static String NAME = "BaseModule";

    public static void main(String[] args) {
        SpringApplication.run(BaseModule.class, args);
    }

    public BaseApplication() {
        super();
        addApplicationContextConfigurer(new ComponentScanConfigurer(
                getClass().getPackage().getName() + ".configurer",
                getClass().getPackage().getName() + ".controller",
                getClass().getPackage().getName() + ".domain",
                getClass().getPackage().getName() + ".domain.entity",
                getClass().getPackage().getName() + ".domain.mapper",
                getClass().getPackage().getName() + ".repository",
                getClass().getPackage().getName() + ".service",
                getClass().getPackage().getName() + ".yml",
                getClass().getPackage().getName() +".installers",
                getClass().getPackage().getName() + ".util",
                getClass().getPackage().getName()+ ".security",
                getClass().getPackage().getName() + ".interceptor",
                getClass().getPackage().getName() + ".extensions",
                getClass().getPackage().getName() + ".module",
                "org.springframework.web.socket"));

        setPropertiesPrefix( "base" );
        setExposeTransformer( new BeanPrefixingTransformer( "base" ) );
        setPrimary( true );
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return "Module containing LAMISPlus";    }

    //runtime JPA module and datasource
    @Bean
    @Primary
    @ConfigurationProperties("org.lamisplus.modules.base")
    public DataSourceProperties baseDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("org.lamisplus.modules.base")
    public DataSource baseDataSource() {
        return baseDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    public AcrossHibernateJpaModule baseJpaModule() {
        return AcrossHibernateJpaModule.builder().prefix( "base" ).build();
    }
}