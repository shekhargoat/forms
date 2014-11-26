package com.forms.server.cruddao.api;

import com.forms.server.dto.AppuserTO;
import com.forms.server.exception.ApplicationException;
import com.forms.server.exception.persistence.RecordNotFoundException;

// TODO: Auto-generated Javadoc
/**
 * The Interface IAppuserService.
 */
public interface IAppuserService {
	
	/**
	 * Find appuser by id.
	 *
	 * @param id the id
	 * @return the appuser to
	 * @throws RecordNotFoundException the record not found exception
	 * @throws ApplicationException the application exception
	 */
	public AppuserTO findAppuserById(Integer id)throws RecordNotFoundException,ApplicationException;
	
	/**
	 * Find appuser by username.
	 *
	 * @param username the username
	 * @return the appuser to
	 * @throws RecordNotFoundException the record not found exception
	 * @throws ApplicationException the application exception
	 */
	public AppuserTO findAppuserByUsername(String username)throws RecordNotFoundException,ApplicationException;
	
	/**
	 * Find appuser by sid.
	 *
	 * @param sid the sid
	 * @return the appuser to
	 * @throws RecordNotFoundException the record not found exception
	 * @throws ApplicationException the application exception
	 */
	public AppuserTO findAppuserBySid(String sid)throws RecordNotFoundException,ApplicationException;

}
