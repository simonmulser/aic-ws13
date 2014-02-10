package com.aic.rest;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import com.aic.core.service.CloudServiceImpl;
import com.aic.entities.InstancePool;
import com.aic.util.Util;

public class CloudRestTest extends JerseyTest {
	
	private CloudServiceImpl cloudService = (CloudServiceImpl) CloudServiceImpl.getInstance();

	@Override
	protected Application configure() {
		ResourceConfig config = new ResourceConfig(CloudRest.class);
		return config;

	}
	@Before
	public void before(){
		
	}

	@Test
	public void testGetInstancePool() {
		cloudService.updateHostPool(Util.getInstancePool());
		InstancePool response = (InstancePool) target().path("cloud/instancepool/all").request().get(InstancePool.class);
		
		assertThat(response, is(notNullValue()));
		assertThat(response.getInstances(), is(notNullValue()));
		assertThat(response.getInstances().size(), is(2));
	}  
}
