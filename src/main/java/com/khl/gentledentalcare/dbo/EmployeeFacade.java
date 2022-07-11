package com.khl.gentledentalcare.dbo;

import com.khl.gentledentalcare.models.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class EmployeeFacade extends AbstractEmployee<Employee> {

    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final String SQL_GET_TOTAL_EMPLOYEE = "SELECT COUNT(*) FROM Employee";
    private static final String SQL_INSERT_EMPLOYEE = "INSERT INTO Employee(EmployeeID, Salary, Insurance) VALUES(?, ?, ?)";
    private static final String SQL_UPDATE_EMPLOYEE = "UPDATE Employee SET Salary = ?, Insurance = ? WHERE EmployeeID = ?";
    private static final String SQL_GET_PAGING_EMPLOYEE = "SELECT * FROM Employee ORDER BY EmployeeID OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY;";
    private static final String SQL_GET_EMPLOYEE_BY_ID = "SELECT * FROM Employee WHERE EmployeeID = ?";
    private static final String SQL_GET_TOP_DENTIST = "SELECT TOP ? * FROM Employee";

    private Employee getInfoEmployeeFromSQL(ResultSet resultSet) throws SQLException {
        String getEmployeeID = resultSet.getString("EmployeeID");
        double getSalary = resultSet.getDouble("Salary");
        int getInsurance = resultSet.getInt("Insurance");
        return new Employee(getEmployeeID, getSalary, getInsurance);
    }

    @Override
    protected List<Employee> getAllEmployee(Connection connection, Object object, Object action) throws SQLException {
        ArrayList<Employee> employeeList = new ArrayList<>();

        try {
            if (connection != null) {
                switch (action.toString()) {
                    case "PagingAccount":
                        preparedStatement = connection.prepareStatement(SQL_GET_PAGING_EMPLOYEE);
                        preparedStatement.setInt(1, ((int) object - 1) * 5);
                        break;
                    case "ShowTopEmployee":
                        preparedStatement = connection.prepareStatement(SQL_GET_TOP_DENTIST);
                        preparedStatement.setInt(1, (int) object);
                        break;
                }

                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Employee employee = getInfoEmployeeFromSQL(resultSet);
                    employeeList.add(employee);
                }
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return employeeList;
    }

    @Override
    protected boolean addEmployee(Connection connection, Employee employee) throws SQLException {
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_INSERT_EMPLOYEE);

                preparedStatement.setString(1, employee.getEmployeeID());
                preparedStatement.setDouble(2, employee.getSalary());
                preparedStatement.setInt(3, employee.getInsurance());
                preparedStatement.executeUpdate();
                return true;
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return false;
    }

    @Override
    protected boolean updateEmployee(Connection connection, Employee employee) throws SQLException {
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_UPDATE_EMPLOYEE);
                preparedStatement.setDouble(1, employee.getSalary());
                preparedStatement.setInt(2, employee.getInsurance());
                preparedStatement.setString(3, employee.getEmployeeID());
                preparedStatement.executeUpdate();
                return true;
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return false;
    }

    @Override
    protected int countEmployee(Connection connection) throws SQLException {
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_GET_TOTAL_EMPLOYEE);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return 0;
    }

    @Override
    protected Employee getEmployee(Connection connection, Object employeeID) throws SQLException {
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_GET_EMPLOYEE_BY_ID);
                preparedStatement.setString(1, employeeID.toString());
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    return getInfoEmployeeFromSQL(resultSet);
                }
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }

}
