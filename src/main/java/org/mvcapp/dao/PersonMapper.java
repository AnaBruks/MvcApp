package org.mvcapp.dao;

import org.mvcapp.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person(i, "Name" + i, "test" + i + "gmail.com" + "ACity" + i + "street");
        person.setName(resultSet.getString("name"));
        person.setId(resultSet.getInt("id"));
        person.setEmail(resultSet.getString("email"));

        return person;
    }
}
