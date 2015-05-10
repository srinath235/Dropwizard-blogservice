/**
 * File Name: 		BlogConfiguration.java
 * Version:		0.1
 * Module:		sampledropwizard
 * Developer:		srinath
 * Creation Date:	8 May 2015 14:00:53
 * Description:
 */
package com.trigen.dropwizard.config;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author srinath
 *
 */
public class BlogConfiguration extends Configuration {
    
    @JsonProperty
    @NotEmpty
    public String mongoHost="localhost";
    
    @JsonProperty
    @NotEmpty
    public String mongoPort="27017";
    
    @JsonProperty
    @NotEmpty
    public String mongodb="test";
    
    @JsonProperty
    @NotEmpty
    public String mongoCollection = "posts";
    
    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();

    
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    /**
     * @return the mongoHost
     */
    public String getMongoHost() {
        return mongoHost;
    }

    /**
     * @return the mongoPort
     */
    public String getMongoPort() {
        return mongoPort;
    }

    /**
     * @return the mongodb
     */
    public String getMongodb() {
        return mongodb;
    }

    /**
     * @return the mongoCollection
     */
    public String getMongoCollection() {
        return mongoCollection;
    }
    
      

}
