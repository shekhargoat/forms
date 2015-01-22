/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forms.server.service;

import com.forms.server.cruddao.api.IAppuserService;
import com.forms.server.dto.AppuserTO;
import com.forms.server.exception.ActivationKeyExpired;
import com.forms.server.exception.ApplicationException;
import com.forms.server.exception.InvalidActivationKey;
import com.forms.server.exception.persistence.RecordNotFoundException;
import com.forms.ui.res.SignupRes;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author SSD
 */

@Path("/v1/signup")
public class FormsSignupService {

    @EJB
    IAppuserService appuserService;

    @Path("/validate/pwd/{pwd}")
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

    @Path("/validate/username/{username}")
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


    @Path("/security/questions")
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
    
    @Path("/activate/{appSid}/{activationKey}")
    @PUT
    public Response verifyActivationLink(@PathParam("appSid")String appuserSid,@PathParam("activationKey")String activationKey){
        boolean flgActivate=false;
        try {
            flgActivate=appuserService.verifyActivationLink(appuserSid, activationKey);
            if(flgActivate)return Response.status(Response.Status.OK).build();
            else
                return Response.status(Response.Status.PRECONDITION_FAILED).build();
        } catch (InvalidActivationKey ex) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (ActivationKeyExpired ex) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        } catch (ApplicationException ex) {
            Logger.getLogger(FormsSignupService.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Response userSignup(SignupRes signupRes){
        appuserService.
    }
    
}
