package com.khl.gentledentalcare.dbo;

import com.khl.gentledentalcare.models.Booking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class BookingFacade extends AbstractBooking<Booking> {

    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final String SQL_ADD_BOOKING = "INSERT INTO Booking(BookingID, UserID, HospitalID) VALUES(?, ?, ?)";
    private static final String SQL_GET_TOTAL_BOOKING_WITH_USER_ID = "SELECT COUNT(*) FROM Booking WHERE UserID = ?";
    private static final String SQL_GET_TOTAL_BOOKING = "SELECT COUNT(*) FROM Booking";
    private static final String SQL_GET_TOP_5_BOOKING_WITH_USER = "SELECT TOP 5 * FROM Booking WHERE UserID = ?";
    private static final String SQL_GET_TOP_5_BOOKING = "SELECT TOP 5 * FROM Booking";
    private static final String SQL_GET_ALL_BOOKING = "SELECT * FROM Booking";
    private static final String SQL_GET_NEXT_5_BOOKING_WITH_USER = "SELECT * FROM Booking WHERE UserID = ? ORDER BY BookingID OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY;";
    private static final String SQL_GET_PAGING_BOOKING = "SELECT * FROM Booking ORDER BY BookingID OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY;";
    private static final String SQL_UPDATE_BOOKING_STATUS = "UPDATE Booking SET BookingStatus = ? WHERE BookingID = ?";

    private Booking getInfoBookingFromSQL(ResultSet resultSet) throws SQLException {
        String getBookingID = resultSet.getString("BookingID");
        String getUserID = resultSet.getString("UserID");
        String getHospitalID = resultSet.getString("HospitalID");
        String getBookingNote = resultSet.getString("BookingNote");
        int getBookingStatus = resultSet.getInt("BookingStatus");
        Timestamp getBookingCreated = resultSet.getTimestamp("BookingCreated");

        return new Booking(getBookingID, getUserID, getHospitalID, getBookingNote, getBookingStatus, getBookingCreated);
    }

    @Override
    protected List<Booking> getAllBooking(Connection connection, Object action, Object value, Booking booking) throws SQLException {
        ArrayList<Booking> bookingList = new ArrayList<>();

        try {
            if (connection != null) {
                switch (action.toString()) {
                    case "GetTop5BookingWithUser":
                        preparedStatement = connection.prepareStatement(SQL_GET_TOP_5_BOOKING_WITH_USER);
                        preparedStatement.setString(1, booking.getUserID());
                        break;
                    case "GetTop5Booking":
                        preparedStatement = connection.prepareStatement(SQL_GET_TOP_5_BOOKING);
                        break;
                    case "GetNext5BookingWithUser":
                        preparedStatement = connection.prepareStatement(SQL_GET_NEXT_5_BOOKING_WITH_USER);
                        preparedStatement.setString(1, booking.getUserID());
                        preparedStatement.setInt(2, ((int) value - 1) * 5);
                        break;
                    case "GetPagingBooking":
                        preparedStatement = connection.prepareStatement(SQL_GET_PAGING_BOOKING);
                        preparedStatement.setInt(1, ((int) value - 1) * 5);
                        break;
                    case "GetAllBooking":
                        preparedStatement = connection.prepareStatement(SQL_GET_ALL_BOOKING);
                        break;
                }

                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Booking getBooking = getInfoBookingFromSQL(resultSet);
                    bookingList.add(getBooking);
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
        return bookingList;
    }

    @Override
    protected boolean addBooking(Connection connection, Booking booking) throws SQLException {
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_ADD_BOOKING);
                preparedStatement.setString(1, booking.getBookingID());
                preparedStatement.setString(2, booking.getUserID());
                preparedStatement.setString(3, booking.getHospitalID());
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
    protected boolean updateBooking(Connection connection, Booking booking) throws SQLException {
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_UPDATE_BOOKING_STATUS);
                preparedStatement.setInt(1, booking.getBookingStatus());
                preparedStatement.setString(2, booking.getBookingID());
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
    protected int countBooking(Connection connection, Object value, Object action) throws SQLException {
        try {
            if (connection != null) {
                switch (action.toString()) {
                    case "CountBookingWithUserID":
                        preparedStatement = connection.prepareStatement(SQL_GET_TOTAL_BOOKING_WITH_USER_ID);
                        preparedStatement.setString(1, value.toString());
                        break;
                    case "CountAllBooking":
                        preparedStatement = connection.prepareStatement(SQL_GET_TOTAL_BOOKING);
                        break;
                }
                
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
