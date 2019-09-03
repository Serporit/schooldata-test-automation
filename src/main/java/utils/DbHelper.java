package utils;

import reporting.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbHelper {

    public static int countMessagesInTableByUniqueId(String dbName, String tableName, String uniqueId) {
        String call = "select * from " + tableName + " where SOURCE_SERVICE_ID = '" + uniqueId + "'";
        int count = 0;
        PreparedStatement statement;
        try {
            statement = ConnectionManager.getInstance().getConnection(dbName).prepareCall(call);
            Logger.info("Counting records by id " + uniqueId + " in " + dbName + " / " + tableName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count++;
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Logger.info("Records found: " + count);
        return count;
    }

    public static List<String> callQueryAndGetColumnAsList(String dbName, String command, String resultColumn) {
        List<String> result = new ArrayList<>();
        ResultSet resultSet = executeCommand(dbName, command);
        try {
            while (resultSet.next()) {
                result.add(resultSet.getString(resultColumn));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    public static ResultSet executeCommand(String dbName, String command) {
        Connection connection = ConnectionManager.getInstance().getConnection(dbName);
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Logger.debug("Executing command:\n\t" + command + "\n\tin " + dbName);
        try {
            statement = connection.prepareCall(command);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return resultSet;
    }

    public static Object executeSelect(String dbName, String command, String resultCollumn) {
        ResultSet resultSet = executeCommand(dbName, command);
        try {
            if (resultSet.next()) {
                return resultSet.getObject(resultCollumn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static Object executeSelect(String dbName, String command) {
        return executeSelect(dbName, command, "RESULT");
    }

    public static int getDistrictsCount(String searchQuery) {
        int result = ((Long) executeSelect("schooldata_stage", "select (select count(*) from schooldata_stage.districts where full_name like '%" + searchQuery + "%' and deleted=0) + (select count(*) from schooldata_stage.cmos where full_name like '%" + searchQuery + "%' and deleted=0) as result;")).intValue();
        Logger.info("colleges count (from DB) : " + result);
        return result;
    }

    public static int getSchoolsCount(String searchQuery) {
        String sq = "Texas";
        int result = ((Long) executeSelect("schooldata_stage", "select count(*) as result from schooldata_stage.schools where full_name like '%" + searchQuery + "%' and organization_classification_id in ('07','11','10','09','08') and deleted=0 and is_private=0;")).intValue();
        Logger.info("colleges count (from DB) : " + result);
        return result;
    }

    public static int getCollegesCount(String searchQuery) {
        String sq = "Texas";
        int result = ((Long) executeSelect("schooldata_stage", "select count(*) as result from schooldata_stage.colleges as c inner join schooldata_stage.college_types as ct on c.college_type_id = ct.id where ct.type = 'College' and c.full_name like '%" + searchQuery + "%';")).intValue();
        Logger.info("colleges count (from DB) : " + result);
        return result;
    }
}