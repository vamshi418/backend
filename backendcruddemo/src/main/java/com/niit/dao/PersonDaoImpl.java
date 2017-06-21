package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Person;



@Repository
public class PersonDaoImpl implements PersonDao{
	
@Autowired
private SessionFactory sessionFactory;



public PersonDaoImpl() {}
	


public PersonDaoImpl(SessionFactory sessionFactory) {
	
	this.sessionFactory=sessionFactory;
}



public List<Person> getAllPersons() {
Session session=sessionFactory.openSession();
Query query=session.createQuery("from Person");
List<Person> person=query.list();
session.close();
return person;
}



public Person savePerson(Person person) {
Session session=sessionFactory.openSession();
session.save(person);

session.flush();
session.close();
return person;
}
public Person getPersonById(int id) {
Session session=sessionFactory.openSession();
//select * from person_batch19 where id=?
Person person=(Person)session.get(Person.class, id);
session.close();
return person;

}
public void updatePerson(Person person) {
Session session=sessionFactory.openSession();
session.update(person);
session.flush();
session.close();
}

}

