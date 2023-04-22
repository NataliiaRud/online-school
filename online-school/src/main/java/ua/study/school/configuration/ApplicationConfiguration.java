package ua.study.school.configuration;

import org.h2.jdbcx.JdbcDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import java.util.Properties;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:config.properties")
@ComponentScan("ua.study.school")
@EnableTransactionManagement
@EnableJpaRepositories("ua.study.school.repository")
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
    public DataSource dataSource() {
        DataSource dataSource = new DriverManagerDataSource(
                env.getProperty("db.url"),
                env.getProperty("db.user"),
                env.getProperty("db.password"));
        return dataSource;
    }

    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        // hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        hibernateProperties.setProperty("hibernate.cache.use_second_level_cache", "true");
        hibernateProperties.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");

        return hibernateProperties;
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean lfb = new LocalContainerEntityManagerFactoryBean();
        lfb.setDataSource(dataSource());
        lfb.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        lfb.setPackagesToScan("ua.study.school.models");
        lfb.setJpaProperties(hibernateProperties());
        return lfb;
    }

    @Bean
    JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}
