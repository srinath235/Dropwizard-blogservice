/**
 * File Name: 		AdaptedDate.java
 * Version:		0.1
 * Module:		BlogService-Dropwizard
 * Developer:		srinath
 * Creation Date:	10 May 2015 12:06:26
 * Description:
 */
package com.trigen.dropwizard.jaxb.adapter;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author srinath
 *
 */
public class AdaptedDate {

    private String date;  
    
    private String time;

    @XmlElement(name="date")
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }
    
    @XmlElement(name="time")
    public String getTime() {
        return time;
    }

    /**
     * @param postdate the date to set
     */
    public void setTime(String time) {
        this.time = time;
    }
    
}
