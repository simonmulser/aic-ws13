package com.aic.core.app;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.aic.core.service.CompanyServiceImpl;
import com.aic.core.service.CompanyService;
import com.aic.entities.Analysis;
import com.aic.entities.Company;
import com.aic.util.CompanyProvider;
import com.aic.util.Util;

public class CompanyServiceTest{

	private CompanyServiceImpl companyService;
	private CompanyProvider companyProvider;
	private CompanyProvider companyProviderOld;
	
	@Before
	public void setUp(){
		companyService = (CompanyServiceImpl) CompanyServiceImpl.getInstance();
		companyProviderOld  = companyService.getCompanyProvider();
		companyProvider = mock(CompanyProvider.class);
		companyService.setCompanyProvider(companyProvider);
	}
	
	@After
	public void setAfter(){
		companyService.setCompanyProvider(companyProviderOld);
	}

	
	@Test
	public void testGetCompanies(){
		List<Company> companies = Util.getCompanies();
		when(companyProvider.getCompanies()).thenReturn(companies);
		
		assertThat(companyService.getCompanies().size(), is(companies.size()));
	}
	
	@Test
	public void testGetCompany(){
		Company company = Util.getCompany();
		when(companyProvider.getCompany("ibm")).thenReturn(company);
		
		assertThat(companyService.getCompany("ibm").getName(), is(company.getName()));
	}
	
	@Test
	public void testAddCompany(){
		Company company = Util.getCompany();
		companyService.addCompany(company);
		
		verify(companyProvider).add(company);
	}
	
	@Test
	public void testAddCompanyWithNoSlugShouldSetSlug(){
		Company company = Util.getCompany();
		company.setSlug(null);
		companyService.addCompany(company);
		
		ArgumentCaptor<Company> argument = ArgumentCaptor.forClass(Company.class);
		verify(companyProvider).add(argument.capture());
		assertThat(argument.getValue().getSlug(), is("ibm"));
	}
}
