package com.khl.gentledentalcare.dbo;

import com.khl.gentledentalcare.models.Services;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ServiceFacade extends AbstractServices<Services> {

    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final String SQL_GET_ALL_SERVICES = "SELECT * FROM [Services] WHERE ServiceStatus = ?";
    private static final String SQL_GET_TOP_6_SERVICES = "SELECT TOP 6 * FROM [Services]";
    private static final String SQL_GET_TOP_4_SERVICES_RANDOM = "SELECT TOP 4 * FROM [Services] ORDER BY NEWID()";
    private static final String SQL_PAGING_SERVICES = "SELECT * FROM [Services] ORDER BY ServiceID OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY;";
    private static final String SQL_GET_NEXT_6_SERVICES = "SELECT * FROM [Services] ORDER BY ServiceID OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY;";
    private static final String SQL_ADD_SERVICES = "INSERT INTO [Services](ServiceID, ServiceName, ServicePrice, ImageService, DescriptionService, ServiceStatus) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SQL_GET_TOTAL_SERVICES = "SELECT COUNT(*) FROM [Services]";
    private static final String SQL_SERVICES_STATUS = "UPDATE [Services] SET ServiceStatus = ? WHERE ServiceID = ?";
    private static final String SQL_EDIT_SERVICES = "UPDATE [Services] SET ServiceName = ?, ServicePrice = ?, ImageService = ?, DescriptionService = ? WHERE ServiceID = ?";
    private static final String SQL_GET_SERVICES_DETAIL_BY_ID = "SELECT * FROM [Services] WHERE ServiceID = ?";
    private static final String SQL_SEARCH_SERVICES_BY_NAME = "SELECT * FROM [Services] WHERE ServiceName LIKE ?";

    private Services getInfoServicesFromSQL(ResultSet resultSet) throws SQLException {
        String getServiceID = resultSet.getString("ServiceID");
        String getServiceName = resultSet.getString("ServiceName");
        double getServicePrice = resultSet.getDouble("ServicePrice");
        byte[] getImageService = resultSet.getBytes("ImageService");
        String getDescriptionService = resultSet.getString("DescriptionService");
        int getServiceStatus = resultSet.getInt("ServiceStatus");
        Timestamp getServiceCreated = resultSet.getTimestamp("ServiceCreated");

        return new Services(getServiceID, getServiceName, Base64.encode(getImageService), getDescriptionService, getServicePrice, getServiceStatus, getServiceCreated);
    }

    @Override
    protected List<Services> getServices(Connection connection, Object value, Object action) throws SQLException {
        ArrayList<Services> servicesList = new ArrayList<>();

        try {
            if (connection != null) {
                switch (action.toString()) {
                    case "Top6Service":
                        preparedStatement = connection.prepareStatement(SQL_GET_TOP_6_SERVICES);
                        break;
                    case "Top4ServiceRandom":
                        preparedStatement = connection.prepareStatement(SQL_GET_TOP_4_SERVICES_RANDOM);
                        break;
                    case "GetNext6Service":
                        preparedStatement = connection.prepareStatement(SQL_GET_NEXT_6_SERVICES);
                        preparedStatement.setInt(1, Integer.parseInt(value.toString()));
                        break;
                    case "GetAllService":
                        preparedStatement = connection.prepareStatement(SQL_GET_ALL_SERVICES);
                        preparedStatement.setInt(1, Integer.parseInt(value.toString()));
                        break;
                    case "PagingService":
                        preparedStatement = connection.prepareStatement(SQL_PAGING_SERVICES);
                        preparedStatement.setInt(1, ((int) value - 1) * 5);
                        break;
                    case "SearchByName":
                        preparedStatement = connection.prepareStatement(SQL_SEARCH_SERVICES_BY_NAME);
                        String[] cutText = value.toString().split("\\.");
                        preparedStatement.setString(1, "%" + cutText[0] + "%");
                        break;
                }

                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Services services = getInfoServicesFromSQL(resultSet);
                    servicesList.add(services);
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
        return servicesList;
    }

    @Override
    protected boolean addServices(Connection connection, Services service) throws SQLException {
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_ADD_SERVICES);
                preparedStatement.setString(1, service.getServiceID());
                preparedStatement.setString(2, service.getServiceName());
                preparedStatement.setDouble(3, service.getServicePrice());
                preparedStatement.setBytes(4, Base64.decode(service.getImageService()));
                preparedStatement.setString(5, service.getDescriptionService());
                preparedStatement.setInt(6, service.getServiceStatus());
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
    protected boolean updateServices(Connection connection, Services service, Object action) throws SQLException {
        try {
            if (connection != null) {
                switch (action.toString()) {
                    case "DeleteServices":
                        preparedStatement = connection.prepareStatement(SQL_SERVICES_STATUS);
                        preparedStatement.setInt(1, service.getServiceStatus());
                        preparedStatement.setString(2, service.getServiceID());
                        break;
                    case "EditServices":
                        preparedStatement = connection.prepareStatement(SQL_EDIT_SERVICES);
                        preparedStatement.setString(1, service.getServiceName());
                        preparedStatement.setDouble(2, service.getServicePrice());
                        preparedStatement.setBytes(3, Base64.decode(service.getImageService()));
                        preparedStatement.setString(4, service.getDescriptionService());
                        preparedStatement.setString(5, service.getServiceID());
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
    protected int countServices(Connection connection) throws SQLException {
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_GET_TOTAL_SERVICES);
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
    protected Services getServicesDetail(Connection connection, Object serviceDetail) throws SQLException {
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_GET_SERVICES_DETAIL_BY_ID);
                preparedStatement.setString(1, serviceDetail.toString());
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    return getInfoServicesFromSQL(resultSet);
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
