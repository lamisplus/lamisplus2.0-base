package org.lamisplus.modules.base.configurer;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.lamisplus.modules.base.ModuleDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@Slf4j
@Setter
@Getter
@ConfigurationProperties(prefix = "modules")
public class DataSourceConfig {

    private String name;
    private String driverClassName;

    private  String username;

    private String password;

    private String url;

    private DataSourceProperties properties;

//    public DataSourceProperties dataSourceProperties() {
//        return new DataSourceProperties ();
//    }


//    @ModuleDataSource
//    public DataSource dataSource() {
//        try {
//            log.info ("data source properties from config {}", driverClassName);
//            return properties
//                    .initializeDataSourceBuilder ()
//                    .type (HikariDataSource.class)
//                    .build ();
//        } catch (Exception exception) {
//            exception.printStackTrace ();
//        }
//        return null;
//    }

    @ModuleDataSource
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName (driverClassName);
        dataSourceBuilder.password (password);
        dataSourceBuilder.username (username);
        dataSourceBuilder.url (url);
        dataSourceBuilder.type (HikariDataSource.class);
        return dataSourceBuilder.build ();
    }



}
