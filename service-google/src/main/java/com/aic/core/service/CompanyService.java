package com.aic.core.service;


import com.aic.entities.Companies;

import java.util.List;

public interface CompanyService {
	
	public List<Companies> getCompanies();
	
	public List<Companies> getCompaniesByUser(String name);
	
	public Companies getCompany(String company);

	public void addCompany(Companies companies);

}
