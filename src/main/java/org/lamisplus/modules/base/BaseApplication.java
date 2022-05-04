package org.lamisplus.modules.base;

import com.foreach.across.core.AcrossModule;
import com.foreach.across.core.context.configurer.ComponentScanConfigurer;
import com.foreach.across.core.transformers.BeanPrefixingTransformer;
import com.foreach.across.modules.hibernate.jpa.AcrossHibernateJpaModule;
import com.foreach.across.modules.hibernate.jpa.repositories.config.EnableAcrossJpaRepositories;
import com.foreach.across.modules.web.AcrossWebModule;

import com.foreach.across.config.AcrossApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@AcrossApplication(
        modules = {
                AcrossWebModule.NAME
                //AcrossHibernateJpaModule.NAME
        })
@Slf4j
@EnableSwagger2
@EnableAsync
@EnableScheduling
public class BaseApplication extends AcrossModule {
    public final static String NAME = "BaseModule";
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

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
    public DataSourceProperties baseDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource baseDataSource() {
        return baseDataSourceProperties().initializeDataSourceBuilder()
                .driverClassName(driverClassName)
                .password(password)
                .url(url)
                .username(username).build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean baseJpaEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(baseDataSource());
        em.setPackagesToScan(new String[]{"org.lamisplus.modules.base.domain.entity"});
        em.setPersistenceUnitName("base");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        //em.setJpaProperties();

        return em;
    }

    @Bean(name = "baseJpaTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier(value = "baseJpaEntityManagerFactory") EntityManagerFactory baseJpaEntityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(baseJpaEntityManagerFactory);

        return transactionManager;
    }

    @Bean(name = "baseJpaExceptionTranslation")
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public AcrossHibernateJpaModule baseJpaModule() {
        return AcrossHibernateJpaModule.builder().prefix( "base" ).build();
    }

    /*@Bean
    public DataSource syncDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername("postgres");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/lamis3-plus-liquibase-test");
        dataSource.setPassword("emeka");
        dataSource.setDriverClassName("org.postgresql.Driver");
        return dataSource;
    }

    @Bean
    public AcrossHibernateJpaModule syncJpaModule() {
        return AcrossHibernateJpaModule.builder().prefix( "sync" ).build();
    }*/

    /*@Bean
    //Reads database properties from the config.yml
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new FileSystemResource(modulePath + File.separator +"config.yml"));
        propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());
        propertySourcesPlaceholderConfigurer.setIgnoreResourceNotFound(true);
        return propertySourcesPlaceholderConfigurer;
    }*/
}
