package com.goit.gojavaonline;

import com.goit.gojavaonline.model.Employee;
import com.goit.gojavaonline.model.JdbcEmployeeDao;
import com.goit.gojavaonline.model.jdbc.EmployeeDao;

/**
 * Created by tamila on 8/10/16.
 */
public class Main {

    public static void main(String[] args) {
        EmployeeDao jdbcEmployeeDao = new JdbcEmployeeDao();

        //employeeDao.getAll().forEach(System.out::println);


        Employee employee = jdbcEmployeeDao.load(1);
        System.out.println(employee);
    }
}
