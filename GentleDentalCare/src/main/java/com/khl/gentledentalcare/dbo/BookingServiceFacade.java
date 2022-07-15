package com.khl.gentledentalcare.dbo;

import com.khl.gentledentalcare.models.BookingService;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class BookingServiceFacade extends AbstractBookingService<BookingService> {

    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final String SQL_ADD_BOOKING_SERVICE = "INSERT INTO BookingService(BookingServiceID, ServiceID, BookingID, SlotServiceID, BookingDate) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_GET_SLOT_WITH_SERVICE_DATE = "SELECT * FROM BookingService WHERE ServiceID = ? AND BookingDate = ?";
    private static final String SQL_GET_SLOT_WITH_BOOKING_ID = "SELECT * FROM BookingService WHERE BookingID = ?";

    private BookingService getInfoBookingServiceFromSQL(ResultSet resultSet) throws SQLException {
        String getBookingServiceID = resultSet.getString("BookingServiceID");
        String getServiceID = resultSet.getString("ServiceID");
        String getBookingID = resultSet.getString("BookingID");
        String getSlotServiceID = resultSet.getString("SlotServiceID");
        Date getBookingDate = resultSet.getDate("BookingDate");

        return new BookingService(getBookingServiceID, getServiceID, getBookingID, getSlotServiceID, getBookingDate);
    }

    @Override
    protected List<BookingService> getAllBookingService(Connection connection, BookingService bookingService, Object action) throws SQLException {
        ArrayList<BookingService> bookingServiceList = new ArrayList<>();

        try {
            if (connection != null) {
                switch (action.toString()) {
                    case "GetSlotWithServiceDate":
                        preparedStatement = connection.prepareStatement(SQL_GET_SLOT_WITH_SERVICE_DATE);
                        preparedStatement.setString(1, bookingService.getServiceID());
                        preparedStatement.setDate(2, bookingService.getBookingDate());
                        break;
                    case "GetBookingServiceWithID":
                        preparedStatement = connection.prepareStatement(SQL_GET_SLOT_WITH_BOOKING_ID);
                        preparedStatement.setString(1, bookingService.getBookingID());
                        break;
                }

                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    BookingService dentist = getInfoBookingServiceFromSQL(resultSet);
                    bookingServiceList.add(dentist);
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
        return bookingServiceList;
    }

    @Override
    protected boolean addBookingService(Connection connection, BookingService bookingService) throws SQLException {
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_ADD_BOOKING_SERVICE);
                preparedStatement.setString(1, bookingService.getBookingServiceID());
                preparedStatement.setString(2, bookingService.getServiceID());
                preparedStatement.setString(3, bookingService.getBookingID());
                preparedStatement.setString(4, bookingService.getSlotServiceID());
                preparedStatement.setDate(5, bookingService.getBookingDate());
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
