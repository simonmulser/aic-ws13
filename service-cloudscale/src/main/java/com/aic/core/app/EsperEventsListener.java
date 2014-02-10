package com.aic.core.app;

import org.apache.log4j.Logger;

import at.ac.tuwien.infosys.cloudscale.messaging.objects.monitoring.CPUEvent;
import at.ac.tuwien.infosys.cloudscale.messaging.objects.monitoring.RAMEvent;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class EsperEventsListener implements UpdateListener{
	
	private Logger log = Logger.getLogger(EsperEventsListener.class);

	private EPServiceProvider epService;

	public EsperEventsListener(){
		Configuration config = new Configuration();
		config.addEventType("CPUEvent",CPUEvent.class);
		config.addEventType("RAMEvent", RAMEvent.class);
		epService = EPServiceProviderManager.getDefaultProvider(config);
	}

	@Override
	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		EventBean event = newEvents[0];
		log.info(event.getUnderlying());

	}

	public EPServiceProvider getEpService() {
		return epService;
	}

	public void setEpService(EPServiceProvider epService) {
		this.epService = epService;
	}
	
	
}
