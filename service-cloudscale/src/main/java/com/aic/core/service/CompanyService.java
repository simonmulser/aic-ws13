package com.aic.core.service;


import java.util.List;

import com.aic.entities.Company;

public interface CompanyService {
	
	public List<Company> getCompanies();
	
	public List<Company> getCompaniesByUser(String name);
	
	public Company getCompany(String company);

	public void addCompany(Company company);

}
