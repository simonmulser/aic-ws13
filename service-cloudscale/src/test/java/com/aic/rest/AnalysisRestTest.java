package com.aic.rest;

import java.util.ArrayList;
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

import com.aic.core.service.AnalysisServiceImpl;
import com.aic.entities.Analysis;
import com.aic.entities.Company;
import com.aic.rest.CompanyRest;
import com.aic.util.Util;

public class AnalysisRestTest extends JerseyTest {

	private AnalysisServiceImpl analysisService = (AnalysisServiceImpl) AnalysisServiceImpl.getInstance();

	@Override
	protected Application configure() {
		ResourceConfig config = new ResourceConfig(AnalysisRest.class);
		return config;

	}
	@Before
	public void before(){
	}

	@Test
	public void testGetAnalysesWithSizeZero() {
		analysisService.setAnalyses(new ArrayList<Analysis>());
		GenericType<Collection<Analysis>> genericType = new GenericType<Collection<Analysis>>(){};

		final List<Analysis> response = (List<Analysis>) target().path("analysis/all").request().get(genericType);
		assertThat(response, is(notNullValue()));
		assertThat(response.size(), is(0));
	} 

	@Test
	public void testGetAnalyses() {
		analysisService.setAnalyses(Util.getAnalyses());
		GenericType<Collection<Analysis>> genericType = new GenericType<Collection<Analysis>>(){};

		final List<Analysis> response = (List<Analysis>) target().path("analysis/all").request().get(genericType);
		assertThat(response, is(notNullValue()));
		assertThat(response.size(), is(3));
	} 
}
