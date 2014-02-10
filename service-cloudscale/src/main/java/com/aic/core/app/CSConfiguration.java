package com.aic.core.app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;

import at.ac.tuwien.infosys.cloudscale.annotations.CloudScaleConfigurationProvider;
import at.ac.tuwien.infosys.cloudscale.configuration.CloudScaleConfiguration;
import at.ac.tuwien.infosys.cloudscale.configuration.CloudScaleConfigurationBuilder;
import at.ac.tuwien.infosys.cloudscale.vm.ec2.EC2CloudPlatformConfiguration;

public class CSConfiguration {
	
	@CloudScaleConfigurationProvider
	public static CloudScaleConfiguration getConfiguration()
			throws FileNotFoundException, IOException
	{
		Properties prop = new Properties();
		InputStream input = null;
		
		EC2CloudPlatformConfiguration ec2CloudPlatformConfig = new EC2CloudPlatformConfiguration();
		ec2CloudPlatformConfig.setAwsConfigFile("ec2.properties");
		ec2CloudPlatformConfig.setAwsEndpoint("ec2.us-west-2.amazonaws.com");

		//ec2CloudPlatformConfig.setMessageQueueConfiguration(messageQueueConfig);
		ec2CloudPlatformConfig.setInstanceType("t1.micro");
		ec2CloudPlatformConfig.setImageName("CloudScale_v0.2.0");
		ec2CloudPlatformConfig.setSshKey("team3group1");
		CloudScaleConfiguration configuration = CloudScaleConfigurationBuilder
				.createLocalConfigurationBuilder().with(ec2CloudPlatformConfig)
				.withGlobalLoggingLevel(Level.ALL)
 				.with(new ScalingPolicy())
				.withMonitoring(true)
 				.withMonitoringEvents(DurationEvent.class)
 				.withMQServerHostname("ec2-54-244-99-58.us-west-2.compute.amazonaws.com")
 				.build();
		CloudScaleConfiguration.setServerContext(false);
		configuration.common().clientLogging().setDefaultLoggingLevel(Level.ALL);

		// this governs how often we run the scaling-down check for each thread (check every 5 minutes)
		configuration.common().setScaleDownIntervalInSec(60 * 5);
		configuration.common().communication().setRequestTimeout(1000*5*60);
		
		configuration.server().logging().setCustomLoggingLevel(
				"at.ac.tuwien.infosys.cloudscale.classLoader.caching.RemoteClassLoader", Level.ALL);
		configuration.server().logging().setCustomLoggingLevel(
				"at.ac.tuwien.infosys.cloudscale.classLoader.caching.fileCollectors.FileBasedFileCollector", Level.ALL);
		configuration.common().clientLogging().setCustomLoggingLevel(
				"at.ac.tuwien.infosys.cloudscale.classLoader.caching.RemoteClassProvider", Level.ALL);
		configuration.common().clientLogging().setCustomLoggingLevel(
				"at.ac.tuwien.infosys.cloudscale.classLoader.caching.fileCollectors.FileBasedFileCollector", Level.ALL);
		configuration.common().clientLogging().setCustomLoggingLevel(
				"at.ac.tuwien.infosys.cloudscale.vm.IdManager", Level.OFF);
		
		System.out.println("returning config " + configuration.toString());
		return configuration;
		
	}
}
