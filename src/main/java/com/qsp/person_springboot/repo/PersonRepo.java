package com.qsp.person_springboot.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qsp.person_springboot.dto.Person;

public interface PersonRepo extends JpaRepository<Person, Integer> {
	Person findByPhone(long phone);
	Person findByEmail(String email);
	List<Person> getByName(String name);
	
	

}
