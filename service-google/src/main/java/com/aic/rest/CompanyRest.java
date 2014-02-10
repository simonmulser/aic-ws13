package com.aic.rest;

import com.aic.core.service.CompanyService;
import com.aic.core.service.CompanyServiceImpl;
import com.aic.entities.Companies;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/company")
public class CompanyRest extends AbstractRest {

	private static Logger logger = Logger.getLogger(CompanyRest.class);

	private CompanyService companyService = CompanyServiceImpl.getInstance();

	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCompanies(){
		logger.info("get comapanies");
		
		GenericEntity<List<Companies>> entity = new GenericEntity<List<Companies>>(companyService.getCompanies()) {};
		return makeCORS(Response.ok(entity));
	}
	
	@Path("/all/{user}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCompaniesByUser(@PathParam("user") String user){
		logger.info("get comapanies");
		
		GenericEntity<List<Companies>> entity = new GenericEntity<List<Companies>>(companyService.getCompaniesByUser(user)) {};
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
	public Response addCompany(@HeaderParam("Access-Control-Request-Headers") String requestH, Companies companies){
		logger.info("add companies " + companies.getName());
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" +requestH + " " + companies);
		companyService.addCompany(companies);
		return makeCORS(Response.ok("added succesful companies " + companies.getName()));
	}

	@OPTIONS
	public Response corsMyResource(@HeaderParam("Access-Control-Request-Headers") String requestH, @PathParam("analysis") String analysis) {
		_corsHeaders = requestH;
		return makeCORS(Response.ok(), requestH);
	}
}