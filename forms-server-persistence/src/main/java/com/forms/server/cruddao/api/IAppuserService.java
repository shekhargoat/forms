package com.forms.server.cruddao.api;

import com.forms.server.dto.AppuserTO;
import com.forms.server.dto.SecurityQuestionsTO;
import com.forms.server.exception.ActivationKeyExpired;
import com.forms.server.exception.ApplicationException;
import com.forms.server.exception.InvalidActivationKey;
import com.forms.server.exception.persistence.RecordNotFoundException;
import java.util.List;

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
        
        /**
         * Authenticates the user used for login
         * 
         * @param username
         * @param password
         * @return
         * @throws ApplicationException
         */
        public boolean authenticateUser(String username,String password) throws ApplicationException;
        
        /**
         * Checks if the password exists
         * 
         * @param password
         * @return
         * @throws RecordNotFoundException
         * @throws ApplicationException
         */
        public boolean doesPwdExists(String password)throws RecordNotFoundException, ApplicationException;
        
        /**
         * Get all security questions
         * 
         * @return List of all Security Questions in the DB
         * @throws RecordNotFoundException
         */
        public List<SecurityQuestionsTO> getAllSecurityQuestions() throws RecordNotFoundException; 
        
        public boolean verifyActivationLink(String appuserSid,String activationKey) throws InvalidActivationKey, ActivationKeyExpired, ApplicationException;
        
        public boolean userSignup(AppuserTO appuserTO)throws IllegalArgumentException;
}
