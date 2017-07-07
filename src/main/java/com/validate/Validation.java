package com.validate;

import java.util.List;

import com.model.ModelError;
import com.model.LoginUser;

public class Validation {
	
	
	@SuppressWarnings("unused")
	private boolean nullAndisEmptyCheck(String value)
	{
		if(value != null && !value.isEmpty())
		{
			return true;
		}
		return false;
		
	}
	
	
	public void vaildateUser(LoginUser user, List<ModelError> errorList)
	{
		ModelError error = null;
		if(user.getFirstName().length() >= 30)
		{
			error = new ModelError();
			error.setErrorMsg("FirstName exceeds maximum length of 30");
			errorList.add(error);
			
		}
		
		if(user.getLastName().length() >= 30)
		{
			error = new ModelError();
			error.setErrorMsg("LastName exceeds maximum length of 30");
			errorList.add(error);
		}
		if(user.getEmail().length() >= 50)
		{
			error = new ModelError();
			error.setErrorMsg("Email exceeds maximum length of 50");
			errorList.add(error);
		}
		if(user.getUserName().length() >= 80)
		{
			error = new ModelError();
			error.setErrorMsg("UserName exceeds maximum length of 80");
			errorList.add(error);
		}
		/*if(user.getHintAnswer().length() > 30)
		{
			error = new ModelError();
			error.setErrorMsg("HintAnswer exceeds maximum length of 30");	
			errorList.add(error);
		}*/
		
	}

}
