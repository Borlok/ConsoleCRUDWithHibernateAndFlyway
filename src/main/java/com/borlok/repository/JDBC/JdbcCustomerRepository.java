package com.borlok.repository.JDBC;

import com.borlok.model.Account;
import com.borlok.model.AccountStatus;
import com.borlok.model.Customer;
import com.borlok.model.Specialty;
import com.borlok.repository.CustomerRepository;

import java.sql.*;
import java.util.*;

public class JdbcCustomerRepository implements CustomerRepository {
    private ResourceBundle sqlCommandsResource;

    public JdbcCustomerRepository() {
        sqlCommandsResource = Utils.getResourceForSqlCommands();
    }

    @Override
    public Customer create(Customer customer) {
        try (Connection connection = Utils.getConnection()) {
            try {
                connection.setAutoCommit(false);
                Statement statement = connection.createStatement();
                statement.executeUpdate(
                        sqlCommandsResource.getString("createCustomer"));
                customer.setId(addSpecialtiesOfCustomerToDBThenReturnCustomerId(connection, customer));
                createAccountOfCustomerToDB(connection, customer);
                connection.commit();
                statement.close();
                return convertResultSetToCustomer(getCustomerResultSetById(connection, customer.getId()));
            } catch (Exception e) {
                connection.rollback();
                System.err.println("Что-то пошло не так в методе create() CustomerRepository: Выполнен откат\n" + e);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            System.err.println("Что-то пошло не так в методе create() CustomerRepository\n" + e);
        }
        return ifReturnNull();
    }

    private int addSpecialtiesOfCustomerToDBThenReturnCustomerId(Connection connection, Customer customer) throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery(
                sqlCommandsResource.getString("getLastCustomerId"));
        int customerId = getCustomerId(resultSet);
        PreparedStatement preparedStatement = connection
                .prepareStatement(sqlCommandsResource
                        .getString("addSpecialtiesToCustomer"));

        for (Specialty s : customer.getSpecialties()) {
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, s.getId());
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        preparedStatement.close();
        resultSet.close();
        return customerId;
    }

    private void createAccountOfCustomerToDB(Connection connection, Customer customer) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                sqlCommandsResource.getString("createAccount"));
        preparedStatement.setString(1, customer.getAccount().getName());
        preparedStatement.setInt(2, customer.getId());
        preparedStatement.setInt(3,
                AccountStatus.valueOf(customer.getAccount().getStatus().name()).ordinal() + 1);
        preparedStatement.execute();
        preparedStatement.close();
    }

    private int getCustomerId(ResultSet resultSet) throws SQLException {
        resultSet.next();
        return resultSet.getInt(1);
    }

    @Override
    public Customer getById(Integer id) {
        try (Connection connection = Utils.getConnection()) {
            return convertResultSetToCustomer(getCustomerResultSetById(connection, id));
        } catch (SQLException e) {
            System.err.println("Что-то пошло не так в методе getById() CustomerRepository\n" + e);
        }
        return ifReturnNull();
    }

    private ResultSet getCustomerResultSetById(Connection connection, Integer id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                sqlCommandsResource.getString("getCustomerById"));
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }

    private Customer ifReturnNull() {
        Customer customer = new Customer();
        Account account = new Account("DELETED", AccountStatus.DELETED);
        customer.setAccount(account);
        return customer;
    }

    private Customer convertResultSetToCustomer(ResultSet resultSet) throws SQLException {
        Set<Specialty> specialties = new HashSet<>();
        Customer customer = new Customer();
        Account account = new Account();
        while (resultSet.next()) {
            Specialty specialty = new Specialty();
            customer.setId(resultSet.getInt(1));
            account.setId(resultSet.getInt(2));
            account.setName(resultSet.getString(3));
            account.setStatus(AccountStatus.valueOf(resultSet.getString(4)));
            specialty.setName(resultSet.getString(5)); //Вместо возвращаемго null можно возвращать пустой
            specialties.add(specialty);
        }
        customer.setAccount(account);
        customer.setSpecialties(specialties);
        resultSet.close();
        return customer;
    }

    @Override
    public Customer update(Customer customer, Integer id) {
        try (Connection connection = Utils.getConnection()) {
            try {
                connection.setAutoCommit(false);
                customer.setId(id);
                getOldAccountIdSetOnNewCustomer(connection, customer);
                updateAccountOfCustomer(connection, customer);
                deleteOldAndAddNewSpecialtiesToCustomer(customer, connection);
                connection.commit();
                return convertResultSetToCustomer(getCustomerResultSetById(connection, id));
            } catch (Exception e) {
                connection.rollback();
                System.err.println("Что-то пошло не так в методе update() CustomerRepository: Выполнен откат\n" + e);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            System.err.println("Что-то пошло не так в методе update() CustomerRepository\n" + e);

        }
        return ifReturnNull();
    }

    private void getOldAccountIdSetOnNewCustomer(Connection connection, Customer customer) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement(sqlCommandsResource.getString("getAccountByCustomerId"));
        preparedStatement.setInt(1, customer.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        customer.getAccount().setId(resultSet.getInt(1));
    }

    private void updateAccountOfCustomer(Connection connection, Customer customer) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                sqlCommandsResource.getString("updateAccount")
        );
        preparedStatement.setString(1, customer.getAccount().getName());
        preparedStatement.setInt(2,
                AccountStatus.valueOf(customer.getAccount().getStatus().name()).ordinal() + 1);
        preparedStatement.setInt(3, customer.getAccount().getId());
        preparedStatement.execute();
        preparedStatement.close();
    }

    private void deleteOldAndAddNewSpecialtiesToCustomer(Customer customer, Connection connection) throws SQLException {
        int customerId = customer.getId();
        PreparedStatement preparedStatement = connection.prepareStatement(
                sqlCommandsResource.getString("deleteSpecialtiesByCustomerId"));
        preparedStatement.setInt(1, customerId);
        preparedStatement.execute();
        preparedStatement = connection.prepareStatement(
                sqlCommandsResource.getString("addSpecialtiesToCustomer"));

        for (Specialty s : customer.getSpecialties()) {
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, s.getId());
            preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();
        preparedStatement.close();
    }

    @Override
    public List<Customer> getAll() {
        try (Connection connection = Utils.getConnection()) {
            List<Customer> customers = new ArrayList<>();
            ResultSet resultSet = connection.createStatement().executeQuery(
                    sqlCommandsResource.getString("getAllCustomersId"));
            while (resultSet.next()) {
                customers.add(getById(resultSet.getInt(1)));
            }
            resultSet.close();
            return customers;
        } catch (SQLException e) {
            System.err.println("Что-то пошло не так в методе getAll() CustomerRepository\n" + e);
        }
        return new ArrayList<>();
    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = Utils.getConnection()) {
            try {
                connection.setAutoCommit(false);
                PreparedStatement preparedStatement = connection.prepareStatement(
                        sqlCommandsResource.getString("deleteAccountByCustomerId"));
                preparedStatement.setInt(1, id);
                preparedStatement.execute();
                preparedStatement = connection.prepareStatement(
                        sqlCommandsResource.getString("deleteSpecialtiesByCustomerId"));
                preparedStatement.setInt(1, id);
                preparedStatement.execute();
                preparedStatement = connection.prepareStatement(
                        sqlCommandsResource.getString("deleteCustomer"));
                preparedStatement.setInt(1, id);
                preparedStatement.execute();
                preparedStatement.close();
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
                System.err.println("Что-то пошло не так в методе delete() CustomerRepository: Выполнен откат\n" + e);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            System.err.println("Что-то пошло не так в update()\n" + e);
        }
    }

    @Override
    public String toString() {
        return "JdbcCustomerRepository";
    }
}
