package com.goit.gojavaonline.controllers;

import com.goit.gojavaonline.model.Employee;
import com.goit.gojavaonline.model.jdbc.EmployeeDao;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

/**
 * Created by tamila on 8/17/16.
 */
public class EmployeeController {
    //private PlatformTransactionManager txManager;
    private EmployeeDao employeeDao;

    @Transactional
    public List<Employee> getAllEmployee() {
        /*TransactionStatus status = txManager.getTransaction(new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED));
        try{
            List<Employee> result = employeeDao.getAll();
            txManager.commit(status);
            return result;
        } catch (Exception e) {
            txManager.rollback(status);
            throw new RuntimeException(e);
        }*/
        return employeeDao.getAll();
    }

    /*public void setTxManager(PlatformTransactionManager txManager) {
        this.txManager = txManager;
    }*/

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
}
