package com.aic.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.aic.core.service.CompanyServiceImpl;
import com.aic.core.service.CompanyService;
import com.aic.entities.Analysis;
import com.aic.entities.Company;
import com.aic.util.Util;

@Path("/company")
public class CompanyRest extends AbstractRest {

	private static Logger logger = Logger.getLogger(CompanyRest.class);

	private CompanyService companyService = CompanyServiceImpl.getInstance();

	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCompanies(){
		logger.info("get comapanies");
		
		GenericEntity<List<Company>> entity = new GenericEntity<List<Company>>(companyService.getCompanies()) {};
		return makeCORS(Response.ok(entity));
	}
	
	@Path("/all/{user}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCompaniesByUser(@PathParam("user") String user){
		logger.info("get comapanies");
		
		GenericEntity<List<Company>> entity = new GenericEntity<List<Company>>(companyService.getCompaniesByUser(user)) {};
		return makeCORS(Response.ok(entity));
	}

	@Path("/{company}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCompany(@PathParam("company") String company){
		logger.info("get company");

		return makeCORS(Response.ok(companyService.getCompany(company)));
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCompany(@HeaderParam("Access-Control-Request-Headers") String requestH, Company company){
		logger.info("add company " + company.getName());
		
		companyService.addCompany(company);
		return makeCORS(Response.ok("added succesful company " + company.getName()));
	}

	@OPTIONS
	public Response corsMyResource(@HeaderParam("Access-Control-Request-Headers") String requestH, @PathParam("analysis") String analysis) {
		_corsHeaders = requestH;
		return makeCORS(Response.ok(), requestH);
	}
}