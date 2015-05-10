/**
 * File Name: 		PostDAO.java
 * Version:		0.1
 * Module:		BlogService-Dropwizard
 * Developer:		srinath
 * Creation Date:	9 May 2015 17:34:40
 * Description:
 */
package com.trigen.dropwizard.dao;

import io.dropwizard.hibernate.AbstractDAO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.trigen.dropwizard.model.Post;
import com.trigen.dropwizard.model.PostTag;
import com.trigen.dropwizard.model.Tag;

/**
 * @author srinath
 *
 */
public class MySqlDaoImpl extends AbstractDAO<Post> {

    /**
     * @param sessionFactory
     */
    public MySqlDaoImpl(SessionFactory sessionFactory) {
	super(sessionFactory);	
    }
    
    public Post findById(int id)
    {
	return get(id);
    }
    
    public Post findByPermLink(String permaLink)
    {	
	Query query = namedQuery("findByPermaLink");
	query.setParameter("permalink", permaLink);
	List<Post> posts = list(query);
	if(posts.isEmpty())
	    return null;
	return posts.get(0);
    }
    
    public void deleteByPermLink(String permaLink)
    {	
	Post post = findByPermLink(permaLink);
	if(post!=null)
	    currentSession().delete(post);		
    }
    
    public void createBlog(Post post)
    {
	Set<Tag> clonedTags = new HashSet<Tag>(post.getTags());
	List<Tag> removedTags = new ArrayList<Tag>();
	List<Object[]> existingTags = getTags();
	for(Object[] object:existingTags)
	{
	    String existingTag = (String)object[1];
	    for(Tag clonedTag:clonedTags)
	    {
		if(existingTag.toString().equalsIgnoreCase(clonedTag.toString()))
		{
		    clonedTag.setId((Integer)object[0]);
		    removedTags.add(clonedTag);
		    post.getTags().remove(clonedTag);
		}
	    }
	    
	}
	persist(post);
	for(Tag tag:removedTags)
	{
	    Query query = currentSession().createSQLQuery("insert into post_tag (post_id,tag_id) values (?,?)");
	    query.setParameter(0, post.getId());
	    query.setParameter(1, tag.getId());
	    query.executeUpdate();	    
	}
	
    }
    
    private List<Object[]> getTags()
    {
	Query query = namedQuery("loadAllTags");	
	return query.list();	
    }

}
