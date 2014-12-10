package com.enigma.filter;

import javax.ws.rs.ext.Provider;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;



@Provider
public class CORSFilter implements ContainerResponseFilter {

	@Override
	public ContainerResponse filter(ContainerRequest arg0, ContainerResponse arg1) {
		arg1.getHttpHeaders().add("Access-Control-Allow-Origin", "*");
		arg1.getHttpHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
		arg1.getHttpHeaders().add("Access-Control-Allow-Credentials", "true");
		arg1.getHttpHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
		arg1.getHttpHeaders().add("Access-Control-Max-Age", "1209600");
		return arg1;
	}

}
