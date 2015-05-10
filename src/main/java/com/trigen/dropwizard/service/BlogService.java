/**
 * File Name: 		BlogService.java
 * Version:		0.1
 * Module:		sampledropwizard
 * Developer:		srinath
 * Creation Date:	8 May 2015 14:03:46
 * Description:
 */
package com.trigen.dropwizard.service;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.trigen.dropwizard.config.BlogConfiguration;
import com.trigen.dropwizard.dao.MySqlDaoImpl;
import com.trigen.dropwizard.healthcheck.MongoHealthCheck;
import com.trigen.dropwizard.manage.MongoManage;
import com.trigen.dropwizard.model.Comment;
import com.trigen.dropwizard.model.Post;
import com.trigen.dropwizard.model.PostComment;
import com.trigen.dropwizard.model.PostTag;
import com.trigen.dropwizard.model.Tag;
import com.trigen.dropwizard.resource.BlogResource;
import com.trigen.dropwizard.service.delegate.ServiceDelegate;

/**
 * @author srinath
 *
 */
public class BlogService extends Application<BlogConfiguration> {
    
    public static void main(String arg[]) throws Exception
    {
	new BlogService().run(new String[] { "server",arg[0] });
    }
    
    @Override
    public String getName()
    {
	return "blog";
    }
    
    private final HibernateBundle<BlogConfiguration> hibernateBundle = new HibernateBundle<BlogConfiguration>(Post.class,Comment.class,Tag.class,PostComment.class,PostTag.class){

	@Override
	public DataSourceFactory getDataSourceFactory(BlogConfiguration blogConfiguration) {
	    
	    return blogConfiguration.getDataSourceFactory();
	}
	
    };
    
    @Override
    public void initialize(Bootstrap<BlogConfiguration> bootStrap)
    {
	bootStrap.addBundle(hibernateBundle);
    }

    /* (non-Javadoc)
     * @see io.dropwizard.Application#run(io.dropwizard.Configuration, io.dropwizard.setup.Environment)
     */
    @Override
    public void run(BlogConfiguration blogConfiguration, Environment environment) throws Exception {
	//Mongo db configuration
	MongoClient mongoClientInstance = new MongoClient(blogConfiguration.getMongoHost(), Integer.parseInt(blogConfiguration.getMongoPort()));
	MongoManage mongoManage =new MongoManage(mongoClientInstance);
	environment.lifecycle().manage(mongoManage);
	environment.healthChecks().register("MongoHealthCheck", new MongoHealthCheck(mongoClientInstance));
	MongoDatabase db = mongoClientInstance.getDatabase(blogConfiguration.getMongodb());
	MongoCollection<Document> dbCollection = db.getCollection(blogConfiguration.getMongoCollection());
	
	//MqSql db configuration
	final MySqlDaoImpl mySqlDaoImpl = new MySqlDaoImpl(hibernateBundle.getSessionFactory());
	
	ServiceDelegate serviceDelegate = new ServiceDelegate(mySqlDaoImpl, dbCollection);
	
	environment.jersey().register(new BlogResource(serviceDelegate));
    }

}
