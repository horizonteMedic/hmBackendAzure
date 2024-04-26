package backendhm.serviciosRest;

import com.google.common.base.Preconditions;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "azureEntityManagerFactory", transactionManagerRef = "azureTransactionManagerFactory", basePackages = {"backendhm.serviciosRest.models.azure.repository"})
public class ConfigBDAzure {
    @Autowired
    private Environment env;

    @Bean(name =  "azureDataSource")
    public DataSource azureDatasource(){

        HikariConfig config=new HikariConfig();
        config.setDriverClassName(Preconditions.checkNotNull(env.getProperty("azure.datasource.driver-class-name")));
        config.setJdbcUrl(env.getProperty("azure.datasource.jdbc-url"));
        config.setMaximumPoolSize(Integer.parseInt(env.getProperty("azure.datasource.hikari.maximum-pool-size")));
        config.setMaxLifetime(Long.parseLong(env.getProperty("azure.datasource.hikari.max-lifetime")));
        config.setPoolName(env.getProperty("hikariCP.datasource.pool_name"));
        config.setConnectionTimeout(Long.parseLong(env.getProperty("azure.datasource.hikari.connection-timeout")));
        config.setMinimumIdle(Integer.parseInt(env.getProperty("azure.datasource.hikari.minimum-idle")));
        config.setIdleTimeout(Long.parseLong(env.getProperty("azure.datasource.hikari.idle-timeout")));
        config.setAutoCommit(Boolean.getBoolean(env.getProperty("azure.datasource.hikari.auto-commit")));

        //DriverManagerDataSource dataSource=new DriverManagerDataSource();

        //dataSource.setUrl(env.getProperty("azure.datasource.jdbc-url"));
        //dataSource.setDriverClassName(env.getProperty("azure.datasource.driver-class-name"));
        return new HikariDataSource(config);
    }

    @Bean("azureEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean em=new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(azureDatasource());
        em.setPackagesToScan("backendhm.serviciosRest.models.azure.entity");

        HibernateJpaVendorAdapter vendorAdapter=new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Map<String, Object> properties=new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",env.getProperty("azure.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.show-sql",env.getProperty("azure.jpa.show-sql"));
        properties.put("hibernate.dialect",env.getProperty("azure.jpa.database-platform"));
        properties.put("hibernate.format_sql", env.getProperty("azure.jpa.properties.hibernate.format_sql"));

        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean(name = "azureTransactionManagerFactory")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager    transactionManager = new JpaTransactionManager();
            transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return transactionManager;
    }
}
