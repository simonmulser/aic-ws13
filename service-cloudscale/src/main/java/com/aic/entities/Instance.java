package com.aic.entities;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.joda.time.DateTime;

import com.aic.rest.DateTimeAdapter;

import at.ac.tuwien.infosys.cloudscale.vm.IHost;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Instance {
	
	private String ipAddress;
	
	@XmlJavaTypeAdapter(DateTimeAdapter.class)
	private DateTime startupTime;
	
	@XmlJavaTypeAdapter(DateTimeAdapter.class)
	private DateTime lastRequestTime;
	
	private double currentCPULoad;
		
	private int cloudObjectsCount;

	private int processors;

	private double freeMemory;

	private double maxMemory;

	private double usedMemory;

	public Instance(){}
	
	public static Instance create(IHost host) {
		
		return new Instance(host);
	}
	
	private Instance(IHost host){
		this.ipAddress = host.getIpAddress();
		this.startupTime = new DateTime(host.getStartupTime());
		this.lastRequestTime = new DateTime(host.getLastRequestTime());
		this.currentCPULoad = host.getCurrentCPULoad().getCpuLoad();
		this.processors = host.getCurrentCPULoad().getProcessors();
		this.usedMemory = host.getCurrentRAMUsage().getUsedMemory();
		this.freeMemory = host.getCurrentRAMUsage().getFreeMemory();
		this.maxMemory = host.getCurrentRAMUsage().getMaxMemory();
		this.cloudObjectsCount = host.getCloudObjectsCount();
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public DateTime getStartupTime() {
		return startupTime;
	}

	public void setStartupTime(DateTime startupTime) {
		this.startupTime = startupTime;
	}

	public DateTime getLastRequestTime() {
		return lastRequestTime;
	}

	public void setLastRequestTime(DateTime lastRequestTime) {
		this.lastRequestTime = lastRequestTime;
	}

	public double getCurrentCPULoad() {
		return currentCPULoad;
	}

	public void setCurrentCPULoad(double currentCPULoad) {
		this.currentCPULoad = currentCPULoad;
	}

	public int getCloudObjectsCount() {
		return cloudObjectsCount;
	}

	public void setCloudObjectsCount(int cloudObjectsCount) {
		this.cloudObjectsCount = cloudObjectsCount;
	}

	public int getProcessors() {
		return processors;
	}

	public void setProcessors(int processors) {
		this.processors = processors;
	}

	public double getFreeMemory() {
		return freeMemory;
	}

	public void setFreeMemory(double freeMemory) {
		this.freeMemory = freeMemory;
	}

	public double getMaxMemory() {
		return maxMemory;
	}

	public void setMaxMemory(double maxMemory) {
		this.maxMemory = maxMemory;
	}

	public double getUsedMemory() {
		return usedMemory;
	}

	public void setUsedMemory(double usedMemory) {
		this.usedMemory = usedMemory;
	}
}
