package com.forms.server.cruddao;

import org.dozer.DozerBeanMapper;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Hoa Ho
 * Date: 5/2/13
 * Time: 6:11 PM
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
}
