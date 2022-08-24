package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionProvider{

        private static DBConnectionProvider instance = new DBConnectionProvider();
        private Connection connection;


        private final String DRIVER_NAME = "com.mysql.jdbc.Driver";
        private final String DB_URL = "jdbc:mysql://localhost:3306/events";
        private final String USERNAME = "root";
        private final String PASSWORD = "root";


        private DBConnectionProvider (){
            try {
                Class.forName(DRIVER_NAME);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        public static DBConnectionProvider getInstance() {
            return instance;
        }


        public Connection getConnection() {
            try {
                if (connection==null || connection.isClosed()){
                    connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }
    }


