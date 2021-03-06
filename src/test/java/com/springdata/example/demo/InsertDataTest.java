package com.springdata.example.demo;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springdata.example.entity.Account;
import com.springdata.example.entity.Movement;
import com.springdata.example.entity.User;
import com.springdata.example.repository.AccountRepository;
import com.springdata.example.repository.MovementRepository;
import com.springdata.example.repository.UserRepository;



@RunWith(SpringRunner.class)
@SpringBootTest


public class InsertDataTest {
	@Autowired UserRepository userRepository;
	@Autowired AccountRepository accountRepository;
	@Autowired MovementRepository movementRepository;


	//Alta de usuarios, cuentas y movimientos para pruebas postman

	@Test
	public void insertData() throws ParseException {
		List<Account> user1Accounts=new ArrayList<>();
		List <Movement>movements= new ArrayList<>();

		movements.add(saveTestMovement(new BigDecimal(1000), "EUR","00490001239874561",new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse("01-01-2017")));
		movements.add(saveTestMovement(new BigDecimal(1000), "EUR","00490001239874561", new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse("02-01-2017")));
		movements.add(saveTestMovement(new BigDecimal(1000), "EUR","00490001239874561",new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse("03-01-2017")));
		movements.add(saveTestMovement(new BigDecimal(200), "EUR","00490001239874561",new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse("04-01-2017")));
		movements.add(saveTestMovement(new BigDecimal(50), "EUR","00490001239874561",new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse("05-01-2017")));
		movements.add(saveTestMovement(new BigDecimal(50), "EUR","00490001239874561",new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse("06-01-2017")));
		movements.add(saveTestMovement(new BigDecimal(-1000), "EUR","00490001239874561",new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse("07-01-2017")));
		movements.add(saveTestMovement(new BigDecimal(-250), "EUR","00490001239874561",new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse("08-01-2017")));
		movements.add(saveTestMovement(new BigDecimal(50), "EUR","00490001239874561",new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse("09-01-2017")));
		movements.add(saveTestMovement(new BigDecimal(-1000), "EUR","00490001239874561",new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse("10-01-2017")));
		movements.add(saveTestMovement(new BigDecimal(-300), "EUR","00490001239874561",new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse("11-01-2017")));
		movements.add(saveTestMovement(new BigDecimal(20), "EUR","00490001239874561",new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse("12-01-2017")));


		Account account1=saveTestAccount("00490001239874561","SANTANDER",movements,new BigDecimal(820) );
		user1Accounts.add(account1);
		saveTestUser("1234","Beatriz", "Lopez",30,user1Accounts);
		saveTestAccount("10490001239874561","SANTANDER",null,new BigDecimal(456) );
		saveTestAccount("20490001239874561","SANTANDER",null ,new BigDecimal(788));
		saveTestAccount("30490001239874561","SANTANDER",null ,new BigDecimal(234));
		saveTestAccount("40490001239874561","SANTANDER",null,new BigDecimal(1234) );


		saveTestUser("5678","John", "Smith",20,null);
		saveTestUser("9963","Pepito", "Grillo",52, null);
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


	private Account saveTestAccount(String accountNumber,  String bankName, List<Movement>movements,BigDecimal balance) {
		Account account= new Account();
		account.setAccountNumber(accountNumber);
		account.setBankName(bankName);
		account.setBalance(balance);
		account.setMovements(movements);
		return accountRepository.save(account);
	}

	private Movement saveTestMovement(BigDecimal amount, String currency, String account, Date date) {
		Movement movement= new Movement();
		movement.setAmount(amount);
		movement.setDate(date);
		movement.setCurrency("EUR");
		movement.setAccountNumber(account);
		return movementRepository.save(movement);
	}

}
