package com.forms.server.cruddao;

import com.forms.server.common.CommonMethods;
import com.forms.server.common.FormsConstants;
import com.forms.server.common.QueryParameter;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import com.forms.server.cruddao.api.IAppuserService;
import com.forms.server.cruddao.api.IBaseApplicationService;
import com.forms.server.dto.AppuserTO;
import com.forms.server.dto.DistrictTO;
import com.forms.server.dto.DistrictTO.StateCode;
import com.forms.server.dto.LanguageTO;
import com.forms.server.dto.SecurityQuestionsTO;
import com.forms.server.dto.StateTO;
import com.forms.server.entities.Appuser;
import com.forms.server.entities.AppuserSecurityAnswer;
import com.forms.server.entities.AppuserSecurityAnswerPK;
import com.forms.server.entities.District;
import com.forms.server.entities.Language;
import com.forms.server.entities.SecurityQuestions;
import com.forms.server.entities.State;
import com.forms.server.exception.ActivationKeyExpired;
import com.forms.server.exception.ApplicationException;
import com.forms.server.exception.InvalidActivationKey;
import com.forms.server.exception.InvalidInputException;
import com.forms.server.exception.persistence.RecordNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Stateless
@Local(IAppuserService.class)
public class AppuserServiceImpl implements IAppuserService {

    @EJB(beanName = "DozerMan")
    DozerMan dozerMan;

    @EJB
    IBaseApplicationService applicationServiceImpl;
    
    Logger logger=LoggerFactory.getLogger(AppuserServiceImpl.class);

    public AppuserTO findAppuserById(Integer id)throws RecordNotFoundException, ApplicationException {
            // TODO Auto-generated method stub
            Appuser appuser=(Appuser) applicationServiceImpl.findEntityById(Appuser.class, id);
            if(appuser!=null)
                    return dozerMan.getMapper().map(appuser, AppuserTO.class);
            else
                    throw new RecordNotFoundException("No record returned by Persistence",null);
    }

    public Boolean findAppuserByUsername(String username)throws RecordNotFoundException, ApplicationException {
    	Boolean isAvailable=false;
    	try{
    		Appuser appuser=(Appuser) applicationServiceImpl.findSingleByNamedQueryWithParam(Appuser.FIND_BY_NAME, QueryParameter.with("param", username).parameters());
    		if(appuser!=null)isAvailable=true;
    	}catch(NoResultException noResultException){
    		logger.error("User not available");
    		isAvailable=true;
    	}catch (Exception e) {
			logger.error("While validating the user, throwing exception",e);
			throw new ApplicationException(FormsConstants.FORMS_PS_006,e);
		}
            return isAvailable;
    }

    public AppuserTO findAppuserBySid(String sid)
                    throws RecordNotFoundException, ApplicationException {
            Appuser appuser=(Appuser) applicationServiceImpl.findSingleByNamedQueryWithParam(Appuser.FIND_BY_SID, QueryParameter.with("param", sid).parameters());
            if(appuser != null)
                return dozerMan.getMapper().map(appuser, AppuserTO.class);
            else
                throw new RecordNotFoundException("No record returned by Persistence",null);
    }

    @Override
    public boolean authenticateUser(String username,String password) throws ApplicationException{
        Appuser appuser=null;
        try {
            appuser=(Appuser) applicationServiceImpl.findSingleByNamedQueryWithParam(Appuser.FIND_BY_NAME, QueryParameter.with("param", username).parameters());
        } catch (ApplicationException ex) {
            throw new ApplicationException("Error while searching Username", "Error while searching Username", ex);
        }
        if(appuser==null)return false;
        return appuser.getPassword().equals(password);

    }


    @Override
    public List<SecurityQuestionsTO> getAllSecurityQuestions() throws ApplicationException {
    	List<SecurityQuestionsTO> securityQuestionsTOs=null;
    	try{
        List<SecurityQuestions> securityQuestions=applicationServiceImpl.findListByNamedQueryWithoutParam(SecurityQuestions.FIND_ALL);
        securityQuestionsTOs=dozerMan.convertList(securityQuestions, SecurityQuestionsTO.class);
    	}catch(Exception e){
    		logger.error("Whiel getting the security question from db,throwing exception",e);
			throw new ApplicationException("Error while getting the security question list",FormsConstants.FORMS_PS_007,e);
    	}
        return securityQuestionsTOs;
    }

    public boolean verifyActivationLink(String sid, String activationKey) throws InvalidActivationKey, ActivationKeyExpired, ApplicationException {
        Appuser appuser=null;
        logger.trace("Verifying Activation Link for {},{}",sid,activationKey);
        try{
            appuser=(Appuser) applicationServiceImpl.findSingleByNamedQueryWithParam(Appuser.VERIFICATION_QUERY, QueryParameter.with("param",activationKey ).and("param1", sid).parameters());   
            logger.debug("Appuser found for activation key {} and sid {}",activationKey,sid);
        }catch(Exception e){
            throw new ApplicationException("Error While verifying activation link", "Error While verifying activation link", e);
        }
        if(appuser==null)throw new InvalidActivationKey();
        else{
            if(appuser.getIsEnabled())throw new ActivationKeyExpired();
            appuser.setIsEnabled(true);
            try{
                logger.debug("Activating user id {}",sid);
                applicationServiceImpl.update(appuser);
                return true;
            }catch(Exception e){
                logger.error("Error while activation user accountSid: {}",sid);
                throw new ApplicationException("Error while activating user account", "Error while activating user account", e);
            }
        }
    }

    public boolean userSignup(AppuserTO appuserTO,HttpServletRequest request) throws ApplicationException {
    	Boolean result=false;
    	try{
    		String generatedNumber=CommonMethods.generateRandomNumber();
    		String activationToken=CommonMethods.generateActivitionKey(generatedNumber,request);
    		Appuser appuser=dozerMan.convert(appuserTO, Appuser.class);
            appuser.setAccountCreatedOn(new Date());
            appuser.setAccountLastAccessed(new Date());
            appuser.setActivationKey(generatedNumber);
            appuser.setIsEnabled(false);
            appuser.setPassword(CommonMethods.encriptedPassword(appuserTO.getPassword()));
            appuser=(Appuser) applicationServiceImpl.update(appuser);
            if(appuserTO.getSecurityQuestionsTOs()!=null && appuserTO.getSecurityQuestionsTOs().size()!=0){
            	List<SecurityQuestionsTO> securityQuestionsTOs=appuserTO.getSecurityQuestionsTOs();
            	for (SecurityQuestionsTO securityQuestionsTO : securityQuestionsTOs) {
					AppuserSecurityAnswerPK appuserSecurityAnswerPK=new AppuserSecurityAnswerPK();
					appuserSecurityAnswerPK.setAppuserId(appuser.getId());
					appuserSecurityAnswerPK.setSecurityQuestionId(applicationServiceImpl.getIdBySid(SecurityQuestions.class,securityQuestionsTO.getSid()));
					AppuserSecurityAnswer appuserSecurityAnswer=new AppuserSecurityAnswer();
					appuserSecurityAnswer.setAnswer(securityQuestionsTO.getAnswer());
					appuserSecurityAnswer.setAnswerPK(appuserSecurityAnswerPK);
					applicationServiceImpl.create(appuserSecurityAnswer);
					appuserSecurityAnswerPK=null;
					appuserSecurityAnswer=null;
				}
            }
            result=true;
            CommonMethods.sendMail(appuserTO,activationToken,request);
    	}catch(Exception e){
    		throw new ApplicationException(FormsConstants.FORMS_PS_008,e);
    	}
         return result;
    }

	public List<DistrictTO> listOfDistrictByStateCode(StateCode stateCode)
			throws ApplicationException {
		List<DistrictTO> districtTOs=null;
		try{
		if(stateCode!=null){
			List<District> districts=applicationServiceImpl.findListByNamedQueryWithParam(District.FIND_BY_STATE_CODE,QueryParameter.with("stateCode", stateCode.name()).parameters());
			districtTOs=dozerMan.convertList(districts,DistrictTO.class);
		}else{
			throw new InvalidInputException("Input Can be empty",FormsConstants.FORMS_PS_002);
		}
		}catch(Exception e){
			logger.error("Whiel getting the district value from db,throwing exception",e);
			throw new ApplicationException("Error while getting the district list",FormsConstants.FORMS_PS_003,e);
		}
		return districtTOs;
	}

	public List<StateTO> listOfState() throws ApplicationException {
		List<StateTO> stateTOs=null;
		try{
			List<State> states=applicationServiceImpl.findListByNamedQueryWithoutParam(State.FIND_ALL_STATE);
			stateTOs=dozerMan.convertList(states,StateTO.class);
		}catch(Exception e){
			logger.error("Whiel getting the state value from db,throwing exception",e);
			throw new ApplicationException("Error while getting the state list",FormsConstants.FORMS_PS_004,e);
		}
		return stateTOs;
	}

	public List<LanguageTO> listOfLanguage() throws ApplicationException {
		List<LanguageTO> languageTOs=null;
		try{
			List<Language> language=applicationServiceImpl.findListByNamedQueryWithoutParam(Language.FIND_ALL_Language);
			languageTOs=dozerMan.convertList(language,LanguageTO.class);
		}catch(Exception e){
			logger.error("Whiel getting the language value from db,throwing exception",e);
			throw new ApplicationException("Error while getting the language list",FormsConstants.FORMS_PS_005,e);
		}
		return languageTOs;
	}
        
}
