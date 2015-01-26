package com.forms.server.cruddao.api;

import com.forms.server.dto.AppuserTO;
import com.forms.server.dto.DistrictTO;
import com.forms.server.dto.DistrictTO.StateCode;
import com.forms.server.dto.LanguageTO;
import com.forms.server.dto.SecurityQuestionsTO;
import com.forms.server.dto.StateTO;
import com.forms.server.exception.ActivationKeyExpired;
import com.forms.server.exception.ApplicationException;
import com.forms.server.exception.InvalidActivationKey;
import com.forms.server.exception.persistence.RecordNotFoundException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
	public Boolean findAppuserByUsername(String username)throws RecordNotFoundException,ApplicationException;
	
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
         * Get all security questions
         * @return List of all Security Questions in the DB
         * @throws RecordNotFoundException
         * @throws ApplicationException 
         */
        public List<SecurityQuestionsTO> getAllSecurityQuestions() throws RecordNotFoundException, ApplicationException; 
        
        public boolean verifyActivationLink(String appuserSid,String activationKey) throws InvalidActivationKey, ActivationKeyExpired, ApplicationException;
        /**
         * 
         * @param appuserTO
         * @return
         * @throws IllegalArgumentException
         * @throws ApplicationException 
         */
        public boolean userSignup(AppuserTO appuserTO,HttpServletRequest request)throws IllegalArgumentException, ApplicationException;
        /**
         * API to get list of district by state code
         * @param stateCode
         * @return List<DistrictTO>
         * @throws ApplicationException
         */
		public List<DistrictTO> listOfDistrictByStateCode(StateCode stateCode) throws ApplicationException;
        /**
         * API to get all the list of state
         * @return List<StateTO>
         * @throws ApplicationException
         */
		public List<StateTO> listOfState() throws ApplicationException;
        /**
         * API to get all the list of language
         * @return List<LanguageTO>
         * @throws ApplicationException
         */
		public List<LanguageTO> listOfLanguage() throws ApplicationException;
}
