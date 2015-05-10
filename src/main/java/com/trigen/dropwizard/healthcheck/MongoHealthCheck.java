/**
 * File Name: 		MongoHealthCheck.java
 * Version:		0.1
 * Module:		sampledropwizard
 * Developer:		srinath
 * Creation Date:	8 May 2015 14:48:53
 * Description:
 */
package com.trigen.dropwizard.healthcheck;

import com.codahale.metrics.health.HealthCheck;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

/**
 * @author srinath
 *
 */
public class MongoHealthCheck extends HealthCheck {
    
    private MongoClient mongoClientInstance;
    
    
    public MongoHealthCheck(MongoClient mongoClientInstance) {	
	this.mongoClientInstance = mongoClientInstance;
    }

    /* (non-Javadoc)
     * @see com.codahale.metrics.health.HealthCheck#check()
     */
    @Override
    protected Result check() throws Exception {
	mongoClientInstance.getDatabaseNames();
	return Result.healthy();
    }

}
