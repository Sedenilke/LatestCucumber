package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DatabaseUtils {
    private static ResultSet rSet;
    /**
     * This method creates
     *
     * @param sqlQuery
     * return obj
     */
    private static Connection conn = null;
    private static Statement stm = null;
    private static ResultSetMetaData rSetMetaData = null;
     //method for getting a resultset
    public static ResultSet getResultSet(String query) {

        try {
            conn = DriverManager.getConnection(ConfigReader.getPropertyValue("databaseurl"),
                    ConfigReader.getPropertyValue("databaseusername"),
                    ConfigReader.getPropertyValue("databasepassword"));
            stm = conn.createStatement();
            rSet = stm.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rSet;
    }

    /**
     * This method extract data from ResultSet and stores into List of Maps
     */

    public static ResultSetMetaData getRsetMetadata(String query) {
        rSet = getResultSet(query);
        try {
            rSetMetaData = rSet.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rSetMetaData;

    }

    public static List<Map<String, String>> getListOfMapsFromRSet(String query) {
        rSetMetaData = getRsetMetadata(query);
        List<Map<String, String>> listFromRset = new ArrayList<>();
        Map<String, String> map;
        try {
            while (rSet.next()) {
                //to give the order we need to choose linkedhashmap
                map = new LinkedHashMap<>();
                for (int i = 1; i <= rSetMetaData.getColumnCount(); i++) {
                    String key = rSetMetaData.getColumnName(i);
                    String value = rSet.getString(key); // rSet.getString("id"); rSet.getString("FirstName");
                    map.put(key, value); // we add the values inside the map
                }
                // System.out.println(map);
                // we created key-value pairs based on the columns and related row value. here
                //we are adding the first row columnname+columnvalue pair maps inside the list:
                listFromRset.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listFromRset;
    }
}

