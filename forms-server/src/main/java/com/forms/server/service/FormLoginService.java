package com.forms.server.service;


import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.forms.server.cruddao.api.IAppuserService;
import com.forms.server.dto.AppuserTO;
import com.forms.server.exception.ApplicationException;
import com.forms.server.exception.persistence.RecordNotFoundException;


@Path("/v1")
public class FormLoginService {
	
	@EJB
	IAppuserService appuserService;

	@Path("/appuser/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInitialJson(@PathParam("id") Integer id){
		try {
			AppuserTO appuserTO=appuserService.findAppuserById(id);
//			return buildSuccessResponse(appuserTO);
			return Response.status(200).entity(appuserTO).build();
		} catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(200).build();
	}
        
        @Path("/validate/signup/pwd/{pwd}")
        @GET
	public Response verifyPassword(@PathParam("pwd")String password){
            try {
                boolean flg=appuserService.doesPwdExists(password);
                if(flg)return Response.status(Response.Status.PRECONDITION_FAILED).build();
                else
                    return Response.status(Response.Status.OK).build();
            } catch (ApplicationException ex) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        }
        
        @Path("/validate/signup/username/{username}")
        @GET
        public Response verifyUsername(@PathParam("username")String username){
            AppuserTO appuserTO = null;
            try {
                appuserTO = appuserService.findAppuserByUsername(username);
            } catch (ApplicationException ex) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
            if(appuserTO==null)return Response.status(Response.Status.OK).build();
            else
                return Response.status(Response.Status.PRECONDITION_FAILED).build();
        }
        
        
        @Path("/validate/security/questions")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getSecurityQuestions(){
            try {
                return Response.status(Response.Status.OK).entity(appuserService.getAllSecurityQuestions()).build();
            } catch (RecordNotFoundException ex) {
                return Response.status(Response.Status.NO_CONTENT).build();
            } catch(Exception e){
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        }
	
        
	private <T> Response buildSuccessResponse(T value){
		if(value != null)
			return Response.status(200).entity(value).build();
		else
			return Response.status(200).build();
	}
}
