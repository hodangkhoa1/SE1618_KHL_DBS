package com.khl.gentledentalcare.dbo;

import com.khl.gentledentalcare.models.Booking;
import com.khl.gentledentalcare.models.BookingService;
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
public class BookingServiceFacade extends AbstractBookingService<BookingService> {
    
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final String SQL_ADD_BOOKING_SERVICE = "INSERT INTO BookingService(BookingServiceID, ServiceID, BookingID, SlotServiceID, BookingDate) VALUES(?, ?, ?, ?, ?)";
    
    private BookingService getInfoBookingServiceFromSQL(ResultSet resultSet) throws SQLException {
        String getBookingServiceID = resultSet.getString("BookingServiceID");
        String getServiceID = resultSet.getString("ServiceID");
        String getBookingID = resultSet.getString("BookingID");
        String getSlotServiceID = resultSet.getString("SlotServiceID");
        Date getBookingDate = resultSet.getDate("BookingDate");

        return new BookingService(getBookingServiceID, getServiceID, getBookingID, getSlotServiceID, getBookingDate);
    }

    @Override
    protected List<BookingService> getAllBookingService(Connection connection) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

    @Override
    protected boolean updateBookingService(Connection connection, Object bookingServiceID) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected int countBookingService(Connection connection) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
