package com.forms.server.cruddao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.forms.server.common.FormsConstants;


public final class ApplicationService implements Cloneable {
	private EntityManagerFactory entityManagerFactory=null;
	private EntityManager entityManager=null;

	private static boolean objectCreated=false;
	private static ApplicationService service;
	
	public ApplicationService(){
		entityManagerFactory=Persistence.createEntityManagerFactory(FormsConstants.Persistence_unit_name);
		setEntityManager(entityManagerFactory.createEntityManager());
	}
	
	public static ApplicationService getService(){
		if(objectCreated)return service;
		else{
			service=new ApplicationService();
			objectCreated=true;
			return service;
		}
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return null;
	}
}
