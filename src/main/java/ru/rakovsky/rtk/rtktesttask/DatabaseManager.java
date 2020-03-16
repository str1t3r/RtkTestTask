package ru.rakovsky.rtk.rtktesttask;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import org.json.JSONArray;

/**
 *
 * @author str1t
 */
public class DatabaseManager {
    
    public static final int DEFAULT_LIMIT = 2;
    public static final int DEFAULT_OFFSET = 0;
    
    private static final String DB_USER = "username";
    private static final String DB_PASSWORD = "password";
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    
    public static JSONArray getDataFromDatabase() {
       return DatabaseManager.getDataFromDatabase(DEFAULT_LIMIT, DEFAULT_OFFSET);
    }
    
    public static JSONArray getDataFromDatabase(int limit, int offset) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (Exception e) {
            System.err.println("Oracle JDBC Driver not found");
        }
        ArrayList<Color> colors = new ArrayList();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("select " + Color.NAME_COLUMN + ", " + Color.NUMBER_COLUMN + " from " + Color.TABLE_NAME + " offset " + offset + " rows fetch next " + limit + " rows only");
                ) {
            
            while (rs.next()) {
                String colorNumber = rs.getString(Color.NUMBER_COLUMN);
                String colorName = rs.getString(Color.NAME_COLUMN);
                colors.add(new Color(colorName, colorNumber));
            }
            return new JSONArray(colors);
        } catch (SQLException e) {
            System.err.println("SQL Exception:");
            e.printStackTrace();
        }
        return new JSONArray();
    }
    
    
    public static String callOracleProcedure(Connection conn, String procedureName, String param) {
        try (CallableStatement cstmt = conn.prepareCall("{? = call " + procedureName + "(?)}");) {
            cstmt.registerOutParameter(1, Types.VARCHAR);
            cstmt.setString(2, param);
            cstmt.executeUpdate();
            String result = cstmt.getString(1);
            return result;
        } catch (SQLException e) {
            System.err.println("SQL Exception:");
            e.printStackTrace();
        }
        return null;
    }
   
}
