package com.forms.server.service;


import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.forms.server.dto.LoginForm;


@Path("/test")
public class FormLoginService {

	@Produces(MediaType.APPLICATION_JSON)
	public Response getInitialJson(){
		LoginForm form=new LoginForm();
		form.setPassword("x");
		form.setUsername("x");
		return Response.status(200).entity(form).build();
	}
}
