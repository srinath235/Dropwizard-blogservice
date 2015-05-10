/**
 * File Name: 		Tag.java
 * Version:		0.1
 * Module:		sampledropwizard
 * Developer:		srinath
 * Creation Date:	9 May 2015 00:33:26
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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author srinath
 *
 */
@XmlRootElement(name="tag")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="tag")
public class Tag implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @XmlTransient
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @XmlElement(name="tagname")
    private String tag;

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
     * @return the tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * @param tag the tag to set
     */
    public void setTag(String tag) {
        this.tag = tag;
    } 
    
    
    public String toString()
    {
	return this.tag;
    }
    

}
