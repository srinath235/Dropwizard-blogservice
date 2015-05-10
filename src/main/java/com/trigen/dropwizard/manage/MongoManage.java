/**
 * File Name: 		MongoManage.java
 * Version:		0.1
 * Module:		sampledropwizard
 * Developer:		srinath
 * Creation Date:	8 May 2015 14:43:56
 * Description:
 */
package com.trigen.dropwizard.manage;

import io.dropwizard.lifecycle.Managed;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

/**
 * @author srinath
 *
 */
public class MongoManage implements Managed {
    
    private MongoClient mongoClientInstance;
    
    public MongoManage(MongoClient mongoClientInstance)
    {
	this.mongoClientInstance = mongoClientInstance;
    }

    /* (non-Javadoc)
     * @see io.dropwizard.lifecycle.Managed#start()
     */
    public void start() throws Exception {
	// TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see io.dropwizard.lifecycle.Managed#stop()
     */
    public void stop() throws Exception {
	mongoClientInstance.close();
    }

}
