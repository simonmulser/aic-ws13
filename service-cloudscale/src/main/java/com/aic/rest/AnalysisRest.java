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

import com.aic.core.service.AnalysisService;
import com.aic.core.service.AnalysisServiceImpl;
import com.aic.entities.Analysis;
import com.aic.entities.Company;
import com.aic.util.Util;

@Path("/analysis")
public class AnalysisRest extends AbstractRest {

	private static Logger logger = Logger.getLogger(AnalysisRest.class);

	private AnalysisService analysisService = AnalysisServiceImpl.getInstance();


	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAnalyses(){
		logger.info("get analyses");

		GenericEntity<List<Analysis>> entity = new GenericEntity<List<Analysis>>(analysisService.getAnalyses()) {};
		return makeCORS(Response.ok(entity));
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response startAnalysis(@HeaderParam("Access-Control-Request-Headers") String requestH, @PathParam("analysis") String analysisSlug, Analysis analysis){
		logger.info("start analysis " + analysisSlug);

		return makeCORS(Response.ok(analysisService.startAnalysis(analysis)));
	}

	@OPTIONS
	public Response corsMyResource(@HeaderParam("Access-Control-Request-Headers") String requestH, @PathParam("analysis") String analysis) {
		_corsHeaders = requestH;
		return makeCORS(Response.ok(), requestH);
	}
}
