package com.forms.server.cruddao;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import com.forms.server.cruddao.api.IAppuserService;
import com.forms.server.cruddao.api.IBaseApplicationService;
import com.forms.server.dto.AppuserTO;
import com.forms.server.entities.Appuser;
import com.forms.server.exception.ApplicationException;
import com.forms.server.exception.persistence.RecordNotFoundException;

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
		return null;
	}

	public AppuserTO findAppuserBySid(String sid)
			throws RecordNotFoundException, ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

}
