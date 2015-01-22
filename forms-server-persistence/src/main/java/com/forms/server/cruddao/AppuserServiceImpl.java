package com.forms.server.cruddao;

import com.forms.server.common.CommonMethods;
import com.forms.server.common.QueryParameter;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import com.forms.server.cruddao.api.IAppuserService;
import com.forms.server.cruddao.api.IBaseApplicationService;
import com.forms.server.dto.AppuserTO;
import com.forms.server.dto.SecurityQuestionsTO;
import com.forms.server.entities.Appuser;
import com.forms.server.entities.SecurityQuestions;
import com.forms.server.exception.ActivationKeyExpired;
import com.forms.server.exception.ApplicationException;
import com.forms.server.exception.InvalidActivationKey;
import com.forms.server.exception.persistence.RecordNotFoundException;
import java.util.ArrayList;
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
            Appuser appuser=applicationServiceImpl.findEntityById(Appuser.class, id);
            if(appuser!=null)
                    return dozerMan.getMapper().map(appuser, AppuserTO.class);
            else
                    throw new RecordNotFoundException("No record returned by Persistence",null);
    }

    public AppuserTO findAppuserByUsername(String username)throws RecordNotFoundException, ApplicationException {
            // TODO Auto-generated method stub
            Appuser appuser=(Appuser) applicationServiceImpl.findSingleByNamedQuery(Appuser.FIND_BY_NAME, QueryParameter.with("param", username).parameters());
            if(appuser != null)
                return dozerMan.getMapper().map(appuser, AppuserTO.class);
            else
                throw new RecordNotFoundException("No record returned by Persistence",null);
    }

    public AppuserTO findAppuserBySid(String sid)
                    throws RecordNotFoundException, ApplicationException {
            Appuser appuser=(Appuser) applicationServiceImpl.findSingleByNamedQuery(Appuser.FIND_BY_SID, QueryParameter.with("param", sid).parameters());
            if(appuser != null)
                return dozerMan.getMapper().map(appuser, AppuserTO.class);
            else
                throw new RecordNotFoundException("No record returned by Persistence",null);
    }

    @Override
    public boolean authenticateUser(String username,String password) throws ApplicationException{
        Appuser appuser=null;
        try {
            appuser=(Appuser) applicationServiceImpl.findSingleByNamedQuery(Appuser.FIND_BY_NAME, QueryParameter.with("param", username).parameters());
        } catch (ApplicationException ex) {
            throw new ApplicationException("Error while searching Username", "Error while searching Username", ex);
        }
        if(appuser==null)return false;
        return appuser.getPassword().equals(password);

    }

    @Override
    public boolean doesPwdExists(String password)throws RecordNotFoundException, ApplicationException{
         Appuser appuser=(Appuser) applicationServiceImpl.findSingleByNamedQuery(Appuser.FIND_BY_PWD, QueryParameter.with("param", password).parameters());
            if(appuser != null)
                return true;
            else
                return false;
    }

    @Override
    public List<SecurityQuestionsTO> getAllSecurityQuestions() throws RecordNotFoundException {
        List<SecurityQuestions> securityQuestions=null;
        securityQuestions=applicationServiceImpl.findByNamedQuery(SecurityQuestions.FIND_ALL);
        if(securityQuestions==null || securityQuestions.size()<=0)throw new RecordNotFoundException();
        List<SecurityQuestionsTO> securityQuestionsTOs=new ArrayList<SecurityQuestionsTO>();
        for(SecurityQuestions question:securityQuestions){
            SecurityQuestionsTO questionTO=dozerMan.getMapper().map(question, SecurityQuestionsTO.class);
            securityQuestionsTOs.add(questionTO);
        }
        return securityQuestionsTOs;
    }

    public boolean verifyActivationLink(String sid, String activationKey) throws InvalidActivationKey, ActivationKeyExpired, ApplicationException {
        Appuser appuser=null;
        logger.trace("Verifying Activation Link for {},{}",sid,activationKey);
        try{
            appuser=(Appuser) applicationServiceImpl.findSingleByNamedQuery(Appuser.VERIFICATION_QUERY, QueryParameter.with("param",activationKey ).and("param1", sid).parameters());   
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

    public boolean userSignup(AppuserTO appuserTO) throws IllegalArgumentException {
        logger.trace("Starting user sign up with : {}",CommonMethods.);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
}
