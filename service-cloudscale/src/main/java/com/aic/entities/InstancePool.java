package com.aic.entities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import at.ac.tuwien.infosys.cloudscale.vm.IHost;
import at.ac.tuwien.infosys.cloudscale.vm.IHostPool;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class InstancePool {
	
	private List<Instance> instances;

	public InstancePool(){}
	
	public static InstancePool create(IHostPool hostPool) {
		List<Instance> instances = new ArrayList<Instance>();
		for(IHost host : hostPool.getHosts()){
			instances.add(Instance.create(host));
		}
		
		// TODO Auto-generated method stub
		return new InstancePool(instances);
	}
	
	private InstancePool(List<Instance> instances){
		this.instances = instances;
	}

	public List<Instance> getInstances() {
		return instances;
	}

	public void setInstances(List<Instance> instances) {
		this.instances = instances;
	}
}
