package com.aic.rest;

import com.aic.core.service.UsersService;
import com.aic.core.service.UsersServiceImpl;
import com.aic.entities.User;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/user")
public class UsersRest extends AbstractRest {

	private static Logger logger = Logger.getLogger(UsersRest.class);

	private UsersService userService = new UsersServiceImpl();

	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers(){
		logger.info("get users");
		
		GenericEntity<List<User>> entity = new GenericEntity<List<User>>(userService.getUsers()) {};
		return makeCORS(Response.ok(entity));
	}

	@Path("/{user}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCompany(@PathParam("user") String user){
		logger.info("get user");

		return makeCORS(Response.ok(userService.getUser(user)));
	}
	
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response addCompany(@HeaderParam("Access-Control-Request-Headers") String requestH, Companies company){
//		logger.info("add company " + company.getName());
//		
//		userService.addCompany(company);
//		return makeCORS(Response.ok("added succesful company " + company.getName()));
//	}

	@OPTIONS
	public Response corsMyResource(@HeaderParam("Access-Control-Request-Headers") String requestH, @PathParam("analysis") String analysis) {
		_corsHeaders = requestH;
		return makeCORS(Response.ok(), requestH);
	}
}