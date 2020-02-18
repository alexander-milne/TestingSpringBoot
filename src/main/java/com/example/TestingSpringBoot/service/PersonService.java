package com.example.TestingSpringBoot.service;

import com.example.TestingSpringBoot.dao.PersonDao;
import com.example.TestingSpringBoot.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    private final PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("postgres") PersonDao personDao) {
        this.personDao = personDao;
    }

    public int addPerson(Person person) {
        System.out.println(" personId: " + person.getId() + " person name: " + person.getName());
        if (person.getId() == null){
            return addPerson(null, person);
        } else {
            return addPerson(person.getId(), person);
        }

    }

    public int addPerson(UUID personId, Person person){
        System.out.println(" personId: " + person.getId() + " person name: " + person.getName());
        personId = Optional.ofNullable(personId)
                .orElse(UUID.randomUUID());
        System.out.println(" personId: " + person.getId() + " person name: " + person.getName());
        //if (person.getId().equals(null)){
        return personDao.insertPerson(personId, person);
        //} else {
            //return personDao.insertPerson(person.getId(), person);
        //}

    }

    public List<Person> getAllPeople() {
        return personDao.selectAllPeople();
    }

    public Optional<Person> getPersonById(UUID id){
        return personDao.selectPersonById(id);
    }

    public int deletePerson(UUID id) {
        return personDao.deletePersonById(id);
    }

    public int updatePerson(UUID id, Person person) {
        return personDao.updatePersonById(id, person);
    }

}
