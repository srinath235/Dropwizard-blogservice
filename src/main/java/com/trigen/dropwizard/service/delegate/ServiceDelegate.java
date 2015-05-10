/**
 * File Name: 		ServiceDelegate.java
 * Version:		0.1
 * Module:		BlogService-Dropwizard
 * Developer:		srinath
 * Creation Date:	10 May 2015 00:46:33
 * Description:
 */
package com.trigen.dropwizard.service.delegate;

import static com.mongodb.client.model.Filters.eq;

import java.io.StringReader;

import javax.xml.bind.JAXB;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.trigen.dropwizard.dao.MySqlDaoImpl;
import com.trigen.dropwizard.model.Post;

/**
 * @author srinath
 *
 */
public class ServiceDelegate {

    private MySqlDaoImpl mySqlDaoImpl;

    private MongoCollection<Document> postCollection;

    public ServiceDelegate(MySqlDaoImpl mySqlDaoImpl,
	    MongoCollection<Document> postCollection) {
	this.mySqlDaoImpl = mySqlDaoImpl;
	this.postCollection = postCollection;
    }

    public void createBlogOnMongoDb(String post) {
	Document postObject = Document.parse(post);
	postCollection.insertOne(postObject);
    }

    public String findBlogByPermLinkFromMongoDb(String permaLink)
	    throws Exception {

	Document post = postCollection.find(eq("permalink", permaLink)).first();

	String postString = "{}";
	if (post != null) {
	    try {
		postString = post.toJson();
	    } catch (Exception e) {
		throw new Exception(
			"Conversion from document to json format failed");
	    }
	}
	return postString;
    }

    public void createBlogOnMysqlDb(String post) {
	Post postJaxbObject = JAXB
		.unmarshal(new StringReader(post), Post.class);
	mySqlDaoImpl.createBlog(postJaxbObject);
    }

    public String findBlogByPermLinkFromMysqlDb(String permaLink)
	    throws Exception {

	Post jaxbPostObject = mySqlDaoImpl.findByPermLink(permaLink);

	String postString = "<error>Record not found</error>";
	if (jaxbPostObject != null) {
	    JAXB.marshal(jaxbPostObject, postString);
	}
	return postString;
    }
    
    public void deleteBlogOnMongoDb(String permalink) {	
	postCollection.deleteOne(eq("permalink", permalink));
    }
    
    public void deleteBlogOnMysqlDb(String permlink) {	
	mySqlDaoImpl.deleteByPermLink(permlink);
    }

}
