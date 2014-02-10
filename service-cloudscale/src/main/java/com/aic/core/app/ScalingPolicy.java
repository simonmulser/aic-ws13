package com.aic.core.app;

import java.util.UUID;

import com.aic.core.service.CloudService;
import com.aic.core.service.CloudServiceImpl;
import com.aic.core.service.CompanyServiceImpl;
import com.aic.entities.InstancePool;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;

import at.ac.tuwien.infosys.cloudscale.policy.IScalingPolicy;
import at.ac.tuwien.infosys.cloudscale.vm.ClientCloudObject;
import at.ac.tuwien.infosys.cloudscale.vm.IHost;
import at.ac.tuwien.infosys.cloudscale.vm.IHostPool;

public class ScalingPolicy implements IScalingPolicy{
	
	private Object lock = new Object();
	
	private EsperEventsListener esperEventsListener;
	private String expressionCPU = "select * from `CPUEvent`";
	private String expressionRAM = "select * from `RAMEvent`";
	private EPStatement statementCPU;
	private EPStatement statementRAM;
	
	CloudService cloudService = CloudServiceImpl.getInstance();
	
	public ScalingPolicy(){
		esperEventsListener = new EsperEventsListener();
		statementCPU = esperEventsListener.getEpService().getEPAdministrator().createEPL(expressionCPU);
		statementCPU.addListener(esperEventsListener);		
		statementRAM = esperEventsListener.getEpService().getEPAdministrator().createEPL(expressionRAM);
		statementRAM.addListener(esperEventsListener);	
	}


	@Override
	public IHost selectHost(ClientCloudObject newCloudObject, IHostPool hostPool) 
	{

		System.out.println("Starting to select host");		synchronized (lock) {
			
			InstancePool pool = InstancePool.create(hostPool);
			cloudService.updateHostPool(pool);
			
			IHost selected = null;
			
			for (IHost host : hostPool.getHosts()) {
				int hostedObjects = host.getCloudObjectsCount();
				System.out.println(String.format(
					"Host %s (%s) has current %d active objects",
					host.getId().toString(), host.getIpAddress(), hostedObjects
				));
				if(hostedObjects < 5) {
					selected = host;
				}
			}			
			if(selected == null){
				System.out.println("Found no suitable host, scaling up");
			}else{
				System.out.println("Using host "+selected.getId().toString());
			}			
			if(selected != null)
				return selected;
			
		}
		
		IHost newHost = hostPool.startNewHost( );
		return newHost;
	}

	@Override
	public boolean scaleDown(IHost host, IHostPool hostPool) {

		boolean result = true;
		
		synchronized (lock) {
			System.out.println("Checking whether to scale down host " + host.getId().toString());
			
			InstancePool pool = InstancePool.create(hostPool);
			cloudService.updateHostPool(pool);

			
			if(!host.isOnline()) {
				result = false;
			}

			if(host.getCloudObjectsCount() > 0) {
				result = false;
			}

			if(!otherUnusedHost(hostPool, host.getId())) {
				result = false;			}
		}
		if(result) {
			System.out.println("Scaling down host " + host.getId().toString());
		}
		return result;
	}

	private boolean otherUnusedHost(IHostPool hostPool, UUID id) {

		for(IHost host : hostPool.getHosts()) {
			if(!host.getId().equals(id) && host.getCloudObjectsCount() == 0)
				return true;
		}
		return false;
	}

}
