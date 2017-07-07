package com.example.demo;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.model.Form;

public interface FormRepository extends MongoRepository<Form, String>  {

    public Form findByName(String name);
    public Form findByEmail(String email);

}
