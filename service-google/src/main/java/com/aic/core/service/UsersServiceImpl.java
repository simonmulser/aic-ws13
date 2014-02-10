package com.aic.core.service;

import com.aic.entities.User;
import com.aic.util.UsersProvider;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

public class UsersServiceImpl implements UsersService{

	private Logger logger;

	//private static UsersServiceImpl instance;

	private static UsersProvider usersProvider;

	public UsersServiceImpl() {
		logger = Logger.getLogger(UsersServiceImpl.class);
		usersProvider = new UsersProvider();
	}

//	public static CompanyService getInstance(){
//		if(instance == null){
//			instance = new UsersServiceImpl();
//		}
//		return instance;
//	}

//	@Override
//	public List<Companies> getCompanies() {
//		logger.info("get companies");
//		
//		return companyProvider.getCompanies();
//	}
//
//	@Override
//	public Companies getCompany(String company) {
//		logger.info("get company " + company);
//		
//		return companyProvider.getCompany(company);
//	}
//	
//	@Override
//	public void addCompany(Companies company) {
//		logger.info("add company " + company.getName());
//		
//		company.setSlug(company.getName().toLowerCase());
//		companyProvider.add(company);	
//	}
//
//	public CompanyProvider getCompanyProvider() {
//		return companyProvider;
//	}
//
//	public void setCompanyProvider(CompanyProvider companyProvider) {
//		this.companyProvider = companyProvider;
//	}

	@Override
	public List<User> getUsers() {
		return usersProvider.getUsers();
	}
	
	@Override
	public Map<String, User> getUsersAsMap() {
		return usersProvider.getUsersAsMap();
	}

	@Override
	public User getUser(String name) {
		return usersProvider.getUser(name);
	}
}
