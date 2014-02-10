package com.aic.core.service;


import org.apache.log4j.Logger;

import com.aic.entities.InstancePool;

import at.ac.tuwien.infosys.cloudscale.vm.IHostPool;


public class CloudServiceImpl implements CloudService{

	private Logger log;

	private static CloudServiceImpl instance;

	private InstancePool hostPool;

	private CloudServiceImpl() {
		log = Logger.getLogger(CompanyServiceImpl.class);
	}

	public static CloudService getInstance(){
		if(instance == null){
			instance = new CloudServiceImpl();
		}
		return instance;
	}

	@Override
	public synchronized void updateHostPool(InstancePool hostPool) {
		log.info("update hostPool");

		this.hostPool = hostPool;
	}

	@Override
	public synchronized InstancePool getInstancePool() {
		log.info("get hostPool");

		return hostPool;
	}
}
