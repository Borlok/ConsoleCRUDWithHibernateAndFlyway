package com.borlok.repository.JDBC;

import com.borlok.model.Specialty;
import com.borlok.repository.SpecialtyRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class JdbcSpecialtyRepository implements SpecialtyRepository {
    private ResourceBundle sqlCommandsResource;

    public JdbcSpecialtyRepository() {
        sqlCommandsResource = Utils.getResourceForSqlCommands();
    }

    @Override
    public Specialty create(Specialty specialty) {
        try {
            Connection connection = Utils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sqlCommandsResource.getString("createSpecialty"));
            preparedStatement.setString(1,specialty.getName());
            preparedStatement.execute();
            ResultSet resultSet = getResultSetOfLastSpecialty(connection);
            return convertResultSetToSpecialty(resultSet);
        } catch (SQLException e) {
            System.err.println("что-то пошло не так в методе create() SpecialtyRepository\n" + e);
        }
        return ifReturnNull();
    }

    private Specialty convertResultSetToSpecialty(ResultSet resultSet) throws SQLException {
        Specialty specialty = new Specialty();
        while (resultSet.next()) {
            specialty.setId(resultSet.getInt(1));
            specialty.setName(resultSet.getString(2));
        }
        resultSet.close();
        return specialty;
    }

    @Override
    public Specialty getById(Integer id) {
        try {
            Connection connection = Utils.getConnection();
            return convertResultSetToSpecialty(getSpecialtyResultSetById(connection, id));
        } catch (SQLException e) {
            System.err.println("что-то пошло не так в методе getById() SpecialtyRepository\n" + e);
        }
        return ifReturnNull();
    }

    private ResultSet getSpecialtyResultSetById(Connection connection, Integer id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                sqlCommandsResource.getString("getSpecialtyById"));
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }

    private ResultSet getResultSetOfLastSpecialty(Connection connection) throws SQLException {
        return connection.createStatement().executeQuery(
                sqlCommandsResource.getString("getLastSpecialty"));
    }

    @Override
    public Specialty update(Specialty specialty, Integer id) {
        try {
            Connection connection = Utils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sqlCommandsResource.getString("updateSpecialty"));
            preparedStatement.setString(1,specialty.getName());
            preparedStatement.setInt(2,id);
            preparedStatement.execute();

            return convertResultSetToSpecialty(getSpecialtyResultSetById(connection, id));
        } catch (SQLException e) {
            System.err.println("что-то пошло не так в методе update() SpecialtyRepository\n" + e);
        }
        return ifReturnNull();
    }

    private Specialty ifReturnNull() {
        return new Specialty("DELETED");
    }

    @Override
    public List<Specialty> getAll() {
        try {
            Connection connection = Utils.getConnection();
            ResultSet resultSet = connection.createStatement()
                    .executeQuery(sqlCommandsResource.getString("getAllSpecialties"));
            List<Specialty> specialties = new ArrayList<>();
            while (resultSet.next()) {
                Specialty specialty  = new Specialty();
                specialty.setId(resultSet.getInt(1));
                specialty.setName(resultSet.getString(2));
                specialties.add(specialty);
            }
            return specialties;
        } catch (SQLException e) {
            System.err.println("что-то пошло не так в методе getAll() SpecialtyRepository\n" + e);
        }
        return new ArrayList<>();
    }

    @Override
    public void delete(Integer id) {
        try {
            Connection connection = Utils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sqlCommandsResource.getString("deleteSpecialty"));
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("что-то пошло не так в методе delete() SpecialtyRepository\n" + e);
        }
    }

    @Override
    public String toString() {
        return "JdbcSpecialtyRepository";
    }
}
