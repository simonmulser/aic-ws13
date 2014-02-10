package com.aic.rest;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Scanner;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.DeserializationConfig;


@Provider
@Consumes({"application/json"})
public class JsonMessageBodyReader implements MessageBodyReader {

	@Override
	public boolean isReadable(Class arg0, Type arg1, Annotation[] arg2,
			MediaType arg3) {
		return true;
	}

	@Override
	public Object readFrom(Class arg0, Type arg1, Annotation[] arg2,
			MediaType arg3, MultivaluedMap arg4, InputStream arg5)
			throws IOException, WebApplicationException {
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	return mapper.readValue(arg5, arg0);
	}
}