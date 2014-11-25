package com.forms.server.cruddao;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.forms.server.cruddao.api.IApplicationService;
import com.forms.server.exception.ApplicationException;
import com.forms.server.exception.persistence.RecordNotFoundException;

@Stateless
@Local(IApplicationService.class)
public class ApplicationService implements IApplicationService {
	
	private Logger logger=LoggerFactory.getLogger(ApplicationService.class);
	
	@PersistenceContext(unitName = "FormsDS")
	EntityManager em;

	@EJB(beanName = "DozerMan")
	DozerMan dozerMan;
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public  <T> T create(T t) {
		this.em.persist(t);
		this.em.flush();
		this.em.refresh(t);
		return t;
	}

	public <T> T find(Object id, Class<T> type) throws RecordNotFoundException {
		try{
		return (T) this.em.find(type, id);
		}catch(Exception e){
			throw new RecordNotFoundException(e.getMessage(),e);
		}
	}

	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public <T> T update(T t) {
		return (T) this.em.merge(t);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public <T> void delete(Object id, Class<T> type) {
		Object ref = this.em.getReference(type, id);
		this.em.remove(ref);
	}

	@Override
	public List<Object> findByNamedQuery(String queryName) {
		return this.em.createNamedQuery(queryName).getResultList();
	}

	@Override
	public Object findSingleByNamedQuery(String queryName) {
		return this.em.createNamedQuery(queryName).getSingleResult();
	}

	@Override
	public List<Object> findByNamedQuery(String queryName, int resultLimit) {
		return this.em.createNamedQuery(queryName).setMaxResults(resultLimit).getResultList();
	}

	@Override
	public List findByNamedQuery(String queryName,Map<String, Object> parameters) {
		return findByNamedQuery(queryName, parameters, 0);
	}

	@Override
	public List findByNamedNativeQuery(String queryName,Map<String, Object> parameters) {
		return findByNamedNativeQuery(queryName, parameters, 0);
	}

	@Override
	public Object findSingleByNamedQuery(String queryName,Map<String, Object> parameters) throws ApplicationException {
		Set<Map.Entry<String, Object>> rawParameters = parameters.entrySet();
		Query query = this.em.createNamedQuery(queryName);

		for (Map.Entry<String, Object> entry : rawParameters) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

		try {
			return query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		} catch (Exception e){
			throw new ApplicationException("",e);
		}
	}

	@Override
	public List<Object> findByNamedQuery(String queryName,Map<String, Object> parameters, int resultLimit) {
		Set<Map.Entry<String, Object>> rawParameters = parameters.entrySet();
		Query query = this.em.createNamedQuery(queryName);
		if (resultLimit > 0)
			query.setMaxResults(resultLimit);

		for (Map.Entry<String, Object> entry : rawParameters) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

		return query.getResultList();
	}

	@Override
	public List findByNamedNativeQuery(String queryName,Map<String, Object> parameters, int resultLimit) {
		Set<Map.Entry<String, Object>> rawParameters = parameters.entrySet();
		Query query = this.em.createNamedQuery(queryName);
		if (resultLimit > 0)
			query.setMaxResults(resultLimit);

		for (Map.Entry<String, Object> entry : rawParameters) {
			try {
				int pos = Integer.valueOf(entry.getKey());
				query.setParameter(pos, entry.getValue());
			} catch (NumberFormatException ex) {

			}
		}

		return query.getResultList();
	}

		
	@Override
	public <T> T getEntityBySid(Class<T> clazz, String sid) throws RecordNotFoundException,ApplicationException {
		try{
			Query query = (Query) em.createQuery("from "+clazz.getSimpleName()+" t where hex(t.sid) = :sid");
			query.setParameter("sid", sid);
			return (T) query.getSingleResult();
		}catch (NoResultException e) {
			throw new RecordNotFoundException("Entity Not Found", e);
		} catch (Exception e){
			throw new ApplicationException("",e);
		}
	}

	@Override
	public <T> Integer getIdBySid(Class<T> clazz, String sid)throws RecordNotFoundException,ApplicationException  {
		Query query=null;
		Integer id = null;
		try{
			query = (Query) em.createQuery("select t.id from "+clazz.getSimpleName()+" t where hex(t.sid) = :sid");
			query.setParameter("sid", sid);
			id = (Integer) query.getSingleResult();
			return id;
		}catch (NoResultException e) {
			throw new RecordNotFoundException("Entity Not Found", e);
		} catch (Exception e){
			throw new ApplicationException("",e);
		}
	}

	@Override
	public <T> String getSidById(Class<T> clazz, Integer id) throws RecordNotFoundException,ApplicationException {
		Query query=null;
		String sid = null;
		try{
			query = (Query) em.createQuery("select hex(t.sid) from "+clazz.getSimpleName()+" t where t.id = :id");
			query.setParameter("id", id);
			sid = (String) query.getSingleResult();
		}catch (NoResultException e){
			throw new RecordNotFoundException("No Entity found for ID : \t".concat(String.valueOf(id)),e);
		}catch (Exception e){
			throw new ApplicationException("",e);
		}
		return sid;
	}
	

	@Override
	public <T> T findEntityBySid(Class<T> clazz, String sid) throws RecordNotFoundException {
		Query query=null;
		try{
			query = (Query) em.createQuery(" from "+clazz.getSimpleName()+" t where hex(t.sid) = :sid");
			query.setParameter("sid", sid);
			return (T) query.getSingleResult();
		}catch (NoResultException e){
			throw new RecordNotFoundException("No Entity found for ID : \t".concat(String.valueOf(id)),e);
		}catch (Exception e){
			throw new ApplicationException("",e);
		}
	}


	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	@Override
	public <T> void persist(T t) {
		this.em.persist(t);
	}
	
	@Override
	public <T> T findEntityById(Class entityName, Integer id)throws RecordNotFoundException{
		logger.trace("findEntityById(Class, int) - start"); 
		try{
			Query query = em.createQuery("select e from "+ entityName.getSimpleName() + " e where e.id = :id");
			query.setParameter("id", id);
			T returnT = (T) query.getSingleResult();
			logger.trace("findEntityById(Class, int) - end"); 
			return returnT;
		}catch(NoResultException noResultException){
			logger.error("findEntityById(Class, int)-{}", noResultException.getMessage()); 
			throw new RecordNotFoundException(ApplicationConstants.ES_AM_PR_R_004);
		}catch (Exception exception) {
			logger.error("findEntityById(Class, int)-{}", exception.getMessage()); 
			throw new RecordNotFoundException(ApplicationConstants.ES_AM_PR_R_004);
		}
	}
	
	@Override
	public <T, U> U getToByNamedQuery(Class<T> entityClass, Class<U> dtoClass,
			String namedQuery, Map<String, Object> parameters, String mapId)throws ApplicationException {
		logger.trace("getToByNamedQuery(Class<T>, Class<U>, String, Map<String, Object>) - Start");
		U to = null;
		T entity = (T) findSingleByNamedQuery(namedQuery, parameters);
		if(entity != null){
			to = dozerMan.getMapper().map(entity, dtoClass, mapId);
		}
		logger.trace("getToByNamedQuery(Class<T>, Class<U>, String, Map<String, Object>) - End");
		return to;
	}
	
	@Override
	public <T, U> U getToByNamedQuery(Class<T> entityClass, Class<U> dtoClass,
			String namedQuery, Map<String, Object> parameters)
			throws ApplicationException {
		logger.trace("getToByNamedQuery(Class<T>, Class<U>, String) - Start");
		U to = null;
		T entity = (T) findSingleByNamedQuery(namedQuery, parameters);
		if (entity != null) {
			to = dozerMan.getMapper().map(entity, dtoClass);
		}
		logger.trace("getToByNamedQuery(Class<T>, Class<U>, String) - End");
		return to;
	}

}
