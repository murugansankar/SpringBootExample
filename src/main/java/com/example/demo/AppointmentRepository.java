package com.example.demo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.model.Appointment;

public interface AppointmentRepository extends MongoRepository<Appointment, String> {

 List<Appointment> findByClinicIdAndAppointmentDate(String clinicId,String doctorName);


}
