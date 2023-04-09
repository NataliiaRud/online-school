package ua.study.school.configuration;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import java.util.Properties;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:config.properties")
@ComponentScan("ua.study.school")
@EnableTransactionManagement
public class ApplicationConfiguration {
    @Autowired
    private Environment env;

    @Bean
    public ApplicationProperties applicationProperties() {
        ApplicationProperties applicationProperties = new ApplicationProperties();

        applicationProperties.setDriver(env.getProperty("db.driver"));
        applicationProperties.setPassword(env.getProperty("db.password"));
        applicationProperties.setUrl(env.getProperty("db.url"));
        applicationProperties.setUser(env.getProperty("db.user"));

        return applicationProperties;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("ua.study.school.models");
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = new DriverManagerDataSource(
                env.getProperty("db.url"),
                env.getProperty("db.user"),
                env.getProperty("db.password"));
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        // hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

        return hibernateProperties;
    }
}
