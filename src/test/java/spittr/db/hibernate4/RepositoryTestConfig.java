package spittr.db.hibernate4;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Lusai on 8/31/16.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan
public class RepositoryTestConfig implements TransactionManagementConfigurer {

    @Inject
    private SessionFactory sessionFactory;

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
        embeddedDatabaseBuilder.setType(EmbeddedDatabaseType.H2);
        embeddedDatabaseBuilder.addScript("spittr/db/hibernate4/schema.sql");
        embeddedDatabaseBuilder.addScript("spittr/db/hibernate4/test-data.sql");
        EmbeddedDatabase embeddedDatabase = embeddedDatabaseBuilder.build();
        return embeddedDatabase;
    }

    public PlatformTransactionManager annotationDrivenTransactionManager() {
        System.out.println(sessionFactory);

        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory);

        return hibernateTransactionManager;
    }

    @Bean
    public SessionFactory sessionFactoryBean(){
        try {
            LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
            localSessionFactoryBean.setDataSource(dataSource());
            localSessionFactoryBean.setPackagesToScan("spittr.domain");
            Properties properties = new Properties();
            properties.setProperty("dialect", "org.hibernate.dialect.H2Dialect");
            localSessionFactoryBean.setHibernateProperties(properties);
            localSessionFactoryBean.afterPropertiesSet();
            SessionFactory object = localSessionFactoryBean.getObject();
            return object;
        } catch (IOException e) {
            return null;
        }
    }
}
