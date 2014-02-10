package com.aic.core.service;


import com.aic.entities.Companies;
import com.aic.util.CompanyProvider;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class CompanyServiceImpl implements CompanyService{

	private Logger logger;

	private static CompanyServiceImpl instance;

	private CompanyProvider companyProvider;

	private CompanyServiceImpl() {
		logger = Logger.getLogger(CompanyServiceImpl.class);
		companyProvider = CompanyProvider.getInstance();
	}

	public static CompanyService getInstance(){
		if(instance == null){
			instance = new CompanyServiceImpl();
		}
		return instance;
	}

	@Override
	public List<Companies> getCompanies() {
		logger.info("get companies");
		
		return companyProvider.getCompanies();
	}
	
	@Override
	public List<Companies> getCompaniesByUser(String name) {
		logger.info("get companies by user");
		List<Companies> unfiltered = companyProvider.getCompanies();
		List<Companies> filtered = new ArrayList<Companies>();
		for(Companies c : unfiltered){
			if(c.getUser().equals(name)){
				filtered.add(c);
			}
		}
		return filtered;
	}

	@Override
	public Companies getCompany(String company) {
		logger.info("get company " + company);
		
		return companyProvider.getCompany(company);
	}
	
	@Override
	public void addCompany(Companies companies) {
		logger.info("add companies " + companies.getName());
		
		companies.setSlug(companies.getName().toLowerCase());
		companyProvider.add(companies);
	}

	public CompanyProvider getCompanyProvider() {
		return companyProvider;
	}

	public void setCompanyProvider(CompanyProvider companyProvider) {
		this.companyProvider = companyProvider;
	}
}
