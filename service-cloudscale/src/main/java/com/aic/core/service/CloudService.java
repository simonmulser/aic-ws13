package com.aic.core.service;

import com.aic.entities.InstancePool;


public interface CloudService {
	
	public void updateHostPool(InstancePool pool);
	
	public InstancePool getInstancePool();

}
