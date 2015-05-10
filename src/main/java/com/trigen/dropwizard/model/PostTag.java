/**
 * File Name: 		PostTag.java
 * Version:		0.1
 * Module:		sampledropwizard
 * Developer:		srinath
 * Creation Date:	9 May 2015 01:02:34
 * Description:
 */
package com.trigen.dropwizard.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author srinath
 *
 */
@Embeddable
@Table(name="post_tag")
public class PostTag implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Column(name="post_id")
    private int postId;
    
    @Column(name="tag_id")
    private int tagId;

    /**
     * @return the postId
     */
    public int getPostId() {
        return postId;
    }

    /**
     * @param postId the postId to set
     */
    public void setPostId(int postId) {
        this.postId = postId;
    }

    /**
     * @return the tagId
     */
    public int getTagId() {
        return tagId;
    }

    /**
     * @param tagId the tagId to set
     */
    public void setTagId(int tagId) {
        this.tagId = tagId;
    }    
    
    
}
