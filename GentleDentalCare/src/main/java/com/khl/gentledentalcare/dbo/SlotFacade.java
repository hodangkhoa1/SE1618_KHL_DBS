package com.khl.gentledentalcare.dbo;

import com.khl.gentledentalcare.models.Slot;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class SlotFacade extends AbstractSlot<Slot> {

    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final String SQL_GET_ALL_SLOT = "SELECT * FROM Slot ORDER BY SlotStart ASC";
    private static final String SQL_GET_ALL_SLOT_WITH_ID = "SELECT * FROM Slot WHERE SlotID = ? ORDER BY SlotStart ASC";
    private static final String SQL_GET_ALL_SLOT_WITH_TIME = "SELECT * FROM Slot WHERE SlotStart = ?";

    private Slot getInfoSlotFromSQL(ResultSet resultSet) throws SQLException {
        String getSlotID = resultSet.getString("SlotID");
        Time getSlotStart = resultSet.getTime("SlotStart");

        return new Slot(getSlotID, getSlotStart);
    }

    @Override
    protected List<Slot> getAllSlot(Connection connection, Object slotID, Object action) throws SQLException {
        ArrayList<Slot> slotList = new ArrayList<>();

        try {
            if (connection != null) {
                switch (action.toString()) {
                    case "GetAllSlot":
                        preparedStatement = connection.prepareStatement(SQL_GET_ALL_SLOT);
                        break;
                    case "GetAllSlotWithID":
                        preparedStatement = connection.prepareStatement(SQL_GET_ALL_SLOT_WITH_ID);
                        preparedStatement.setString(1, slotID.toString());
                        break;
                }

                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Slot slot = getInfoSlotFromSQL(resultSet);
                    slotList.add(slot);
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
        return slotList;
    }

    @Override
    protected Slot getSlot(Connection connection, Object slotID, Object action) throws SQLException {
        try {
            if (connection != null) {
                switch (action.toString()) {
                    case "GetSlotWithID":
                        preparedStatement = connection.prepareStatement(SQL_GET_ALL_SLOT_WITH_ID);
                        preparedStatement.setString(1, slotID.toString());
                        break;
                    case "GetSlotWithTime":
                        preparedStatement = connection.prepareStatement(SQL_GET_ALL_SLOT_WITH_TIME);
                        preparedStatement.setString(1, slotID.toString());
                        break;
                }

                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    return getInfoSlotFromSQL(resultSet);
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
