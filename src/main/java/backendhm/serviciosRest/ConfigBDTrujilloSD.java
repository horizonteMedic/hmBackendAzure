package backendhm.serviciosRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
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
@EnableJpaRepositories(entityManagerFactoryRef = "trujilloSDEntityManagerFactory", transactionManagerRef = "trujilloSDTransactionManagerFactory", basePackages = {"backendhm.serviciosRest.models.spTrujilloSD.repository"})
public class ConfigBDTrujilloSD {

    @Autowired
    private Environment env;

    @Bean(name= "trujilloSDDatasource")
    public DataSource trujilloSDDatasource(){
        DriverManagerDataSource dataSource=new DriverManagerDataSource();

        dataSource.setUrl(env.getProperty("trujilloSD.datasource.url"));
        dataSource.setUsername(env.getProperty("trujilloSD.datasource.username"));
        dataSource.setPassword(env.getProperty("trujilloSD.datasource.password"));
        dataSource.setDriverClassName(env.getProperty("trujilloSD.datasource.driver-class-name"));

        return dataSource;
    }
    @Bean(name = "trujilloSDEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean em=new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(trujilloSDDatasource());
        em.setPackagesToScan("backendhm.serviciosRest.models.spTrujilloSD.entity");

        HibernateJpaVendorAdapter vendorAdapter=new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Map<String,Object> properties= new HashMap<>();

        properties.put("hibernate.hbm2ddl.auto",env.getProperty("trujilloSD.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.show-sql",env.getProperty("trujilloSD.jpa.show-sql"));
        properties.put("hibernate.dialect",env.getProperty("trujilloSD.jpa.database-platform"));
        properties.put("hibernate.format_sql", env.getProperty("trujilloSD.jpa,properties.hibernate.format_sql"));


        em.setJpaPropertyMap(properties);

        return em;

    }


    @Bean(name = "trujilloSDTransactionManagerFactory")
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager transactionManager=new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());

        return transactionManager;

    }
}
