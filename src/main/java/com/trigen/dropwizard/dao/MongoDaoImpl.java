/**
 * File Name: 		MongoDaoImpl.java
 * Version:		0.1
 * Module:		BlogService-Dropwizard
 * Developer:		srinath
 * Creation Date:	9 May 2015 17:23:52
 * Description:
 */
package com.trigen.dropwizard.dao;

import static com.mongodb.client.model.Filters.eq;

import java.util.List;

import org.bson.Document;
import org.hibernate.Query;

import com.mongodb.client.MongoCollection;
import com.trigen.dropwizard.model.Post;

/**
 * @author srinath
 *
 */
public class MongoDaoImpl {

    private MongoCollection<Document> postCollection;

    public MongoDaoImpl(MongoCollection<Document> postCollection) {
	this.postCollection = postCollection;
    }

    public String findByPermLink(String permaLink) throws Exception{

	Document post = postCollection.find(eq("permalink", permaLink)).first();

	String postString = "{}";
	if (post != null) {
	    try {
		postString = post.toJson();
	    } catch (Exception e) {
		throw new Exception("Conversion from document o json format failed");
	    }
	}
	return postString;
    }

    public void createBlog(String post) {
	Document postObject = Document.parse(post);
	postCollection.insertOne(postObject);
    }

}
