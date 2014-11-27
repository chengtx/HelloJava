package me.chengtx.webapp.jpa;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.OpenJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;

/**
 * @author <a href="mailto:chengtingxian@gmail.com">Tingxian Cheng</a>
 * @version 11/27/2014
 */
@Configuration
public class JpaConfig {

    @Bean
    public OpenJpaVendorAdapter jpaVendorAdapter() {
        OpenJpaVendorAdapter jpaVendorAdapter = new OpenJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.MYSQL);
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setShowSql(true);
        return jpaVendorAdapter;
    }

    @Bean(destroyMethod = "close")
    public BasicDataSource dataSource() {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("com.mysql.jdbc.Driver");
        bds.setUrl("jdbc:mysql://10.32.127.164:3306/webappdb?rewriteBatchedStatements=true");
        bds.setUsername("root");
        bds.setPassword("c1oudc0w");
        return bds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPersistenceUnitName("mysql");
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter());
        entityManagerFactory.setPersistenceXmlLocation("classpath:META-INF/persistence.xml");
        return entityManagerFactory;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }

}
