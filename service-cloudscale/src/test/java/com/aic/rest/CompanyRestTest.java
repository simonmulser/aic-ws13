package com.aic.rest;

import java.util.Collection;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.aic.core.service.AnalysisServiceImpl;
import com.aic.core.service.CompanyServiceImpl;
import com.aic.entities.Company;
import com.aic.rest.CompanyRest;
import com.aic.util.CompanyProvider;
import com.aic.util.Util;

public class CompanyRestTest extends JerseyTest {

	private CompanyServiceImpl companyService = (CompanyServiceImpl) CompanyServiceImpl.getInstance();
	private CompanyProvider companyProvider = mock(CompanyProvider.class);

	@Override
	protected Application configure() {
		ResourceConfig config = new ResourceConfig(CompanyRest.class);
		return config;

	}
	@Before
	public void before(){
		companyService.setCompanyProvider(companyProvider);
	}

	@Test
	public void testGetCompanies() {
		when(companyProvider.getCompanies()).thenReturn(Util.getCompanies());
		GenericType<Collection<Company>> genericType = new GenericType<Collection<Company>>(){};
		List<Company> response = (List<Company>) target().path("company/all").request().get(genericType);

		assertThat(response, is(notNullValue()));
		assertThat(response.size(), is(3));
	}  

	@Test
	public void testGetCompany() {
		when(companyProvider.getCompany("ibm")).thenReturn(Util.getCompany());
		final Company response = target().path("company/ibm").request().get(Company.class);

		assertThat(response, is(notNullValue()));
		assertThat(response.getName(), is("IBM"));
	}

	@Test
	public void testAddCompany() {
		List<Company> companies = Util.getCompanies();
		when(companyProvider.getCompanies()).thenReturn(companies);

		GenericType<Collection<Company>> genericType = new GenericType<Collection<Company>>(){};
		List<Company> response = (List<Company>) target().path("company/all").request().get(genericType);
		assertThat(response, is(notNullValue()));
		int count = response.size();

		Entity<Company> entity = Entity.entity(Util.getCompany(), MediaType.APPLICATION_JSON);
		target().path("company").request().post(entity);
		companies.add(Util.getCompany());

		response = (List<Company>) target().path("company/all").request().get(genericType);
		assertThat(response, is(notNullValue()));
		assertThat(response.size(), is(count + 1));
	} 
}
