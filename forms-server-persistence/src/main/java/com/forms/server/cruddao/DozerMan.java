package com.forms.server.cruddao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.dozer.DozerBeanMapper;

import com.forms.server.common.FormsConstants;
import com.forms.server.exception.ApplicationException;
/**
 * 
 * @author vikash
 *
 */
@Startup
@Singleton
public class DozerMan {
    private DozerBeanMapper mapper = null;

    public DozerBeanMapper getMapper() {
        return mapper;
    }

    @PostConstruct
    void init() {
        mapper = new DozerBeanMapper();

        List mappingFiles = new ArrayList();
        mappingFiles.add("dozerxmls/dto_entity_mappings.xml");
        mapper.setMappingFiles(mappingFiles);
    }
    public <T, U> List<T> convertList(List<U> srcObjects, Class<T> destnationObjClass) throws ApplicationException {
		return convertList(srcObjects,destnationObjClass,null);
	}
    public <T, U> List<T> convertList(List<U> srcObjects, Class<T> destnationObjClass,String mapId)
			throws ApplicationException {
		List<T> list = null;
		try {
			if (srcObjects != null) {
				list = new ArrayList<T>();
				if (mapId == null) {
					for (U srcObject : srcObjects) {
						list.add(convert(srcObject, destnationObjClass));
					}
				} else {
					for (U srcObject : srcObjects) {
						list.add(convert(srcObject, destnationObjClass, mapId));
					}
				}
			}
		}catch (Exception e) {
			throw new ApplicationException(FormsConstants.DOZER_ERROR,e);
		}
		return list;
	}
    
    public <T> T convert(Object srcObject, Class<T> destnationObj) throws ApplicationException{
		try{
		     return (T) mapper.map(srcObject, destnationObj);
		}catch(Exception exception){
			exception.printStackTrace();
			throw new ApplicationException(FormsConstants.DOZER_ERROR, exception);
		}
	}
	public <T> T convert(Object srcObject, Class<T> destnationObj,String mapId) throws ApplicationException{
		try{
			return (T) mapper.map(srcObject, destnationObj, mapId);
		}catch(Exception exception){
			throw new ApplicationException(FormsConstants.DOZER_ERROR, exception);
		}
	}
}
