/**
 * File Name: 		Comment.java
 * Version:		0.1
 * Module:		sampledropwizard
 * Developer:		srinath
 * Creation Date:	9 May 2015 00:26:35
 * Description:
 */
package com.trigen.dropwizard.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author srinath
 *
 */
@XmlRootElement(name="comment")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="comment")
public class Comment implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @XmlTransient
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String body;
    
    private String author;
        
    private String email;
    
    private int views;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
  

    /**
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the views
     */
    public int getViews() {
        return views;
    }

    /**
     * @param views the views to set
     */
    public void setViews(int views) {
        this.views = views;
    }

       

}
