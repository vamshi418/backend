package com.niit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.PersonDao;
import com.niit.model.Person;

@RestController

public class PersonController {
	
@Autowired
private PersonDao personDao;


//API - RESTful service - Representation State Transfer
@RequestMapping(value="/getallpersons",method=RequestMethod.GET)
public ResponseEntity<List<Person>> getAllPersons(){
List<Person> persons=personDao.getAllPersons();
if(persons.isEmpty())
return new ResponseEntity<List<Person>>(HttpStatus.NO_CONTENT);
else
return new ResponseEntity<List<Person>>(persons,HttpStatus.OK);
//return new ResponseEntity<T>(data,statuscode)
}


@RequestMapping(value="/saveperson",method=RequestMethod.POST)
public ResponseEntity<Person> savePerson(@RequestBody Person person){
person = personDao.savePerson(person);

return new ResponseEntity<Person>(person,HttpStatus.CREATED);

}
@RequestMapping(value="/getpersonbyid/{id}",method=RequestMethod.GET)
public ResponseEntity<Person> getPersonById(@PathVariable int id){
Person person=personDao.getPersonById(id);
if(person==null)
return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
else
return new ResponseEntity<Person>(person,HttpStatus.OK);
}


@RequestMapping(value="/updateperson",method=RequestMethod.PUT)
public ResponseEntity<Void> updatePerson(@RequestBody Person person){
int id=person.getId();
Person personAvailable=personDao.getPersonById(id);
if(personAvailable==null)
return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
else
personDao.updatePerson(person);
return new ResponseEntity<Void>(HttpStatus.OK);

}
}