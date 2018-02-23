package com.springdata.example.entity;

import org.springframework.data.rest.core.config.Projection;



@Projection (name = "userInfo2", types = {User.class})

public interface UserInfo2{
	String getFirstName();
	
 

}


