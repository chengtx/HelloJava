package me.chengtx.webapp.jpa;

import org.apache.commons.dbcp2.BasicDataSource;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
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
@PropertySource("classpath:jdbc.properties")
public class JpaConfig {

    public static final String CF_USE = "cf.use";
    public static final String CF_INSTANCE_NAME = "cf.instance.name";
    public static final String CF_SERVICE_BROKER = "cf.service.name";
    public static final String JDBC_URL = "jdbc.url";
    public static final String JDBC_USERNAME = "jdbc.username";
    public static final String JDBC_PASSWORD = "jdbc.password";
    public static final String JDBC_DRIVER_CLASS_NAME = "jdbc.driverClassName";
    public static final String VCAP_SERVICES = "VCAP_SERVICES";
    public static final String SERVICE_INSTANCE_NAME = "name";
    public static final String SERVICE_CREDENTIALS = "credentials";
    public static final String SERVICE_USERNAME = "username";
    public static final String SERVICE_PASSWORD = "password";
    public static final String SERVICE_HOST = "hostname";
    public static final String SERVICE_PORT = "port";
    public static final String SERVICE_DB = "name";

    public static final String DBCP_INITIAL_SIZE =  "dbcp.initialSize";
    public static final String DBCP_MAX_TOTAL =  "dbcp.maxTotal";
    public static final String DBCP_MAX_IDLE =  "dbcp.maxIdle";
    public static final String DBCP_MIN_IDLE =  "dbcp.minIdle";
    public static final String DBCP_DEFAULT_AUTO_COMMIT =  "dbcp.defaultAutoCommit";
    public static final String DBCP_DEFAULT_READ_ONLY =  "dbcp.defaultReadOnly";
    public static final String DBCP_DEFAULT_TRANSACTION_ISOLATION =  "dbcp.defaultTransactionIsolation";


    @Autowired
    Environment env;

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
        if (Boolean.valueOf(env.getProperty(CF_USE))) {
            // parsing service instance from cloudfoundry broker
            String vcapService = env.getProperty(VCAP_SERVICES);
            if (vcapService != null) {
                JSONObject json = new JSONObject(vcapService);
                JSONArray serviceInstances = json.getJSONArray(env.getProperty(CF_SERVICE_BROKER));
                for (int i = 0; i < serviceInstances.length(); i++) {
                    JSONObject serviceInstance = serviceInstances.getJSONObject(i);
                    if (serviceInstance.getString(SERVICE_INSTANCE_NAME).equals(env.getProperty(CF_INSTANCE_NAME))) {
                        JSONObject credentials = serviceInstance.getJSONObject(SERVICE_CREDENTIALS);
                        String username = credentials.getString(SERVICE_USERNAME);
                        String password = credentials.getString(SERVICE_PASSWORD);
                        String host = credentials.getString(SERVICE_HOST);
                        String port = credentials.getString(SERVICE_PORT);
                        String db = credentials.getString(SERVICE_DB);
                        final String jdbcUrl = "jdbc:mysql://" + host + ":" + port + "/" + db + "?rewriteBatchedStatements=true";
                        bds.setUrl(jdbcUrl);
                        bds.setUsername(username);
                        bds.setPassword(password);
                        break;
                    }
                }
            } else {
                bds.setUrl(env.getProperty(JDBC_URL));
                bds.setUsername(env.getProperty(JDBC_USERNAME));
                bds.setPassword(env.getProperty(JDBC_PASSWORD));
            }
        } else {
            bds.setUrl(env.getProperty(JDBC_URL));
            bds.setUsername(env.getProperty(JDBC_USERNAME));
            bds.setPassword(env.getProperty(JDBC_PASSWORD));
        }
        bds.setDriverClassName(env.getProperty(JDBC_DRIVER_CLASS_NAME));
        // dbcp connection pool configuration
        bds.setInitialSize(Integer.parseInt(env.getProperty(DBCP_INITIAL_SIZE)));
        bds.setMaxTotal(Integer.parseInt(env.getProperty(DBCP_MAX_TOTAL)));
        bds.setMaxIdle(Integer.parseInt(env.getProperty(DBCP_MAX_IDLE)));
        bds.setMinIdle(Integer.parseInt(env.getProperty(DBCP_MIN_IDLE)));
        bds.setDefaultAutoCommit(Boolean.valueOf(env.getProperty(DBCP_DEFAULT_AUTO_COMMIT)));
        bds.setDefaultReadOnly(Boolean.valueOf(env.getProperty(DBCP_DEFAULT_READ_ONLY)));
        bds.setDefaultTransactionIsolation(Integer.parseInt(env.getProperty(DBCP_DEFAULT_TRANSACTION_ISOLATION)));

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
