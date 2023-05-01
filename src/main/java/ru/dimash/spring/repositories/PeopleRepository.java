package ru.dimash.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dimash.spring.models.Person;

public interface PeopleRepository extends JpaRepository<Person, Integer> {
}
