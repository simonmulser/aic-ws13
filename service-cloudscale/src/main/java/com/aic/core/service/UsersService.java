package com.aic.core.service;


import java.util.List;
import java.util.Map;

import com.aic.entities.Company;
import com.aic.entities.User;

public interface UsersService {
	
	public List<User> getUsers();
	
	public Map<String, User> getUsersAsMap();
	
	public User getUser(String name);

//	public void addCompany(Company company);

}
