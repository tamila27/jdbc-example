package com.goit.gojavaonline.model;

import com.goit.gojavaonline.model.jdbc.EmployeeDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tamila on 8/12/16.
 */
public class JdbcEmployeeDao implements EmployeeDao {
    private static Logger LOGGER = LoggerFactory.getLogger(Employee.class);
    private String url = "jdbc:mysql://localhost:3306/COMPANY";
    private String user = "root";
    private String password = "1";


    public JdbcEmployeeDao() {
        loadDriver();
    }

    @Override
    public Employee load(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM EMPLOYEE WHERE ID = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getEmployee(resultSet);
            } else {
                throw new RuntimeException("Cannot find Employee with id = " + id);

            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB " + url, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> result = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM EMPLOYEE");

            while (resultSet.next()) {
                result.add(getEmployee(resultSet));

            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB " + url, e);
            new RuntimeException(e);
        }
        return result;
    }

    private Employee getEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("ID"));
        employee.setAge(resultSet.getInt("AGE"));
        employee.setName(resultSet.getString("NAME"));
        employee.setAddress(resultSet.getString("ADDRESS"));
        employee.setSalary(resultSet.getFloat("SALARY"));
        employee.setJoin_data(resultSet.getString("JOIN_DATE"));
        return employee;
    }

    private static void loadDriver() {
        try {
            LOGGER.info("Loading JDBC driver: com.mysql.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
            LOGGER.info("Driver successfully loaded");
        } catch (ClassNotFoundException e) {
            LOGGER.error("Cannot find driver: com.mysql.cj.jdbc.Driver");
            throw new RuntimeException(e);
        }

    }
}
