package com.khl.gentledentalcare.dbo;

import com.khl.gentledentalcare.models.Hospital;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HospitalFacade extends AbstractHospital<Hospital> {

    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final String SQL_GET_ALL_HOSPITAL = "SELECT * FROM Hospital WHERE HospitalStatus = ?";
    private static final String SQL_GET_TOTAL_HOSPITAL = "SELECT COUNT(*) FROM Hospital";
    private static final String SQL_PAGING_HOSPITAL = "SELECT * FROM Hospital ORDER BY HospitalID OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY;";
    private static final String SQL_ADD_HOSPITAL = "INSERT INTO Hospital(HospitalID, HospitalName, HospitalPhone, HospitalAddress) VALUES(?, ?, ?, ?)";
    private static final String SQL_EDIT_HOSPITAL = "UPDATE Hospital SET HospitalName = ?, HospitalPhone = ?, HospitalAddress = ? WHERE HospitalID = ?";
    private static final String SQL_EDIT_STATUS = "UPDATE Hospital SET HospitalStatus = ? WHERE HospitalID = ?";
    private static final String SQL_GET_HOSPITAL_BY_ID = "SELECT * FROM Hospital WHERE HospitalID = ?";

    private Hospital getInfoHospitalFromSQL(ResultSet resultSet) throws SQLException {
        String getHospitalID = resultSet.getString("HospitalID");
        String getHospitalName = resultSet.getString("HospitalName");
        String getHospitalPhone = resultSet.getString("HospitalPhone");
        String getHospitalAddress = resultSet.getString("HospitalAddress");
        int getHospitalStatus = resultSet.getInt("HospitalStatus");

        return new Hospital(getHospitalID, getHospitalName, getHospitalPhone, getHospitalAddress, getHospitalStatus);
    }

    @Override
    protected List<Hospital> getAllHospital(Connection connection, Object object, Object action, Object status) throws SQLException {
        ArrayList<Hospital> hospitalList = new ArrayList<>();

        try {
            if (connection != null) {
                switch (action.toString()) {
                    case "GetAllHospital":
                        preparedStatement = connection.prepareStatement(SQL_GET_ALL_HOSPITAL);
                        preparedStatement.setString(1, status.toString());
                        break;
                    case "PagingHospital":
                        preparedStatement = connection.prepareStatement(SQL_PAGING_HOSPITAL);
                        preparedStatement.setInt(1, ((int) object - 1) * 5);
                        break;
                }

                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Hospital hospital = getInfoHospitalFromSQL(resultSet);
                    hospitalList.add(hospital);
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
        return hospitalList;
    }

    @Override
    protected boolean addHospital(Connection connection, Hospital hospital) throws SQLException {
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_ADD_HOSPITAL);

                preparedStatement.setString(1, hospital.getHospitalID());
                preparedStatement.setString(2, hospital.getHospitalName());
                preparedStatement.setString(3, hospital.getHospitalPhone());
                preparedStatement.setString(4, hospital.getHospitalAddress());
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
    protected boolean updateHospital(Connection connection, Hospital hospital, Object action) throws SQLException {
        try {
            if (connection != null) {
                switch (action.toString()) {
                    case "EditStatus":
                        preparedStatement = connection.prepareStatement(SQL_EDIT_STATUS);
                        preparedStatement.setInt(1, hospital.getHospitalStatus());
                        preparedStatement.setString(2, hospital.getHospitalID());
                        break;
                    case "EditHospital":
                        preparedStatement = connection.prepareStatement(SQL_EDIT_HOSPITAL);
                        preparedStatement.setString(1, hospital.getHospitalName());
                        preparedStatement.setString(2, hospital.getHospitalPhone());
                        preparedStatement.setString(3, hospital.getHospitalAddress());
                        preparedStatement.setString(4, hospital.getHospitalID());
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
    protected int countHospital(Connection connection) throws SQLException {
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_GET_TOTAL_HOSPITAL);
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
    protected Hospital getHospital(Connection connection, Object hospitalID) throws SQLException {
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_GET_HOSPITAL_BY_ID);
                preparedStatement.setString(1, hospitalID.toString());
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    return getInfoHospitalFromSQL(resultSet);
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
