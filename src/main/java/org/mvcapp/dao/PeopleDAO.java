package org.mvcapp.dao;

import org.mvcapp.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.*;
import java.util.List;

public class PeopleDAO {
    private static int COUNT;

    private final JdbcTemplate JdbcTemplate;

    @Autowired
    public PeopleDAO(JdbcTemplate jdbcTemplate) {
        JdbcTemplate = jdbcTemplate;
    }

    public static List<Person> showAll() {
        return jdbcTemplate.query("SELECT * FROM Person", PersonMapper personMapper){
//        List<Person> people = new ArrayList<>();
//        try {
//            Statement statement = connection.createStatement();
//            String SQL = "SELECT * FROM Person";
//            ResultSet result = statement.executeQuery("SQl");
//
//            while (result.next()) {
//                Person person = new Person();
//                person.setId(result.getInt("id"));
//                person.setName(result.getString("name"));
//                people.add(person);
//            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return people;
    }

    public void addNew (Person person) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO Person VALUES (?,?)"); // id, name
            preparedStatement.setInt(1, person.getId()); // id is AUTO-incremented
            preparedStatement.setString(2, person.getName());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Person show(int id){
        Person person = null;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM Person WHERE id = ?" );
            preparedStatement.setInt(1, id);
            ResultSet  resultSet = preparedStatement.executeQuery();

            person = new Person();
            person.setName(resultSet.getString("name"));
            person.setId(id); // from parameter

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return person;
    }

    public void update (int id, Person person){  // to update data in the particular person
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE Person SET name=? WHERE id = ?" );
            preparedStatement.setInt(1, person.getId()); // id is AUTO-incremented
            preparedStatement.setString(2, person.getName());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(int id){ //delete a person for its id
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM Person WHERE id = ?" );
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
