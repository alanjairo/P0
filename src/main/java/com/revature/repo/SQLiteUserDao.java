package com.revature.repo;

import com.revature.entity.User;
import com.revature.exception.UserSQLException;
import com.revature.utility.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteUserDao implements UserDao{

    @Override
    public User createUser(User newUserCred) {
        String sql = "insert into user values (?, ?)";
        try(Connection connection = DatabaseConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, newUserCred.getUsername());
            preparedStatement.setString(2, newUserCred.getPassword());

            int result = preparedStatement.executeUpdate();
            if(result == 1)
            {
                return newUserCred;
            }
            throw new UserSQLException("User Could Not Be Created, Please Try Again.");
        }catch (SQLException e)
        {
            throw new UserSQLException(e.getMessage());
        }

    }

    @Override
    public List<User> getAllUsers() {

        String sql = "select * from user";
        try(Connection connection = DatabaseConnector.createConnection())
        {
            Statement  statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<User> users = new ArrayList<>();

            while(resultSet.next())
            {
                User userRecord = new User();
                userRecord.setUserId(resultSet.getInt("user_id"));
                userRecord.setUsername(resultSet.getString("username"));
                userRecord.setPassword(resultSet.getString("password"));
                users.add(userRecord);
            }
            return users;
        }catch(SQLException e)
        {
            throw new UserSQLException(e.getMessage());
        }
    }
}
