package com.springdata.example.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springdata.example.entity.User;



public interface UserRepository extends CrudRepository<User, String> {
	
	public List<User> findAllByOrderByFirstNameAsc();

	public List<User> findAllByOrderByFirstNameDesc();
	
	public List<User> findAllByOrderByAgeAsc();
	
	public List<User> findAllByOrderByAgeDesc();

	public User findByFirstName( String firstName);

	public List<User> findByLastName(String lastName);
	public List<User> findByAge();

	public User findByFirstNameAndLastName( String firstName, String lastName);

	public int countByFirstName(String firstname);

	public String deleteByFirstName(String firstname);


	@Query("{'firstName':{$regex:?0,$options:'i'}}")
	public User findByNameIgnoreCase(String firstName);
}
