package com.forms.server.cruddao.api;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import com.forms.server.exception.persistence.RecordNotFoundException;

public interface IBaseApplicationService {
	
	public EntityManager getEm();

	public void setEm(EntityManager em);

	public <T> T create(T t);

	public <T> T find(Object id, Class<T> type) throws RecordNotFoundException;

	public <T> T update(T t);

	public <T> void delete(Object id, Class<T> type);

	public List findByNamedQuery(String queryName);

	public Object findSingleByNamedQuery(String queryName);

	public List findByNamedQuery(String queryName, int resultLimit);

	public List findByNamedQuery(String queryName, Map<String, Object> parameters);

	public List findByNamedNativeQuery(String queryName, Map<String, Object> parameters);

	public Object findSingleByNamedQuery(String queryName,Map<String, Object> parameters) throws RecordNotFoundException;

	public List findByNamedQuery(String queryName, Map<String, Object> parameters,int resultLimit);

	public List findByNamedNativeQuery(String queryName,
			Map<String, Object> parameters, int resultLimit);

	public <T> T getEntityBySid(Class<T> clazz, String sid) throws RecordNotFoundException;

	public <T> Integer getIdBySid(Class<T> clazz, String sid);

	public <T> String getSidById(Class<T> clazz, Integer id) throws RecordNotFoundException;
	public <T> void persist(T t);
	

	public <T> T findEntityById(Class entityName, Integer id) throws RecordNotFoundException;

	public <T, U> U getToByNamedQuery(Class<T> entityClass, Class<U> dtoClass,
			String namedQuery, Map<String, Object> parameters, String mapId) throws RecordNotFoundException;

	public <T, U> U getToByNamedQuery(Class<T> entityClass, Class<U> dtoClass,
			String namedQuery, Map<String, Object> parameters) throws RecordNotFoundException;

	public <T> T findEntityBySid(Class<T> clazz, String sid) throws RecordNotFoundException;
}
