package com.niit.dao;

import java.util.List;

import com.niit.model.Person;

public interface PersonDao 
{
	
	List<Person> getAllPersons();
    Person savePerson(Person person);
	Person getPersonById(int id);
	void updatePerson(Person person);
}
