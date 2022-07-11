package com.khl.gentledentalcare.dbo;

import com.khl.gentledentalcare.models.ServiceSlot;
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
public class ServiceSlotFacade extends AbstractServiceSlot<ServiceSlot> {

    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final String SQL_GET_ALL_SERVICE_SLOT = "SELECT * FROM ServiceSlot";
    private static final String SQL_GET_ALL_SERVICE_SLOT_EMPTY = "SELECT * FROM ServiceSlot WHERE NOT ServiceID = ?";
    private static final String SQL_ADD_SERVICE_SLOT = "INSERT INTO ServiceSlot(SlotServiceID, ServiceID, SlotID) VALUES(?, ?, ?)";
    private static final String SQL_GET_SERVICE_SLOT_BY_ID = "SELECT * FROM ServiceSlot WHERE SlotServiceID = ?";
    private static final String SQL_GET_SERVICE_SLOT_BY_SLOT_ID = "SELECT * FROM ServiceSlot WHERE SlotID = ?";

    private ServiceSlot getInfoServiceSlotFromSQL(ResultSet resultSet) throws SQLException {
        String getSlotServiceID = resultSet.getString("SlotServiceID");
        String getServiceID = resultSet.getString("ServiceID");
        String getSlotID = resultSet.getString("SlotID");
        int getServiceSlotStatus = resultSet.getInt("ServiceSlotStatus");

        return new ServiceSlot(getSlotServiceID, getServiceID, getSlotID, getServiceSlotStatus);
    }

    @Override
    protected List<ServiceSlot> getAllServiceSlot(Connection connection, ServiceSlot serviceSlot, Object action) throws SQLException {
        ArrayList<ServiceSlot> serviceSlotList = new ArrayList<>();

        try {
            if (connection != null) {
                switch (action.toString()) {
                    case "GetServiceSlotEmpty":
                        preparedStatement = connection.prepareStatement(SQL_GET_ALL_SERVICE_SLOT_EMPTY);
                        preparedStatement.setString(1, serviceSlot.getServiceID());
                        break;
                    case "GetAllServiceSlot":
                        preparedStatement = connection.prepareStatement(SQL_GET_ALL_SERVICE_SLOT);
                        break;
                }

                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    ServiceSlot getServiceSlot = getInfoServiceSlotFromSQL(resultSet);
                    serviceSlotList.add(getServiceSlot);
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
        return serviceSlotList;
    }

    @Override
    protected boolean addServiceSlot(Connection connection, ServiceSlot serviceSlot) throws SQLException {
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_ADD_SERVICE_SLOT);
                preparedStatement.setString(1, serviceSlot.getSlotServiceID());
                preparedStatement.setString(2, serviceSlot.getServiceID());
                preparedStatement.setString(3, serviceSlot.getSlotID());
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
    protected boolean updateServiceSlot(Connection connection, Object serviceSlotID) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected ServiceSlot getServiceSlot(Connection connection, Object slotServiceID, Object action) throws SQLException {
        try {
            if (connection != null) {
                switch (action.toString()) {
                    case "GetServiceSlotWithId":
                        preparedStatement = connection.prepareStatement(SQL_GET_SERVICE_SLOT_BY_ID);
                        preparedStatement.setString(1, slotServiceID.toString());
                        break;
                    case "GetServiceSlotWithSlotID":
                        preparedStatement = connection.prepareStatement(SQL_GET_SERVICE_SLOT_BY_SLOT_ID);
                        preparedStatement.setString(1, slotServiceID.toString());
                        break;
                }

                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    return getInfoServiceSlotFromSQL(resultSet);
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
