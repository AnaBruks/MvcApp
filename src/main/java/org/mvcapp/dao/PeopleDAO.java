package org.mvcapp.dao;

import org.mvcapp.models.Person;
import org.postgresql.jdbc.PgConnection;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.List;

public class PeopleDAO {
    private static int COUNT;
//
//    private final JdbcTemplate JdbcTemplate;
//    Connection connection;
//
//    @Autowired
//    public PeopleDAO(JdbcTemplate jdbcTemplate) {
//        JdbcTemplate = jdbcTemplate;
//    }

    public void showAll() {
        return jdbcTemplate.query("SELECT * FROM People", PersonMapper personMapper)
    }

    public void save(Person person) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO People VALUES (?,?)"); // id, name
            preparedStatement.setInt(1, person.getId()); // id is AUTO-incremented
            preparedStatement.setString(2, person.getName());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Person showPerson(int id) {
        Person person = null; // we can use Optional here!
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM People WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next(); // we can have a few users with the same ID here - ITS BAD!
            person = new Person();
            person.setName(resultSet.getString("name"));
            person.setId(id); // from parameter
            person.setEmail(resultSet.getString("email"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return person;
    }

    public void update(int id, Person person) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE People SET name=? , email= ? WHERE id = ?");
            preparedStatement.setInt(3, person.getId()); // id is AUTO-incremented
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getEmail());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(int id) { //delete a person for its id
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM People WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

