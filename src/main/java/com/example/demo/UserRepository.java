package com.example.demo;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.model.LoginUser;

public interface UserRepository extends MongoRepository<LoginUser, String>  {

	public LoginUser findByUserName(String userName);

}
