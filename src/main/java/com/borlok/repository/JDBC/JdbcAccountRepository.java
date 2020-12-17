package com.borlok.repository.JDBC;

import com.borlok.model.Account;
import com.borlok.model.AccountStatus;
import com.borlok.repository.AccountRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class JdbcAccountRepository implements AccountRepository {
    private ResourceBundle sqlCommandsResource;

    public JdbcAccountRepository() {
        sqlCommandsResource = Utils.getResourceForSqlCommands();
    }

    @Override
    public Account create(Account account) {
        try (Connection connection = Utils.getConnection()) {
            try {
                connection.setAutoCommit(false);
                int customerId = createAndGetCustomerId(connection);
                PreparedStatement preparedStatement = connection.prepareStatement(
                        sqlCommandsResource.getString("createAccount"));
                preparedStatement.setString(1, account.getName());
                preparedStatement.setInt(2, customerId);
                preparedStatement.setInt(3,
                        AccountStatus.valueOf(account.getStatus().name()).ordinal() + 1);
                preparedStatement.execute();
                ResultSet resultSet = connection.createStatement().executeQuery(
                        sqlCommandsResource.getString("getLastAccount"));

                connection.commit();
                return getReturnedAccount(resultSet);
            } catch (Exception e) {
                connection.rollback();
                System.err.println("Что-то пошло не так в методе create Account: Выполнен откат\n" + e);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            System.err.println("Что-то пошло не так в методе create Account\n" + e);
        }
        return ifReturnNull();
    }

    private Account ifReturnNull() {
        return new Account("DELETED", AccountStatus.DELETED);
    }

    private int createAndGetCustomerId(Connection connection) throws SQLException {
        connection.createStatement().executeUpdate(
                sqlCommandsResource.getString("createCustomer"));
        ResultSet resultSet =  connection.createStatement()
                .executeQuery(sqlCommandsResource.getString("getLastCustomerId"));
        resultSet.next();
        return resultSet.getInt(1);
    }

    @Override
    public Account getById(Integer id) {
        try (Connection connection = Utils.getConnection()) {
            return getReturnedAccount(getAccountResultSetById(connection, id));
        } catch (SQLException e) {
            System.err.println("Что-то пошло не так в getById()\n" + e);
        }
        return ifReturnNull();
    }

    private ResultSet getAccountResultSetById(Connection connection, Integer id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                sqlCommandsResource.getString("getAccountById"));
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }

    @Override
    public Account update(Account account, Integer id) {
        try (Connection connection = Utils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sqlCommandsResource.getString("updateAccount"));
            preparedStatement.setString(1, account.getName());
            preparedStatement.setInt(2, AccountStatus.valueOf(account.getStatus().name()).ordinal() + 1);
            preparedStatement.setInt(3, id);
            preparedStatement.execute();
            return getReturnedAccount(getAccountResultSetById(connection, id));
        } catch (SQLException e) {
            System.err.println("Что-то пошло не так в update()\n" + e);
        }
        return ifReturnNull();
    }

    private Account getReturnedAccount(ResultSet resultSet) throws SQLException {
        Account account = new Account();

        resultSet.next();
        account.setId(resultSet.getInt("Id"));
        account.setName(resultSet.getString("Name"));
        account.setStatus(AccountStatus.valueOf(resultSet.getString("Status")));

        resultSet.close();
        return account;
    }

    @Override
    public List<Account> getAll() {
        try (Connection connection = Utils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     sqlCommandsResource.getString("getAllAccounts"))) {

            List<Account> accounts = new ArrayList<>();

            while (resultSet.next()) {
                Account account = new Account();
                account.setId(resultSet.getInt(1));
                account.setName(resultSet.getString(2));
                account.setStatus(AccountStatus.valueOf(resultSet.getString(3)));
                accounts.add(account);
            }

            return accounts;
        } catch (SQLException e) {
            System.err.println("Проблема в методе getAll() класса AccountRepository\n" + e);
        }
        return new ArrayList<>();
    }

    private int getAccountId(ResultSet resultSet) throws SQLException {
        resultSet.next();
        return resultSet.getInt(2);
    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = Utils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sqlCommandsResource.getString("getAccountById"));
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            int customerId = getAccountId(resultSet);
            preparedStatement = connection.prepareStatement(
                    sqlCommandsResource.getString("deleteAccount"));
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement = connection.prepareStatement(
                    sqlCommandsResource.getString("deleteCustomer"));
            preparedStatement.setInt(1, customerId);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Что-то пошло не так в delete()\n" + e);
        }
    }

    @Override
    public String toString() {
        return "JdbcAccountRepository";
    }
}
