//package com.example.demo.config;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
//
//import javax.sql.DataSource;
//
//
//@Configuration
//public class H2Configuration {
//    private static final Logger LOGGER = LoggerFactory.getLogger(H2Configuration.class);
//
//    @Bean
//    public DataSource createDataSourceForH2() {
//        DataSource dataSource = new EmbeddedDatabaseBuilder()
//                .setType(EmbeddedDatabaseType.H2)
//                .setName("testdb;MODE=MySQL")
//                .build();
//
//        LOGGER.info("createDataSourceForH2 - created OK");
//
//        return dataSource;
//    }
//}