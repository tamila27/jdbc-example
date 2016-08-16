package com.goit.gojavaonline.model.jdbc;

import com.goit.gojavaonline.model.Employee;

import java.util.List;

/**
 * Created by tamila on 8/12/16.
 */
public interface EmployeeDao {
    Employee load(int id);

    List<Employee> getAll();
}
