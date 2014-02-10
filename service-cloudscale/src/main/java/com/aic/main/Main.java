package com.aic.main;

import java.io.Closeable;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.simple.SimpleContainer;
import org.glassfish.jersey.simple.SimpleContainerFactory;

import com.aic.rest.AnalysisRest;
import com.aic.rest.CloudRest;
import com.aic.rest.CompanyRest;
import com.aic.rest.UsersRest;



/**
 * This class launches the web application in an embedded Jetty container. This is the entry point to your application. The Java
 * command that is used for launching should fire this main method.
 */
public class Main {
	
	private static Logger log = Logger.getLogger(Main.class);
	
    public static void main(String[] args) throws Exception{
    	
    	URI baseUri = UriBuilder.fromUri("http://localhost/").port(8080).build();
        ResourceConfig config = new ResourceConfig(AnalysisRest.class);
        config.register(CompanyRest.class);
        config.register(CloudRest.class);
        config.register(UsersRest.class);
        Closeable server = SimpleContainerFactory.create(baseUri, config);
        log.info("server is started");
        
    }

}
