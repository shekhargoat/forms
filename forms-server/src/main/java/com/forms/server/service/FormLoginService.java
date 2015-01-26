package com.forms.server.service;


import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.forms.server.cruddao.api.IAppuserService;
import com.forms.server.dto.AppuserTO;
import com.forms.server.exception.ApplicationException;
import com.forms.server.exception.persistence.RecordNotFoundException;


@Path("/v1/login")
public class FormLoginService {
	
	@EJB
	IAppuserService appuserService;

	@Path("/appuser/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInitialJson(@PathParam("id") Integer id){
		try {
			AppuserTO appuserTO=appuserService.findAppuserById(id);
			return Response.status(200).entity(appuserTO).build();
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		return Response.status(200).build();
	}
        
        
        
        @Path("/authenticate")
        @GET
        public Response authenticateUser(@HeaderParam("username")String username,@HeaderParam("password")String password){
            boolean flgLogin = false;
            try {
                flgLogin = appuserService.authenticateUser(username, password);
            } catch (ApplicationException ex) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
            if(flgLogin)return Response.status(Response.Status.OK).build();
            else
                return Response.status(Response.Status.UNAUTHORIZED).build();
        }
	
        
	private <T> Response buildSuccessResponse(T value){
		if(value != null)
			return Response.status(200).entity(value).build();
		else
			return Response.status(200).build();
	}
}
