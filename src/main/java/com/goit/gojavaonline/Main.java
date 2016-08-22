package com.goit.gojavaonline;

import com.goit.gojavaonline.controllers.EmployeeController;
import com.goit.gojavaonline.model.Employee;
import com.goit.gojavaonline.model.JdbcEmployeeDao;
import com.goit.gojavaonline.model.jdbc.EmployeeDao;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * Created by tamila on 8/10/16.
 */
public class Main {

    private EmployeeController employeeController;

    public static void main(String[] args) {
        /*AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        ComboPooledDataSource comboPooledDataSource = applicationContext.getBean("dataSource", ComboPooledDataSource.class);
        EmployeeDao jdbcEmployeeDao = applicationContext.getBean("jdbcEmployeeDao", JdbcEmployeeDao.class);

        //EmployeeDao jdbcEmployeeDao = new JdbcEmployeeDao();

        //employeeDao.getAll().forEach(System.out::println);


        Employee employee = jdbcEmployeeDao.load(2);
        System.out.println(employee);*/

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        DataSourceTransactionManager txManager = applicationContext.getBean("txManager", DataSourceTransactionManager.class);
        Main main = applicationContext.getBean("main", Main.class);
        main.start();
    }

    private void start() {
        employeeController.getAllEmployee().forEach(System.out::println);
    }

    public void setEmployeeController(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }
}
