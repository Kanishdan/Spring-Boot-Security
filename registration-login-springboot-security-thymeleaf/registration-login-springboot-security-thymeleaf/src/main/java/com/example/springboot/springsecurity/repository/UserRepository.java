package com.example.springboot.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.springsecurity.controller.dto.UserRegistrationDto;
import com.example.springboot.springsecurity.model.User;


//We create an interface named UserRepository that extends JpaRepository.   By extending JpaRepository, we automatically get access to CRUD methods that allow for saving users, looking up users, and deleting users.
//Since our User class has attributes named email and resetToken, we can define some methods in our interface to generate dynamic queries.   This means we don't need to write any SQL statements.

//By default, Spring Data JPA will automatically parse the method name and create a query from it. 
//In the code example, the findByEmail() method is equivalent to the SQL query:
//SELECT * FROM user WHERE email = ?
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public User findByEmail(String email);
	public User save(UserRegistrationDto registration);
	//In the code example, the findByEmail() method is equivalent to the SQL query:
									//SELECT * FROM user WHERE email = ?
}