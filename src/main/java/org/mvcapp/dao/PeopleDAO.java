package org.mvcapp.dao;

import org.mvcapp.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
public class PeopleDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PeopleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> showAll() {
        return jdbcTemplate.query("SELECT * FROM People", new BeanPropertyRowMapper<>(Person.class));
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO People VALUES (?,?)", person.getName(), person.getEmail());
    }

    public Person showPerson(int id) { // better to rewrite -_-
        return jdbcTemplate.query("SELECT * FROM People where id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE People SET name=? , email= ? WHERE id = ?", person.getName(), person.getEmail(), id);
    }


    public void delete(int id) { //delete a person for its id
        jdbcTemplate.update("DELETE FROM People WHERE id = ?", id);

    }
}

