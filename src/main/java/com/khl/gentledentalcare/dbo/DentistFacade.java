package com.khl.gentledentalcare.dbo;

import com.khl.gentledentalcare.models.Dentist;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
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
public class DentistFacade extends AbstractDentist<Dentist> {

    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final String SQL_GET_ALL_DENTIST = "SELECT * FROM Dentist";
    private static final String SQL_PAGING_DENTIST = "SELECT * FROM Dentist ORDER BY DentistID OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY;";
    private static final String SQL_ADD_DENTIST = "INSERT INTO Dentist(DentistID, NameDentist, NumberPhoneDentist, DetailDentist, ImageDentist, DentistDescription, AcademicRank, TitleDentist) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_DENTIST = "UPDATE Dentist SET NameDentist = ?, NumberPhoneDentist = ?, DetailDentist = ?, ImageDentist = ?, DentistDescription = ?, AcademicRank = ?, TitleDentist = ? WHERE DentistID = ?";
    private static final String SQL_GET_TOTAL_DENTIST = "SELECT COUNT(*) FROM Dentist";

    private Dentist getInfoDentistFromSQL(ResultSet resultSet) throws SQLException {
        String getDentistID = resultSet.getString("DentistID");
        String getNameDentist = resultSet.getString("NameDentist");
        String getNumberPhoneDentist = resultSet.getString("NumberPhoneDentist");
        String getDetailDentist = resultSet.getString("DetailDentist");
        byte[] getImageDentist = resultSet.getBytes("ImageDentist");
        String getDentistDescription = resultSet.getString("DentistDescription");
        String getAcademicRank = resultSet.getString("AcademicRank");
        String getTitleDentist = resultSet.getString("TitleDentist");

        return new Dentist(getDentistID, getNameDentist, getNumberPhoneDentist, getDetailDentist, Base64.encode(getImageDentist), getDentistDescription, getAcademicRank, getTitleDentist);
    }

    @Override
    protected List<Dentist> getAllDentist(Connection connection, Object object, Object action) throws SQLException {
        ArrayList<Dentist> dentistList = new ArrayList<>();

        try {
            if (connection != null) {
                switch (action.toString()) {
                    case "GetAllDentist":
                        preparedStatement = connection.prepareStatement(SQL_GET_ALL_DENTIST);
                        break;
                    case "PagingDentist":
                        preparedStatement = connection.prepareStatement(SQL_PAGING_DENTIST);
                        preparedStatement.setInt(1, ((int) object - 1) * 5);
                        break;
                }

                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Dentist dentist = getInfoDentistFromSQL(resultSet);
                    dentistList.add(dentist);
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
        return dentistList;
    }

    @Override
    protected boolean addDentist(Connection connection, Dentist dentist) throws SQLException {
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_ADD_DENTIST);

                preparedStatement.setString(1, dentist.getDentistID());
                preparedStatement.setString(2, dentist.getNameDentist());
                preparedStatement.setString(3, dentist.getNumberPhoneDentist());
                preparedStatement.setString(4, dentist.getDetailDentist());
                preparedStatement.setBytes(5, Base64.decode(dentist.getImageDentist()));
                preparedStatement.setString(6, dentist.getDentistDescription());
                preparedStatement.setString(7, dentist.getAcademicRank());
                preparedStatement.setString(8, dentist.getTitleDentist());
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
    protected boolean updateDentist(Connection connection, Dentist dentist) throws SQLException {
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_UPDATE_DENTIST);
                preparedStatement.setString(1, dentist.getNameDentist());
                preparedStatement.setString(2, dentist.getNumberPhoneDentist());
                preparedStatement.setString(3, dentist.getDetailDentist());
                preparedStatement.setBytes(4, Base64.decode(dentist.getImageDentist()));
                preparedStatement.setString(5, dentist.getDentistDescription());
                preparedStatement.setString(6, dentist.getAcademicRank());
                preparedStatement.setString(7, dentist.getTitleDentist());
                preparedStatement.setString(8, dentist.getDentistID());
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
    protected int countDentist(Connection connection) throws SQLException {
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_GET_TOTAL_DENTIST);
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
