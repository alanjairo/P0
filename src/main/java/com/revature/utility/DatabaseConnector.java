package com.revature.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:src/main/resources/bank.db");
    }

    public static void main(String[] args) {
        try{
            try(Connection connection = createConnection())
            {
                System.out.println(connection);
            }
        }catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
