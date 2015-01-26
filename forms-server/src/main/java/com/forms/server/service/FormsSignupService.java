package com.forms.server.service;


import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.forms.server.cruddao.api.IAppuserService;
import com.forms.server.dto.AppuserTO;
import com.forms.server.dto.DistrictTO;
import com.forms.server.dto.DistrictTO.StateCode;
import com.forms.server.dto.LanguageTO;
import com.forms.server.dto.SecurityQuestionsTO;
import com.forms.server.dto.StateTO;
import com.forms.server.exception.ActivationKeyExpired;
import com.forms.server.exception.ApplicationException;
import com.forms.server.exception.InvalidActivationKey;
import com.forms.server.exception.InvalidInputException;
import com.forms.ui.res.SignupRes;

/**
 * 
 * @author vikash
 *
 */
@Path("/v1/signup")
public class FormsSignupService {

    @EJB
    IAppuserService appuserService;
    
    private static Logger logger=LoggerFactory.getLogger(FormsSignupService.class);

    @Path("/validate/username/{username}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response verifyUsername(@PathParam("username")String username){
        Boolean isAvailable = null;
        try {
        	isAvailable = appuserService.findAppuserByUsername(username);
        } catch (ApplicationException ex) {
        	logger.error("While calling the listOfDistrictByStateCode(), throwing exception",ex);
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Response.Status.OK).entity(isAvailable).build();
    }

    @Path("/security/questions")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSecurityQuestions(){
    	List<SecurityQuestionsTO> questionsTOs=null;
        try {
        	questionsTOs=appuserService.getAllSecurityQuestions();
        } catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        if(questionsTOs==null){
        	return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.OK).entity(questionsTOs).build();
    }
    
    @Path("/activate/{activationKey}")
    @PUT
    public Response verifyActivationLink(@PathParam("activationKey")String activationKey){
        boolean flgActivate=false;
        try {
            flgActivate=appuserService.verifyActivationLink(null, activationKey);
            if(flgActivate)return Response.status(Response.Status.OK).build();
            else
                return Response.status(Response.Status.PRECONDITION_FAILED).build();
        } catch (InvalidActivationKey ex) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (ActivationKeyExpired ex) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        } catch (ApplicationException ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Path("/register/user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response userSignup(AppuserTO appuserTO,@Context HttpServletRequest request){
    	Boolean result=null;
    	try{
    		result=appuserService.userSignup(appuserTO,request);
    	}catch(ApplicationException e){
    		logger.error("while creating the user, throwing exception",e);
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    	}
    	return Response.status(Response.Status.OK).entity(result).build();
    }
    /**
     * API to retrieve state based upon state code.
     * @param stateCode(KAR,MAH)
     * @return List<DistrictTO>
     */
    @GET
    @Path("/listofdist/state/{stateCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listOfDistrictByStateCode(@PathParam("stateCode") StateCode stateCode){
    	List<DistrictTO> districtTOs=null;
    	try{
    		 logger.trace("calling list of district api by state code {}",stateCode);
    		 districtTOs=appuserService.listOfDistrictByStateCode(stateCode);
    	}catch(InvalidInputException inputException){
    		logger.error("Please enter valid input",inputException);
    		return Response.status(Response.Status.BAD_REQUEST).build();
    	}
    	catch(ApplicationException applicationException){
    		logger.error("While calling the listOfDistrictByStateCode(), throwing exception",applicationException);
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    	}
    	if (districtTOs==null) {
    		return Response.status(Response.Status.NO_CONTENT).build();
		}
    	return Response.status(Response.Status.OK).entity(districtTOs).build();
    }
    
    @GET
    @Path("/listofstate/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listOfState(){
    	List<StateTO> stateTOs=null;
    	try{
    		stateTOs=appuserService.listOfState();
    	}catch(ApplicationException applicationException){
    		logger.error("While calling the listOfState(), throwing exception",applicationException);
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    	}
    	if (stateTOs==null) {
    		return Response.status(Response.Status.NO_CONTENT).build();
		}
    	return Response.status(Response.Status.OK).entity(stateTOs).build();
    }
    @GET
    @Path("/listoflanguage/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listOfLanguage(){
    	List<LanguageTO> languageTO=null;
    	try{
    		languageTO=appuserService.listOfLanguage();
    	}catch(ApplicationException applicationException){
    		logger.error("While calling the listOfLanguage(), throwing exception",applicationException);
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    	}
    	if (languageTO==null) {
    		return Response.status(Response.Status.NO_CONTENT).build();
		}
    	return Response.status(Response.Status.OK).entity(languageTO).build();
    }
}
