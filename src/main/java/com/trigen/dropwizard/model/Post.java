/**
 * File Name: 		Post.java
 * Version:		0.1
 * Module:		sampledropwizard
 * Developer:		srinath
 * Creation Date:	9 May 2015 00:19:23
 * Description:
 */
package com.trigen.dropwizard.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.trigen.dropwizard.jaxb.adapter.DateAdapter;

/**
 * @author srinath
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="post")
@NamedQueries({@NamedQuery(name="findByPermaLink",query="select p from Post p where p.permalink=:permalink"),
    @NamedQuery(name="deleteByPermaLink",query="delete from Post p where p.permalink=:permalink"),
    @NamedQuery(name="loadAllTags",query="select t.id,t.tag from Tag t")})
public class Post implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String permalink;
    
    private String author;
    
    private String title;
    
    @XmlJavaTypeAdapter(value=DateAdapter.class)
    private Date postdate;
    
    private String body;
    
    @XmlElementWrapper(name="comments")
    @XmlElement(name="comment")
    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(
	    name="post_comment",
	    joinColumns={@JoinColumn(name="post_id")},
	    inverseJoinColumns={@JoinColumn(name="comment_id")})
    private Set<Comment> comments;
    
    @XmlElementWrapper(name="tags")
    @XmlElement(name="tag")
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
	    name="post_tag",
	    joinColumns={@JoinColumn(name="post_id")},
	    inverseJoinColumns={@JoinColumn(name="tag_id")})
    private Set<Tag> tags;

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
     * @return the permalink
     */
    public String getPermalink() {
        return permalink;
    }

    /**
     * @param permalink the permalink to set
     */
    public void setPermalink(String permalink) {
        this.permalink = permalink;
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
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the postdate
     */
    public Date getPostdate() {
        return postdate;
    }

    /**
     * @param postdate the postdate to set
     */
    public void setPostdate(Date postdate) {
        this.postdate = postdate;
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
     * @return the comments
     */    
    public Set<Comment> getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    /**
     * @return the tags
     */
    public Set<Tag> getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }       
    
    

}
