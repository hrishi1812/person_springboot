package com.qsp.person_springboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.person_springboot.dto.Person;
import com.qsp.person_springboot.repo.PersonRepo;
@RestController
public class PersonController {
	@Autowired
	private PersonRepo repo;
	@PostMapping("/signup")
	public Person signUp(@RequestBody Person person) {
		return repo.save(person);
		
	}
	@PostMapping("/save/all")
	public List<Person> saveAll(@RequestBody List<Person> persons) {
		return repo.saveAll(persons);
	}
	
	@GetMapping("/find")
	public Person findPerson(@RequestParam int id) {
//		return repo.findById(id).get();
		
//		to avoid no such element exception
		Optional<Person> optional=repo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}
	}
	
	
	@DeleteMapping("/del")
	public Person deletePerson(@RequestParam int id) {
		Optional<Person> o=repo.findById(id);
		if(o.isEmpty()) {
			return null;
		}else {
			repo.deleteById(id);
			return o.get();
			
		}
	}
	
	@DeleteMapping("/delete/person")
	public Person delPerson(@RequestParam int id) {
		Optional<Person> o=repo.findById(id);
		if(o.isPresent()) {
			repo.delete(o.get());
			return o.get();
		}else {
			return null;
			
		}
	}
	
	
	
	@GetMapping("/find/all")
	public List<Person> findAll() {
		return repo.findAll();
	}
	@PutMapping("/update")
	public Person updatePerson(@RequestParam int id,@RequestBody Person person) {
		Optional<Person> optional=repo.findById(id);
		if(optional.isPresent()) {
			person.setId(id);
			return repo.save(person);
		}else {
			return null;
		}
		
	}
	@PatchMapping("/update/phone")
	public Person updatePerson(int id,long phone) {
		Optional<Person> optional=repo.findById(id);
		if(optional.isEmpty()) {
			return null;
		}else {
			Person person=optional.get();
			person.setPhone(phone);
			return repo.save(person);
		}
		
	}
	@PatchMapping("/update/email")
	public Person updatePerson(int id,String email) {
		
		Optional<Person> optional=repo.findById(id);
		if(optional.isEmpty()) {
			return null;
		}else {
			Person person=optional.get();
			person.setEmail(email);
			return repo.save(person);
		}
	}
	@GetMapping("/find/phone")
	public Person findPerson(long phone) {
		return repo.findByPhone(phone);
		
	}
	@GetMapping("/find/email")
	public Person findPerson(String email) {
		return repo.findByEmail(email);
		
	}
	@GetMapping("/find/name")
	public List<Person> findPersonByName(String name) {
		return repo.getByName(name);
		
		
	}
	@DeleteMapping("/delete/phone")
	public Person deleteByPhone(Long phone) {
		Person person=repo.findByPhone(phone);
		if(person!=null) {
			repo.delete(person);
			return person;
		}else {
			return null;
		}
		
		
	}
	@DeleteMapping("/delete/email")
	public Person deleteByEmail(String email) {
		
		Person person=repo.findByEmail(email);
		if(person!=null) {
			repo.delete(person);
			return person;
		}else {
			return null;
		}
		
	}
	@GetMapping("/login")
	public String logIn(String email,long phone) {
		Person person=repo.findByEmail(email);
		if(person==null) {
			return "User not Registred";
		}
		else {
			if(person.getPhone()==phone) {
				return "Login successfully";
				
			}else {
				return "Wrong password";
			}
		}
		
		
	}
	
}
