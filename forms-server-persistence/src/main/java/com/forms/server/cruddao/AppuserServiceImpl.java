package com.forms.server.cruddao;

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
import com.forms.server.exception.ApplicationException;
import com.forms.server.exception.persistence.RecordNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Local(IAppuserService.class)
public class AppuserServiceImpl implements IAppuserService {

	@EJB(beanName = "DozerMan")
	DozerMan dozerMan;
	
	@EJB
	IBaseApplicationService applicationServiceImpl;

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

}
