package com.springdata.example.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;



@Projection (name = "userInfo", types = {User.class})

public interface UserInfo{
	@Value("#{target.firstName} #{target.lastName}")
	String getFullName();
	
	@Value("#{2018-target.age}")
	String getBirthYear();
	
	String getAge();

}


