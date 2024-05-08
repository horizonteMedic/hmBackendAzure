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
@EnableJpaRepositories(entityManagerFactoryRef = "trujilloNPEntityManagerFactory", transactionManagerRef = "trujilloNPTransactionManagerFactory", basePackages = {"backendhm.serviciosRest.models.spTrujilloNP.repository"})
public class ConfigBDTrujilloNP {
    @Autowired
    private Environment env;

    @Bean(name= "trujilloNPDatasource")
    public DataSource trujilloNPDatasource(){
        DriverManagerDataSource dataSource=new DriverManagerDataSource();

        dataSource.setUrl(env.getProperty("trujilloNP.datasource.url"));
        dataSource.setUsername(env.getProperty("trujilloNP.datasource.username"));
        dataSource.setPassword(env.getProperty("trujilloNP.datasource.password"));
        dataSource.setDriverClassName(env.getProperty("trujilloNP.datasource.driver-class-name"));

        return dataSource;
    }

    @Bean(name = "trujilloNPEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean em=new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(trujilloNPDatasource());
        em.setPackagesToScan("backendhm.serviciosRest.models.spTrujilloNP.entity");

        HibernateJpaVendorAdapter vendorAdapter=new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Map<String,Object> properties= new HashMap<>();

        properties.put("hibernate.hbm2ddl.auto",env.getProperty("trujilloNP.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.show-sql",env.getProperty("trujilloNP.jpa.show-sql"));
        properties.put("hibernate.dialect",env.getProperty("trujilloNP.jpa.database-platform"));
        properties.put("hibernate.format_sql", env.getProperty("trujilloNP.jpa,properties.hibernate.format_sql"));


        em.setJpaPropertyMap(properties);

        return em;


    }

    @Bean(name = "trujilloNPTransactionManagerFactory")
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager transactionManager=new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());

        return transactionManager;

    }


}
