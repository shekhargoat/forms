package com.forms.server.service;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.forms.server.dto.LoginForm;


@Path("/test")
public class FormLoginService {

	@Path("/v1")
	@GET
	public Response getInitialJson(){
		LoginForm form=new LoginForm();
		form.setPassword("x");
		form.setUsername("x");
		return Response.status(200).build();
	}
}
