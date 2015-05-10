/**
 * File Name: 		DateAdapter.java
 * Version:		0.1
 * Module:		BlogService-Dropwizard
 * Developer:		srinath
 * Creation Date:	10 May 2015 12:03:04
 * Description:
 */
package com.trigen.dropwizard.jaxb.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author srinath
 *
 */
public class DateAdapter extends XmlAdapter<AdaptedDate, Date> {
    
    private SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
    private SimpleDateFormat HHmmss = new SimpleDateFormat("HHmmss");
    private SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");

    @Override
    public Date unmarshal(AdaptedDate v) throws Exception {
        String dateString = v.getDate()+v.getTime();
        return yyyyMMddHHmmss.parse(dateString);
    }

    @Override
    public AdaptedDate marshal(Date v) throws Exception {
        AdaptedDate adaptedDate = new AdaptedDate();
        adaptedDate.setDate(yyyyMMdd.format(v));
        adaptedDate.setTime(HHmmss.format(v));        
        return adaptedDate;
    }

}