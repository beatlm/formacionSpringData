package com.springdata.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springdata.example.entity.Account;
import com.springdata.example.entity.User;
import com.springdata.example.repository.AccountRepository;
import com.springdata.example.repository.MovementRepository;
import com.springdata.example.repository.UserRepository;


@RunWith(SpringRunner.class)
@SpringBootTest

public class DemoApplicationTests {
	@Autowired UserRepository userRepository;
	@Autowired AccountRepository accountRepository;
	@Autowired MovementRepository movementRepository;




	//Búsqueda de usuarios findByFirstName, findByLastName
	@Test
	public void findByFirstName() {
		User userSearch=userRepository.findByFirstName("Beatriz");
		assertNull(userSearch);


		User user1=saveTestUser("1234","Beatriz", "Lopez",55,null);
		User user2=saveTestUser("5678","Adrian", "Lopez",43,null);



		assertNotNull(userRepository.findByFirstName("Beatriz"));
		
		List <User> searchByLastName=userRepository.findByLastName("Lopez");

		assertEquals(2,searchByLastName.size());

		userRepository.delete(user1);
		userRepository.delete(user2);
		assertEquals(0,userRepository.count());
	}


	//Busqueda de usuarios por nombre y apellido
	@Test
	public void findByFirstNameAndLastName() {
		User userSearch=userRepository.findByFirstNameAndLastName( "Beatriz","Lopez");
		assertNull(userSearch);


		User user1=saveTestUser("1234","Beatriz", "Lopez",67,null);


		userSearch=userRepository.findByFirstNameAndLastName( "Beatriz","Lopez");
		assertNotNull(userSearch);

		userRepository.delete(user1);
		assertEquals(0,userRepository.count());
	}


	//Busqueda de usuarios personalizada findByNameIgnoreCase
	@Test
	public void findByFirstNameNoCaseSensitive() {
		User userSearch=userRepository.findByNameIgnoreCase("BEATRIZ");

		assertNull(userSearch);


		User user1=saveTestUser("1234","Beatriz", "Lopez",34,null);

		userSearch=userRepository.findByNameIgnoreCase("BEATRIZ");
		assertNotNull(userSearch);

		userRepository.delete(user1);
		assertEquals(0,userRepository.count());
	}


	//Metodos count
	@Test
	public void countByFirstName() {
		saveTestUser("1234","Beatriz", "Lopez",23, null);
		saveTestUser("2345","Beatriz", "Moreno",33, null);
		saveTestUser("4456","Pepe", "Moreno",45, null);
		assertEquals(2, userRepository.countByFirstName("Beatriz"));
		 
		userRepository.deleteAll();
		assertEquals(0L, userRepository.count());
		assert(true);
	}
	
	//Metodos order asc
		@Test
		public void findAllByOrderByAge() {
			saveTestUser("1234","Beatriz", "Lopez",30, null);
			saveTestUser("2345","Felipe", "Moreno",23, null);
			saveTestUser("4456","María", "Moreno",50, null);
			assertEquals(23, userRepository.findAllByOrderByAgeAsc().get(0).getAge());
			assertEquals(50, userRepository.findAllByOrderByAgeAsc().get(2).getAge()); 
			
			assertEquals(50, userRepository.findAllByOrderByAgeDesc().get(0).getAge());
			assertEquals(23, userRepository.findAllByOrderByAgeDesc().get(2).getAge()); 
			
			userRepository.deleteAll();
			assertEquals(0L, userRepository.count());
			assert(true);
		}



/*Metodos privados para guardar usuarios, cuentas y movimientos*/
	private User saveTestUser(String nif,String firstName, String lastName,int age, List<Account> accounts) {
		User user1= new User();
		user1.setFirstName(firstName);
		user1.setNif(nif);
		user1.setLastName(lastName);
		user1.setAge(age);
		if(accounts!=null ) {
			user1.setAccounts(accounts);
		}
		return userRepository.save(user1);

	}

	

}
