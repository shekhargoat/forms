package com.forms.rest.activator;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.forms.server.service.FormLoginService;
import com.forms.server.service.FormsSignupService;
/**
 * 
 * @author vikash
 *
 */
@ApplicationPath("/")
public class JaxRsActivator extends Application {
	private static final Logger logger=LoggerFactory.getLogger(JaxRsActivator.class);
	private Set<Object> sigletons = new HashSet<Object>();
	
	private Set<Class<?>> classes = new HashSet<Class<?>>();
	static{
		
	}
	public JaxRsActivator() {
		classes.add(FormLoginService.class);
		classes.add(FormsSignupService.class);
	}

	public Set<Object> getSigletons() {
		return sigletons;
	}

	public Set<Class<?>> getClasses() {
		return classes;
	}
}
