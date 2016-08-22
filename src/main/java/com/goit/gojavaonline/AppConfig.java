package com.goit.gojavaonline;

import com.goit.gojavaonline.controllers.EmployeeController;
import com.goit.gojavaonline.model.JdbcEmployeeDao;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by tamila on 8/16/16.
 */
@Configuration
@EnableAspectJAutoProxy
public class AppConfig {

    public Properties loadProperties(){
        Properties prop = new Properties();
        InputStream input = null;

        try {

            String filename = "jdbc.properties";
            input = AppConfig.class.getClassLoader().getResourceAsStream(filename);
            if(input==null){
                System.out.println("Sorry, unable to find " + filename);
                return null;
            }

            //load a properties file from class path, inside static method
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally{
            if(input!=null){
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;
    }

    @Bean
    public ComboPooledDataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        Properties properties = loadProperties();
        if(properties != null){
            try {
                dataSource.setDriverClass(properties.getProperty("jdbc.driver.class"));
            } catch (PropertyVetoException e) {
                e.printStackTrace();
            }
            dataSource.setJdbcUrl(properties.getProperty("jdbc.url"));
            dataSource.setUser(properties.getProperty("jdbc.user"));
            dataSource.setPassword(properties.getProperty("jdbc.password"));
            dataSource.setMinPoolSize(Integer.valueOf(properties.getProperty("jdbc.min.connections")));
            dataSource.setMaxPoolSize(Integer.valueOf(properties.getProperty("jdbc.max.connections")));
            dataSource.setAcquireIncrement(Integer.valueOf(properties.getProperty("jdbc.acquire.increment")));
        }


        return dataSource;
    }

    @Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer(){
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
        //propertyPlaceholderConfigurer.setLocation("classpath:jdbc.properties");
        return propertyPlaceholderConfigurer;
    }

    @Bean
    public JdbcEmployeeDao employeeDao( ComboPooledDataSource dataSource ) {
        JdbcEmployeeDao jdbcEmployeeDao = new JdbcEmployeeDao();
        jdbcEmployeeDao.setDataSource(dataSource);
        return jdbcEmployeeDao;
    }

    @Bean
    public DataSourceTransactionManager txManager(ComboPooledDataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);

        return dataSourceTransactionManager;
    }

    @Bean
    public EmployeeController employeeController(/*DataSourceTransactionManager txManager,*/ JdbcEmployeeDao employeeDao) {
        EmployeeController employeeController = new EmployeeController();
        employeeController.setEmployeeDao(employeeDao);
        //employeeController.setTxManager(txManager);
        return employeeController;

    }

    @Bean
    public Main main( EmployeeController employeeController ){
        Main main = new Main();
        main.setEmployeeController(employeeController);
        return main;
    }
}
