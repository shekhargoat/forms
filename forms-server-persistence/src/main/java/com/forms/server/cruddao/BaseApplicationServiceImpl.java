package com.forms.server.cruddao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.forms.server.common.FormsConstants;
import com.forms.server.cruddao.api.IBaseApplicationService;
import com.forms.server.exception.ApplicationException;
import com.forms.server.exception.persistence.RecordNotFoundException;
/**
 * @author vikash
 * @param <T>
 */
@Stateless
@Local(IBaseApplicationService.class)
public class BaseApplicationServiceImpl<T extends Serializable> implements IBaseApplicationService<T> {
	
	private Logger logger=LoggerFactory.getLogger(BaseApplicationServiceImpl.class);
	
	@PersistenceContext(unitName = "FormsDS")
	EntityManager entityManager;

	@EJB(beanName = "DozerMan")
	DozerMan dozerMan;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void create(T t) throws ApplicationException {
		try{
			entityManager.merge(t);
		}catch(Exception exception){
			logger.error("create(T)", exception); 
			throw new ApplicationException(FormsConstants.SERVER_ERROR, exception);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public T find(Integer id, Class entityClass) throws RecordNotFoundException {
		return (T)entityManager.find(entityClass,id);
	}

	public T update(T t) throws ApplicationException {
		T updatedObject=null;
		try{
			updatedObject = entityManager.merge(t);
		}catch (Exception exception){
			logger.error("update throwing exception", exception); 
			throw new ApplicationException(FormsConstants.SERVER_ERROR,exception);
		}
        return updatedObject;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void delete(Integer id, Class type) throws ApplicationException {
		try{
			T existingObject=(T) entityManager.find(type, id);
			entityManager.remove(existingObject);
		}catch(Exception e){
			logger.error("delete() method, thrwoing exception",e);
			throw new ApplicationException(FormsConstants.SERVER_ERROR,e);
		}
	}

	public List<T> findListByNamedQueryWithoutParam(String queryName) throws ApplicationException {
		return findListByNamedQueryWithoutParam(queryName,0);
	}

	@SuppressWarnings("unchecked")
	public T findSingleByNamedQueryWithoutParam(String queryName) throws RecordNotFoundException {
		T queryResult=null;
		try{
			Query query=entityManager.createNamedQuery(queryName);
			queryResult=(T) query.getSingleResult();
		}catch(Exception e){
			logger.error("findSingleByNamedQueryWithoutParam() throwing exception",e);
			throw new RecordNotFoundException(FormsConstants.SERVER_ERROR,e);
		}
		return queryResult;
	}

	@SuppressWarnings("unchecked")
	public List<T> findListByNamedQueryWithoutParam(String queryName, int resultLimit) throws ApplicationException {
		List<T> queryResult=null;
		try{
			Query query=entityManager.createNamedQuery(queryName);
			if (resultLimit>0) {
				query.setMaxResults(resultLimit);
			}
			queryResult=query.getResultList();
		}catch(Exception e){
			logger.error("findListByNamedQueryWithoutParam() throwing exception",e);
			throw new ApplicationException(FormsConstants.SERVER_ERROR,e);
		}
		return queryResult;
	}

	public List<T> findListByNamedQueryWithParam(String queryName,
			Map<String, Object> parameters) throws ApplicationException {
		return findListByNamedQueryWithParmAndLimit(queryName,parameters,0);
	}

	@SuppressWarnings("unchecked")
	public T findSingleByNamedQueryWithParam(String queryName,
			Map<String, Object> parameters) throws RecordNotFoundException,
			ApplicationException {
		T t = null;
		try {
			Set<Map.Entry<String, Object>> rawParameters = null;
			if (parameters != null) {
				rawParameters = parameters.entrySet();
			}
			Query query = entityManager.createNamedQuery(queryName);
			for (Map.Entry<String, Object> entry : rawParameters) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
			t = (T) query.getSingleResult();
		} catch (Exception exception) {
			throw new ApplicationException(FormsConstants.SERVER_ERROR, exception);
		}
		return t;
	}

	public List<T> findListByNamedQueryWithParmAndLimit(String queryName,
			Map<String, Object> parameters, int resultLimit) throws ApplicationException {
		List list = null;
		Set<Map.Entry<String, Object>> rawParameters = null;
		if (parameters != null) {
			rawParameters = parameters.entrySet();
		}
		Query query = entityManager.createNamedQuery(queryName);
		if(rawParameters != null){
			for (Map.Entry<String, Object> entry : rawParameters) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		if (resultLimit > 0) {
			query.setMaxResults(resultLimit);
		}
		try {
			list = (List<T>) query.getResultList();
		}catch (Exception exception){
			logger.error("findListByNamedQueryWithParmAndLimit() -error is {}", exception.getMessage());
			throw new ApplicationException(FormsConstants.SERVER_ERROR, exception);
		}
		return list;
	}
	public T getEntityBySid(Class clazz, String sid)
			throws RecordNotFoundException, ApplicationException {
		return findEntityBySid(clazz,sid);
	}

	public Integer getIdBySid(Class entityName, String sid)
			throws ApplicationException {
		Integer id = null;
		try{
			Query query =  entityManager.createQuery("select p.id from " + entityName.getSimpleName() + " p where hex(p.sid) = '" + sid + "'");
			id = (Integer) query.getSingleResult();
		}catch(Exception exception){
			logger.error("getIdBySid()", exception);
			throw new ApplicationException(FormsConstants.SERVER_ERROR,exception);
		}
		return id;
	}

	public String getSidById(Class entityName, Integer id)
			throws RecordNotFoundException, ApplicationException {
		String sid = null;
		try{
			Query query =  entityManager.createQuery("select hex(p.sid) from " + entityName.getSimpleName() + " p where p.id=:id");
			query.setParameter("id", id);
			sid = (String) query.getSingleResult();
		}catch(Exception exception){
			logger.error("getSidById()", exception);
			throw new ApplicationException(FormsConstants.SERVER_ERROR,exception);
		}
		return sid;
	}

	public T findEntityById(Class entityName, Integer id)
			throws RecordNotFoundException, ApplicationException {
		T queryResult=null;
		try{
			Query query = entityManager.createQuery("select e from "+ entityName.getSimpleName() + " e where e.id = :id");
			query.setParameter("id", id);
			queryResult = (T) query.getSingleResult();
		}catch (Exception exception) {
			logger.error("findEntityById()", exception); 
			throw new ApplicationException(FormsConstants.SERVER_ERROR, exception);
		}
		return queryResult;
	}

	public T findEntityBySid(Class clazz, String sid)
			throws RecordNotFoundException {
		T queryResult=null;
		try{
			Query query = (Query) getEntityManager().createQuery("from "+clazz.getSimpleName()+" t where hex(t.sid) = :sid");
			query.setParameter("sid", sid);
			queryResult= (T) query.getSingleResult();
		}catch (Exception exception){
			logger.error("getEntityBySid() ", exception);
			throw new RecordNotFoundException(FormsConstants.SERVER_ERROR, exception);
		}
		return queryResult;
	}
	public T findToObjectBySid(@SuppressWarnings("rawtypes") Class clazz,String sid,Class convertedClass) throws ApplicationException{
		T queryResult=null;
		try{
			T resultT=getEntityBySid(clazz, sid);
			queryResult=(T) dozerMan.getMapper().map(resultT, convertedClass);
		}catch(Exception e){
			logger.error("findToObjectBySid()",e);
			throw new ApplicationException(FormsConstants.SERVER_ERROR,e);
		}
		return queryResult;
	}
	public <T, U> U getToByNamedQuery(Class entityClass, Class<U> dtoClass,
			String namedQuery, Map<String, Object> parameters, String mapId)
			throws RecordNotFoundException, ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	public <T, U> U getToByNamedQuery(Class entityClass, Class<U> dtoClass,
			String namedQuery, Map<String, Object> parameters)
			throws RecordNotFoundException, ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}
	

	public List<T> findListByNamedNativeQueryWithParam(String queryName,
			Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<T> findListByNamedNativeQueryWithParamAndLimit(
			String queryName, Map<String, Object> parameters, int resultLimit) {
		// TODO Auto-generated method stub
		return null;
	}
  
	
}
