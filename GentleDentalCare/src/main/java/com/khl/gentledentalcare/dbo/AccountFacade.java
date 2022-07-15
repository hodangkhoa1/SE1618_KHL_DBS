package com.khl.gentledentalcare.dbo;

import com.khl.gentledentalcare.models.Account;
import com.khl.gentledentalcare.utils.FunctionSendEmail;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AccountFacade extends AbstractAccount<Account> {

    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final String SQL_LOGIN = "SELECT * FROM Account WHERE UserEmail = ?";
    private static final String SQL_LOGIN_WITH_COOKIE = "SELECT * FROM Account WHERE UserEmail = ? AND UserPassword = ?";
    private static final String SQL_GET_ACCOUNT_EMPLOYEE = "SELECT * FROM Account WHERE UserID = ?";
    private static final String SQL_GET_ALL_ACCOUNT = "SELECT * FROM Account WHERE UserRole = ? ORDER BY UserID OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY;";
    private static final String SQL_REGISTER_USER = "INSERT INTO Account(UserID, FullName, UserPassword, UserEmail, DefaultAvatar, ColorAvatar) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SQL_ADD_ACCOUNT_EMPLOYEE = "INSERT INTO Account(UserID, FullName, UserPassword, UserEmail, DateOfBirth, UserAddress, UserPhone, Gender, ImageAvatar, UserRole, UserStatus) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_ADD_ACCOUNT_GOOGLE = "INSERT INTO Account(UserID, FullName, UserEmail, ImageAvatar, UserRole, UserStatus) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SQL_USER_STATUS = "UPDATE Account SET UserStatus = ? WHERE UserEmail = ?";
    private static final String SQL_EDIT_PROFILE = "UPDATE Account SET FullName = ?, Gender = ?, DateOfBirth = ?, UserPhone = ?, ImageAvatar = ?, UserAddress = ? WHERE UserEmail = ?";
    private static final String SQL_EDIT_PROFILE_EMPLOYEE = "UPDATE Account SET FullName = ?, UserEmail = ?, DateOfBirth = ?, UserAddress = ?, UserPhone = ?, Gender = ?, ImageAvatar = ? WHERE UserID = ?";
    private static final String SQL_CHANGE_PASSWORD = "UPDATE Account SET UserPassword = ? WHERE UserEmail = ?";
    private static final String SQL_GET_TOTAL_ACCOUNT = "SELECT COUNT(*) FROM Account WHERE UserRole = ?";
    private static final String SQL_SEARCH_ACCOUNT_BY_NAME = "SELECT * FROM Account WHERE FullName LIKE ?";

    private Account getInfoAccountFromSQL(ResultSet resultSet) throws SQLException {
        String getUserID = resultSet.getString("UserID");
        String getFullName = resultSet.getString("FullName");
        String getUserPassword = resultSet.getString("UserPassword");
        String getUserEmail = resultSet.getString("UserEmail");
        Date getDateOfBirth = resultSet.getDate("DateOfBirth");
        String getUserAddress = resultSet.getString("UserAddress");
        String getUserPhone = resultSet.getString("UserPhone");
        String getSex = resultSet.getString("Gender");
        byte[] getImageAvatar = resultSet.getBytes("ImageAvatar");
        String getColorAvatar = resultSet.getString("ColorAvatar");
        String getDefaultAvatar = resultSet.getString("DefaultAvatar");
        int getUserRole = resultSet.getInt("UserRole");
        int getUserStatus = resultSet.getInt("UserStatus");
        Timestamp getAccountCreated = resultSet.getTimestamp("AccountCreated");

        return new Account(getUserID, getFullName, getUserPassword, getUserEmail, getUserAddress, getUserPhone, getSex, Base64.encode(getImageAvatar), getColorAvatar, getDefaultAvatar, getDateOfBirth, getUserRole, getUserStatus, getAccountCreated);
    }

    @Override
    protected String registerAccount(Connection connection, Account account) throws SQLException {
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_REGISTER_USER);

                preparedStatement.setString(1, account.getUserID());
                preparedStatement.setString(2, account.getFullName());
                preparedStatement.setString(3, account.getUserPassword());
                preparedStatement.setString(4, account.getUserEmail());
                preparedStatement.setString(5, account.getDefaultAvatar());
                preparedStatement.setString(6, account.getColorAvatar());

                FunctionSendEmail se = new FunctionSendEmail(account, "Verify Account");
                if (se.sendMailVerifyAccount()) {
                    preparedStatement.executeUpdate();
                    return "success";
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
        return "error";
    }

    @Override
    protected boolean updateAccount(Connection connection, Account account, Object object) throws SQLException {
        try {
            if (connection != null) {
                switch (object.toString()) {
                    case "EditStatus":
                        preparedStatement = connection.prepareStatement(SQL_USER_STATUS);
                        preparedStatement.setInt(1, account.getUserStatus());
                        preparedStatement.setString(2, account.getUserEmail());
                        break;
                    case "EditProfile":
                        preparedStatement = connection.prepareStatement(SQL_EDIT_PROFILE);
                        preparedStatement.setString(1, account.getFullName());
                        preparedStatement.setString(2, account.getGender());
                        preparedStatement.setDate(3, account.getDateOfBirth());
                        preparedStatement.setString(4, account.getUserPhone());
                        preparedStatement.setBytes(5, Base64.decode(account.getImageAvatar()));
                        preparedStatement.setString(6, account.getUserAddress());
                        preparedStatement.setString(7, account.getUserEmail());
                        break;
                    case "ChangePassword":
                        preparedStatement = connection.prepareStatement(SQL_CHANGE_PASSWORD);
                        preparedStatement.setString(1, account.getUserPassword());
                        preparedStatement.setString(2, account.getUserEmail());
                        break;
                    case "EditProfileEmployee":
                        preparedStatement = connection.prepareStatement(SQL_EDIT_PROFILE_EMPLOYEE);
                        preparedStatement.setString(1, account.getFullName());
                        preparedStatement.setString(2, account.getUserEmail());
                        preparedStatement.setDate(3, account.getDateOfBirth());
                        preparedStatement.setString(4, account.getUserAddress());
                        preparedStatement.setString(5, account.getUserPhone());
                        preparedStatement.setString(6, account.getGender());
                        preparedStatement.setBytes(7, Base64.decode(account.getImageAvatar()));
                        preparedStatement.setString(7, account.getUserID());
                        break;
                }

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
    protected List<Account> getAccount(Connection connection, Object object, Object action, Object status) throws SQLException {
        ArrayList<Account> accountList = new ArrayList<>();

        try {
            if (connection != null) {
                switch (action.toString()) {
                    case "SearchAccount":
                        preparedStatement = connection.prepareStatement(SQL_SEARCH_ACCOUNT_BY_NAME);
                        preparedStatement.setString(1, "%" + object.toString() + "%");
                        break;
                    case "PagingAccount":
                        preparedStatement = connection.prepareStatement(SQL_GET_ALL_ACCOUNT);
                        preparedStatement.setString(1, status.toString());
                        preparedStatement.setInt(2, ((int) object - 1) * 5);
                        break;
                }

                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Account account = getInfoAccountFromSQL(resultSet);
                    accountList.add(account);
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
        return accountList;
    }

    @Override
    protected Account checkAccount(Connection connection, Account account, Object action) throws SQLException {
        try {
            if (connection != null) {
                switch (action.toString()) {
                    case "Login":
                        preparedStatement = connection.prepareStatement(SQL_LOGIN);
                        preparedStatement.setString(1, account.getUserEmail());
                        break;
                    case "LoginWithCookie":
                        preparedStatement = connection.prepareStatement(SQL_LOGIN_WITH_COOKIE);
                        preparedStatement.setString(1, account.getUserEmail());
                        preparedStatement.setString(2, account.getUserPassword());
                        break;
                    case "GetAccountEmployee":
                        preparedStatement = connection.prepareStatement(SQL_GET_ACCOUNT_EMPLOYEE);
                        preparedStatement.setString(1, account.getUserID());
                        break;
                }

                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    return getInfoAccountFromSQL(resultSet);
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

    @Override
    protected int countAccount(Connection connection, Object role) throws SQLException {
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_GET_TOTAL_ACCOUNT);
                preparedStatement.setString(1, role.toString());
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
    protected boolean addAccount(Connection connection, Account account, Object action) throws SQLException {
        try {
            if (connection != null) {
                switch (action.toString()) {
                    case "AddAccountEmployee":
                        preparedStatement = connection.prepareStatement(SQL_ADD_ACCOUNT_EMPLOYEE);
                        preparedStatement.setString(1, account.getUserID());
                        preparedStatement.setString(2, account.getFullName());
                        preparedStatement.setString(3, account.getUserPassword());
                        preparedStatement.setString(4, account.getUserEmail());
                        preparedStatement.setDate(5, account.getDateOfBirth());
                        preparedStatement.setString(6, account.getUserAddress());
                        preparedStatement.setString(7, account.getUserPhone());
                        preparedStatement.setString(8, account.getGender());
                        preparedStatement.setBytes(9, Base64.decode(account.getImageAvatar()));
                        preparedStatement.setInt(10, account.getUserRole());
                        preparedStatement.setInt(11, account.getUserStatus());
                        break;
                    case "AddAccountGoogle":
                        preparedStatement = connection.prepareStatement(SQL_ADD_ACCOUNT_GOOGLE);
                        preparedStatement.setString(1, account.getUserID());
                        preparedStatement.setString(2, account.getFullName());
                        preparedStatement.setString(3, account.getUserEmail());
                        preparedStatement.setBytes(4, Base64.decode(account.getImageAvatar()));
                        preparedStatement.setInt(5, account.getUserRole());
                        preparedStatement.setInt(6, account.getUserStatus());
                        break;
                }

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

}
