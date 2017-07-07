package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.model.Appointment;
import com.model.Form;
import com.model.ModelError;
import com.model.LoginUser;
import com.model.UserException;
import com.mongodb.DB;
import com.validate.Validation;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller
public class FormController {
	
	
	@Autowired
	private FormRepository repository;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AppointmentRepository appointmentRepository;
	private Validation valid = new Validation();
	Gson gson = new Gson();
	
	
	 private Facebook facebook;
	    private ConnectionRepository connectionRepository;

	    public FormController(Facebook facebook, ConnectionRepository connectionRepository) {
	        this.facebook = facebook;
	        this.connectionRepository = connectionRepository;
	    }
	 
	    
	    @PostMapping("/details")
	    public @ResponseBody String helloFacebook(Model model) {
	       
	        String [] fields = { "id", "email",  "first_name", "last_name","name","gender" };
	        User userProfile = facebook.fetchObject("me", User.class, fields);
	        Gson gson = new Gson();
	        String json = gson.toJson(userProfile);
	        return json;

	    }

	@GetMapping("/")
	public String uploading() {
		return "redirect:/LoginRegistrationForm/index.html";
	}
	@GetMapping("/registerpage")
	public String reg() {
		return "redirect:/LoginRegistrationForm/formJson.html";
	}
	@GetMapping("/view")
	public String view() {
		return "view";
	}
	
	@GetMapping("/delete")
	public String delete() {
		return "delete";
	}
	@PostMapping("/register")
	public @ResponseBody LoginUser userRegistration(@RequestBody(required=false) String registerDetails) throws UserException {
		System.out.println(registerDetails);
		LoginUser user = new LoginUser();
		List<ModelError> errorList = new ArrayList<ModelError>();
		user= gson.fromJson(registerDetails, LoginUser.class);
		valid.vaildateUser(user, errorList);
		if(errorList != null && errorList.size() > 0)
		{
		throw new UserException("validation failure", errorList);
			
		}
		userRepository.save(user);

		return user;
		
	}
	
	@PostMapping("/login")
	public @ResponseBody String userLogin(@RequestParam("userName") String userName,@RequestParam("password") String password) throws UserException
	{
		LoginUser user = userRepository.findByUserName(userName);
		
		if(!user.getPassword().equalsIgnoreCase(password))
		{
		throw new UserException("Invaild Password");	
		}
		user.setPassword("hidden");
		
		String json = gson.toJson(user);
		return json;
	
		
	}
	
	
	@PostMapping("/formJsonSubmit")
	public @ResponseBody String formPost(@RequestBody(required=false) String name) throws IOException {
		
		System.out.println("json"+name);
		Form fo =  new Form();
		
		fo = gson.fromJson(name, Form.class);
		repository.save(new Form(fo.getName(),fo.getEmail(),fo.getFavc()));

		String json = gson.toJson("Value successfully added in MongoDB");
		return json;  
	}
	
	@PostMapping("/view/viewData")
	public ModelAndView viewData(@RequestParam("name") String name,@RequestParam("email") String email) throws IOException
	{
		List<Form> formList = new ArrayList<Form>();
		
		 if(name != null && !name.isEmpty() && (email == null || email.isEmpty()))
		{
			formList.add(repository.findByName(name));
			
		}
		else if((name == null || name.equals("")) && !email.isEmpty() && email != null)
		{
			formList.add(repository.findByEmail(email));

		}
		else
			{
			
			for (Form customer : repository.findAll()) {
				formList.add(customer);
			}
			}
		 ModelAndView modelAndView = new ModelAndView();  
			modelAndView.setViewName("viewstatus");      
			modelAndView.addObject("fo", formList);    
			return modelAndView;  			
	}
	
	@PostMapping("/delete/deletedata")
	public ModelAndView  deleteData() throws IOException
	{
		String json = null;
		repository.deleteAll();
	json = gson.toJson("Deleted successfully"); 
		//json = gson.toJson("Unavailable");
		ModelAndView modelAndView = new ModelAndView();  
		modelAndView.setViewName("formstatus");      
		modelAndView.addObject("fo", json);    
		return modelAndView;  
			
	}
	
	@PostMapping("/appointment")
	public @ResponseBody String appointmentFix(@RequestBody(required=false) String name)
	{
		Appointment app = new Appointment();
		String json = null;
		app = gson.fromJson(name,Appointment.class);
		int appoint = getAppointmentNumber(app.getAppointmentDate(), app.getClinicId());
		app.setAppointmentNumber(String.valueOf(appoint));
		System.out.println(appoint);
		appointmentRepository.save(app);
		json = gson.toJson("Appointment number "+appoint+" has been created successfully");
		return json;
		
	}
	
	
	public Integer getAppointmentNumber(String appointmentDate, String clinicId)
	{
		List<Appointment> appList = new ArrayList<Appointment>();
		appList = appointmentRepository.findByClinicIdAndAppointmentDate(clinicId,appointmentDate);
		System.out.println(appList);
		
		System.out.println(appointmentRepository.findAll());
		List<Integer> appointmentNumbers = new ArrayList<>();
		int appointmentMax = 0;
		if(appList != null && appList.size() > 0)
		{
for(Appointment app : appList)
{
	appointmentNumbers.add(Integer.parseInt(app.getAppointmentNumber()));
}
appointmentMax =Collections.max(appointmentNumbers);
		}

return appointmentMax+1;
	}
	
	@PostMapping("/view")
	public @ResponseBody List<Appointment> vie()
	{
		
		return appointmentRepository.findAll();
	}
	}


	

