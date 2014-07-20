/**
 * 
 */
package com.prodyna.pac.ressys.monitoring.performance;

import java.lang.management.ManagementFactory;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import com.prodyna.pac.ressys.monitoring.performance.PerformanceMonitorMXBean;

/**
 * @author Andreas Heizenreder (andreas.heizenreder@prodyna.com)
 * 
 */
@Singleton
@Startup
public class PerformanceMonitorEnabler {

	@Inject
	private Logger log;

	private MBeanServer ms;

	/**
	 * 
	 */
	public PerformanceMonitorEnabler() {
		ms = ManagementFactory.getPlatformMBeanServer();
	}

	@PostConstruct
	public void postConstruct() {
		try {
			ms.registerMBean(new PerformanceMonitor(), new ObjectName(
					PerformanceMonitorMXBean.PERFORMANCE_MONITOR_BEAN));
		} catch (Exception e) {
			log.severe("Error while instantiate performance monitor mbean: "
					+ e.getLocalizedMessage());
		}
	}

	@PreDestroy
	public void preDestroy() {
		try {
			ms.unregisterMBean(new ObjectName(
					PerformanceMonitorMXBean.PERFORMANCE_MONITOR_BEAN));
		} catch (MBeanRegistrationException e) {
			log.severe("Error while unregister performance mbean: "
					+ e.getLocalizedMessage());
		} catch (InstanceNotFoundException e) {
			log.severe("Error while unregister performance mbean: "
					+ e.getLocalizedMessage());
		} catch (MalformedObjectNameException e) {
			log.severe("Error while unregister performance mbean: "
					+ e.getLocalizedMessage());
		}
	}

}
