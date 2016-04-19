package com.java.owcs.customrest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("pop")
public class OwcsRestService {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getExample(){
		return "Teh works";
	}

}
