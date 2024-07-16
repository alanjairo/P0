package com.revature.repo;

import com.revature.entity.Bank;
import com.revature.entity.User;
import com.revature.exception.UserSQLException;
import com.revature.utility.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteBankDao implements BankDao{
    public Bank createNewAccount (Bank newAccountCred)
    {
        String sql = "insert into account values (?, ?, ?, ?, ?, ?)";
        try(Connection connection = DatabaseConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, newAccountCred.getAccountId());
            preparedStatement.setString(2, newAccountCred.getBankName());
            preparedStatement.setString(3, newAccountCred.getAccountType());
            preparedStatement.setDouble(4, newAccountCred.getBalance());
            preparedStatement.setInt(5, newAccountCred.getAccountUser());
            preparedStatement.setInt(6, newAccountCred.getJointUser());

            int result = preparedStatement.executeUpdate();
            if(result == 1)
            {
                return newAccountCred;
            }
            throw new UserSQLException("Account Could Not Be Created, Please Try Again.");
        }catch (SQLException e)
        {
            throw new UserSQLException(e.getMessage());
        }
    }

    @Override
    public List<Bank> getAllAccounts() {
        String sql = "select * from account";
        try(Connection connection = DatabaseConnector.createConnection())
        {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Bank> accounts = new ArrayList<>();

            while(resultSet.next())
            {
                Bank accountRecord = new Bank();
                accountRecord.setAccountId(resultSet.getInt("account_id"));
                accountRecord.setBankName(resultSet.getString("bank_name"));
                accountRecord.setAccountType(resultSet.getString("account_type"));
                accountRecord.setBalance(resultSet.getDouble("balance"));
                accountRecord.setAccountUser(resultSet.getInt("account_user"));
                accountRecord.setJointUser(resultSet.getInt("joint_user"));

                accounts.add(accountRecord);
            }
            return accounts;
        }catch(SQLException e)
        {
            throw new UserSQLException(e.getMessage());
        }
    }

    @Override
    public void updateMoney(Bank bank)
    {
        String sql = "update account set balance = ? where account_id = ?";
        try(Connection connection = DatabaseConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setDouble(1, bank.getBalance());
            preparedStatement.setInt(2, bank.getAccountId());

            //int result =
            preparedStatement.executeUpdate();
            //if(result == 1)
            //{
            //    return bank;
            //}
            //throw new UserSQLException("Account Could Not Be Updated, Please Try Again.");
        }catch (SQLException e)
        {
            throw new UserSQLException("Account Could Not Be Updated, Please Try Again.");
        }
    }

    @Override
    public void closeAccount(Bank bank)
    {
        String sql = "delete from account where account_id = ?";
        try(Connection connection = DatabaseConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, bank.getAccountId());
            preparedStatement.executeUpdate();
        }catch (SQLException e)
        {
            throw new UserSQLException("Account Could Not Be Deleted, Please Try Again.");
        }
    }

}
