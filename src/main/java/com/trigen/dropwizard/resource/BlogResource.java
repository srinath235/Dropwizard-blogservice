/**
 * File Name: 		BlogResource.java
 * Version:		0.1
 * Module:		sampledropwizard
 * Developer:		srinath
 * Creation Date:	8 May 2015 15:46:31
 * Description:
 */
package com.trigen.dropwizard.resource;

import io.dropwizard.hibernate.UnitOfWork;

import java.io.IOException;

import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.trigen.dropwizard.service.delegate.ServiceDelegate;

/**
 * @author srinath
 *
 */
@Path("/blog")
public class BlogResource {

    private ServiceDelegate serviceDelegate;

    public BlogResource(ServiceDelegate serviceDelegate) {
	this.serviceDelegate = serviceDelegate;
    }

    @Path("/publishBlog")
    @POST
    @Timed
    @UnitOfWork
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response publishBlog(@QueryParam("format") String format,
	    @Valid String post) throws JsonParseException,
	    JsonMappingException, IOException {
	Response response = Response.noContent().build();
	if ("JSON".equalsIgnoreCase(format)) {
	    serviceDelegate.createBlogOnMongoDb(post);
	} else if ("XML".equalsIgnoreCase(format)) {
	    serviceDelegate.createBlogOnMysqlDb(post);
	} else {
	    response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
	}
	return response;
    }

    @Path("/getPostByPermaLink")
    @GET
    @Timed
    @UnitOfWork
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public String getPostByPermaLink(@QueryParam("format") String format,
	    @QueryParam("permalink") String permalink) {

	String postString = "Invalid format";
	if ("JSON".equalsIgnoreCase(format)) {
	    try {
		postString = serviceDelegate.findBlogByPermLinkFromMongoDb(permalink);
	    } catch (Exception e) {
		postString = "{'error':'Failed due to " + e.getMessage() + "'}";
	    }
	} else if ("XML".equalsIgnoreCase(format)) {
	    try {
		postString = serviceDelegate.findBlogByPermLinkFromMysqlDb(permalink);
	    } catch (Exception e) {
		postString = "<error>Failed due to " + e.getMessage()
			+ "</error>";
	    }
	} else {
	    postString = "Unknown format " + format;
	}
	return postString;
    }
    
    @Path("/deletePostByPermaLink")
    @DELETE
    @Timed
    @UnitOfWork
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response deletePostByPermaLink(@QueryParam("format") String format,
	    @QueryParam("permalink") String permalink) {

	Response response = Response.noContent().build();
	if ("JSON".equalsIgnoreCase(format)) {
	    serviceDelegate.deleteBlogOnMongoDb(permalink);
	} else if ("XML".equalsIgnoreCase(format)) {
	    serviceDelegate.deleteBlogOnMysqlDb(permalink);
	} else {
	    response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
	}
	return response;
    }

}
