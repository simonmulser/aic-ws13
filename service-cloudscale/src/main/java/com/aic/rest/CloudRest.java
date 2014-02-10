package com.aic.rest;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;


import com.aic.core.service.CloudService;
import com.aic.core.service.CloudServiceImpl;




@Path("/cloud")
public class CloudRest extends AbstractRest{

	private static Logger logger= Logger.getLogger(CloudRest.class);

	private CloudService cloudService = CloudServiceImpl.getInstance();

	@Path("/instancepool/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInstancePool(){
		logger.info("get hostPool");
		
		return makeCORS(Response.ok(cloudService.getInstancePool()));
	}

}