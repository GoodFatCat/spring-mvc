package com.github.goodfatcat.springcourse.dao;

import com.github.goodfatcat.springcourse.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        // new PersonMapper() was changed to BeanPropertyRowMapper because class person has the same name of
        // fields with database row.
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person VALUES(1,?,?,?)",
                person.getName(), person.getAge(), person.getEmail());

    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?",
                new Object[]{id}, new PersonMapper()).stream().findAny().orElse(null);
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE person SET name=?, age=?, email=? WHERE id=?",
                person.getName(), person.getAge(), person.getEmail(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }
}
