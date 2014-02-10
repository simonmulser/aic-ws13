package com.aic.core.service;



import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.aic.entities.Company;
import com.aic.util.CompanyProvider;

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
	public List<Company> getCompanies() {	
		logger.info("get companies");
		
		return companyProvider.getCompanies();
	}
	
	@Override
	public List<Company> getCompaniesByUser(String name) {	
		logger.info("get companies by user");
		List<Company> unfiltered = companyProvider.getCompanies();
		List<Company> filtered = new ArrayList<Company>();
		for(Company c : unfiltered){
			if(c.getUser().equals(name)){
				filtered.add(c);
			}
		}
		return filtered;
	}

	@Override
	public Company getCompany(String company) {
		logger.info("get company " + company);
		
		return companyProvider.getCompany(company);
	}
	
	@Override
	public void addCompany(Company company) {
		logger.info("add company " + company.getName());
		
		company.setSlug(company.getName().toLowerCase());
		companyProvider.add(company);	
	}

	public CompanyProvider getCompanyProvider() {
		return companyProvider;
	}

	public void setCompanyProvider(CompanyProvider companyProvider) {
		this.companyProvider = companyProvider;
	}
}
