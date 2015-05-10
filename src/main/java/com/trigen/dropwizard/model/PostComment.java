/**
 * File Name: 		PostComment.java
 * Version:		0.1
 * Module:		sampledropwizard
 * Developer:		srinath
 * Creation Date:	9 May 2015 00:59:26
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
@Table(name="post_comment")
public class PostComment implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @Column(name="post_id")
    private int postId;
    
    @Column(name="comment_id")
    private int commentId;

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
     * @return the commentId
     */
    public int getCommentId() {
        return commentId;
    }

    /**
     * @param commentId the commentId to set
     */
    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }
    
    

}
