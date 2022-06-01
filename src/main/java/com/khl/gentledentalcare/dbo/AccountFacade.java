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
import java.util.List;

/**
 *
 * @author ASUS
 */
public class AccountFacade extends AbstractAccount<Account> {

    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final String SQL_LOGIN = "SELECT * FROM Account WHERE Email = ?";
    private static final String SQL_GET_ALL_ACCOUNT = "SELECT * FROM Account ORDER BY UserID OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY;";
    private static final String SQL_REGISTER_USER = "INSERT INTO Account(UserID, FullName, UserPassword, UserEmail, DefaultAvatar, ColorAvatar) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SQL_DISABLE_ACCOUNT = "UPDATE Account SET DisableAccount = 1 WHERE UserID = ?";
    private static final String SQL_UN_DISABLE_ACCOUNT = "UPDATE Account SET DisableAccount = 0 WHERE UserID = ?";
    private static final String SQL_EDIT_PROFILE = "UPDATE Account SET UserName = ?, Sex = ?, DateOfBirth = ?, PhoneNumber = ?, ImageAvatar = ?, UserAddress = ? WHERE Email = ?";
    private static final String SQL_CHANGE_PASSWORD = "UPDATE Account SET PasswordHash = ? WHERE Email = ?";
    private static final String SQL_ACTIVE_ACCOUNT = "UPDATE Account SET Active = '1' WHERE Email = ?";
    private static final String SQL_GET_TOTAL_ACCOUNT = "SELECT COUNT(*) FROM Account";
    private static final String SQL_SEARCH_ACCOUNT_BY_NAME = "SELECT * FROM Account WHERE UserName LIKE ?";

    private Account getInfoAccountFromSQL(ResultSet resultSet) throws SQLException {
        String getUserID = resultSet.getString("UserID");
        String getFullName = resultSet.getString("FullName");
        String getUserPassword = resultSet.getString("UserPassword");
        String getUserEmail = resultSet.getString("UserEmail");
        Date getDateOfBirth = resultSet.getDate("DateOfBirth");
        String getUserAddress = resultSet.getString("UserAddress");
        String getUserPhone = resultSet.getString("UserPhone");
        String getSex = resultSet.getString("Sex");
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
    protected boolean updateAccount(Connection connection, Account t, Object object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected List<Account> getAccount(Connection connection, Object object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected Account checkAccount(Connection connection, Object object) throws SQLException {
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_LOGIN);
                preparedStatement.setString(1, object.toString());
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
    protected int countAccount(Connection connection) throws SQLException {
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_GET_TOTAL_ACCOUNT);
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

}
