package org.mvcapp.dao;

import org.mvcapp.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        jdbcTemplate.update("INSERT INTO People (name, email, address) VALUES (?,?, ?)", person.getName(), person.getEmail(), person.getAddress());
    }

    public Person showPerson(int id) {
        return jdbcTemplate.query("SELECT * FROM People where email=?", new Object[]{email},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }
    public Optional<Person> showPerson(String email) {
        return jdbcTemplate.query("SELECT * FROM People where id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE People SET name=? , email= ?, address=? WHERE id = ?", person.getName(), person.getEmail(),
                person.getAddress(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM People WHERE id = ?", id);
    }

    public void batchUpdate() {
        List<Person> people = create1000People();
        jdbcTemplate.batchUpdate(" INSERT into People VALUES (?,?, ?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1, people.get(i).getId());
                        ps.setString(2, people.get(i).getName());
                        ps.setString(3, people.get(i).getEmail());
                        ps.setString(4, people.get(i).getAddress());
                    }
                    @Override
                    public int getBatchSize() {
                        return people.size();
                    }
                });
    }

    private List<Person> create1000People() {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            people.add(new Person (i, "Name" + i, "test" + i + "gmail.com" + "ACity, " + i + ", street"));
        }
        return people;
    }
}

