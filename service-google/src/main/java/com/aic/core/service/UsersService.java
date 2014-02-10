package com.aic.core.service;


import com.aic.entities.User;

import java.util.List;
import java.util.Map;

public interface UsersService {
	
	public List<User> getUsers();
	
	public Map<String, User> getUsersAsMap();
	
	public User getUser(String name);

//	public void addCompany(Companies company);

}
