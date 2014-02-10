package com.aic.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.aic.entities.Company;
import com.aic.entities.SentimentResult;
import com.aic.entities.User;

public class UsersProvider {

	//private static UsersProvider instance;

	private Logger logger;

	//private List<User> users;
	private static Map<String, User> users;

	public UsersProvider() {
		logger = Logger.getLogger(UsersProvider.class);
		initialize();
	}

//	public static UsersProvider getInstance(){
//		if(instance == null){
//			instance = new UsersProvider();
//		}
//		return instance;
//	}

	public List<User> getUsers(){
		logger.info("get users");

		return new ArrayList<User>(users.values());
	}
	
	public Map<String, User> getUsersAsMap(){
		logger.info("get users");

		return users;
	}
	
	public User getUser(String name){
		return users.get(name);
	}

	public void add(User user){
		users.put(user.getUsername(), user);
	}
	
//	public void add(Company company) {
//		logger.info("add company " + company.getName());
//		companies.add(company);		
//	}


	public void initialize() {
		
		if(users != null && users.size() > 0){
			return;
		}
		
		logger.info("initialize");
		JSONParser parser = new JSONParser();
		users = new HashMap<String, User>();

		try {

			JSONArray jsonArray = (JSONArray) parser.parse(new InputStreamReader(UsersProvider.class.getClassLoader().getResourceAsStream("users.json")));

			for(Object userObject : jsonArray){
				JSONObject jsonUserObject = (JSONObject) userObject;
				String name = (String) jsonUserObject.get("username");
				String pw = (String) jsonUserObject.get("password");
				
				users.put(name, new User(name, pw));
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info("initialized with " + users.size() + " companies");
	}

}
