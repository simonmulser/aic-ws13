package com.aic.main;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import com.aic.entities.Company;
import com.aic.util.CompanyProvider;
import com.aic.util.Util;

public class CompanyProviderTest {

	
	
	private final CompanyProvider companyProvider = CompanyProvider.getInstance();

	@Test
	public void testGetCompanies(){
		List<Company> companies = companyProvider.getCompanies();
		
		assertThat(companies, is(notNullValue()));
		assertThat(companies.size(), is(greaterThan(2)));
	}
	
	@Test
	public void testAddCompany(){
		List<Company> companies = companyProvider.getCompanies();
		
		assertThat(companies, is(notNullValue()));
		int count = companies.size();
		
		companyProvider.add(Util.getCompany());
		assertThat(companyProvider.getCompanies().size(), is(count + 1));
	}

	@Test
	public void testGetCompany(){
		Company company = companyProvider.getCompany("IBM");
		assertThat(company.getName(), is("IBM"));
		assertThat(company.getSlug(), is("ibm"));

		assertThat(company.getSentimentResults(), is(notNullValue()));
		assertThat(company.getSentimentResults().size(), is(greaterThan(2)));
		assertThat(company.getSentimentResults().get(0), is(notNullValue()));
		assertThat(company.getSentimentResults().get(0).getPositive(), is(5));
		assertThat(company.getSentimentResults().get(0).getNegative(), is(5));
		assertThat(company.getSentimentResults().get(0).getNeutral(), is(0));

		assertThat(company.getSentimentResults().get(0).getStart().getMillis(), is(1389179673012L));
		assertThat(company.getSentimentResults().get(0).getEnd().getMillis(), is(1389179673055L));
	}
}
