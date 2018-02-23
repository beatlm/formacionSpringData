package com.springdata.example.demo;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springdata.example.repository.AccountRepository;
import com.springdata.example.repository.MovementRepository;
import com.springdata.example.repository.UserRepository;



@RunWith(SpringRunner.class)
@SpringBootTest


public class RemoveDataTest {
	@Autowired UserRepository userRepository;
	@Autowired AccountRepository accountRepository;
	@Autowired MovementRepository movementRepository;


	@Test
	public void insertData() throws ParseException {
		movementRepository.deleteAll();
		accountRepository.deleteAll();
		userRepository.deleteAll();
		assertEquals(0,movementRepository.count());
		assertEquals(0,accountRepository.count());
		assertEquals(0,userRepository.count());
	}

	
}
