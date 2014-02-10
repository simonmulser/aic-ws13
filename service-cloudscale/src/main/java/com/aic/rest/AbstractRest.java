package com.aic.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

public abstract class AbstractRest {
	
	protected String _corsHeaders;


	protected Response makeCORS(ResponseBuilder req, String returnMethod) {
		ResponseBuilder rb = req.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, OPTIONS");

		if (!"".equals(returnMethod)) {
			rb.header("Access-Control-Allow-Headers", returnMethod);
		}

		return rb.build();
	}

	protected Response makeCORS(ResponseBuilder req) {
		return makeCORS(req, _corsHeaders);
	}
}
