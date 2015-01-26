package com.forms.server.cruddao.api;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import com.forms.server.exception.ApplicationException;
import com.forms.server.exception.persistence.RecordNotFoundException;
/**
 * 
 * @author vikash
 * @param <T>
 */
public interface IBaseApplicationService<T extends Serializable> {

	public EntityManager getEntityManager();

	public void setEntityManager(EntityManager em);

	public void create(T t) throws ApplicationException;

	@SuppressWarnings("rawtypes")
	public T find(Integer id, Class type) throws RecordNotFoundException;

	public T update(T t) throws ApplicationException;

	public void delete(Integer id, @SuppressWarnings("rawtypes") Class type) throws ApplicationException;

	public List<T> findListByNamedQueryWithoutParam(String queryName) throws ApplicationException;

	public T findSingleByNamedQueryWithoutParam(String queryName) throws RecordNotFoundException;

	public List<T> findListByNamedQueryWithoutParam(String queryName, int resultLimit) throws ApplicationException;

	public List<T> findListByNamedQueryWithParam(String queryName,
			Map<String, Object> parameters) throws ApplicationException;

	public List<T> findListByNamedNativeQueryWithParam(String queryName,
			Map<String, Object> parameters);

	public T findSingleByNamedQueryWithParam(String queryName,
			Map<String, Object> parameters) throws RecordNotFoundException,
			ApplicationException;

	public List<T> findListByNamedQueryWithParmAndLimit(String queryName,
			Map<String, Object> parameters, int resultLimit) throws ApplicationException;

	public List<T> findListByNamedNativeQueryWithParamAndLimit(
			String queryName, Map<String, Object> parameters, int resultLimit);

	public T getEntityBySid(@SuppressWarnings("rawtypes") Class clazz,
			String sid) throws RecordNotFoundException, ApplicationException;

	public Integer getIdBySid(@SuppressWarnings("rawtypes") Class clazz,
			String sid) throws RecordNotFoundException, ApplicationException;

	public String getSidById(@SuppressWarnings("rawtypes") Class clazz,
			Integer id) throws RecordNotFoundException, ApplicationException;

	public T findEntityById(@SuppressWarnings("rawtypes") Class entityName,
			Integer id) throws RecordNotFoundException, ApplicationException;

	public <T, U> U getToByNamedQuery(@SuppressWarnings("rawtypes") Class entityClass, Class<U> dtoClass,
			String namedQuery, Map<String, Object> parameters, String mapId)
			throws RecordNotFoundException, ApplicationException;

	public <T, U> U getToByNamedQuery(@SuppressWarnings("rawtypes") Class entityClass, Class<U> dtoClass,
			String namedQuery, Map<String, Object> parameters)
			throws RecordNotFoundException, ApplicationException;

	public T findEntityBySid(@SuppressWarnings("rawtypes") Class clazz,
			String sid) throws RecordNotFoundException, ApplicationException;
	
    public T findToObjectBySid(@SuppressWarnings("rawtypes") Class clazz,String sid,Class convertedClass) throws ApplicationException;
	
}
