package controllers;

import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.eclipse.jetty.server.session.JDBCSessionManager.Session;
import org.eclipse.jetty.webapp.WebAppContext.Context;
import org.hibernate.jpa.criteria.compile.CriteriaQueryTypeQueryAdapter;
import com.google.inject.Inject;
import com.google.inject.Provider;
import Entity.Contact;
import Entity.Med;
import Entity.Registration;
import ninja.Result;
import ninja.Results;
import org.hibernate.SessionFactory;
import org.hibernate.*;

public class RegIt implements Reg
{
	@Inject
	Provider<EntityManager> entitiyManagerProvider;
	
	Result result=Results.html();
    @com.google.inject.persist.Transactional
    public Result reg(Registration registration) 
    {
    	try {
    	String id=registration.getId();
    	String name=registration.getName();
    	String email=registration.getEmail();
    	String pass=registration.getPass();
    	registration.setId(id);
    	registration.setName(name);
    	registration.setEmail(email);
    	registration.setPass(pass);
    	Contact contact=new Contact();
    	contact.setId(id);
    	contact.setPass(pass);
    	System.out.println("Inside Registration:");
    	System.out.println("Id:"+id+" Name:"+name+" Email:"+email+" Password:"+pass);
    	EntityManager entityManager = entitiyManagerProvider.get();
    	entityManager.persist(registration);
    	entityManager.persist(contact);
    	//int r=entityManager.createQuery("insert into Reg(id,name,email,pass)"+"select id,name,email,pass from Reg").executeUpdate();
    	//int r1=entityManager.createQuery("insert into logs(id,pass)"+"select id,pass from Reg").executeUpdate();
    	//System.out.println("R:"+r+" R1:"+r1);
    	return result.template("/views/ApplicationController/index.ftl.html");
    	}
    	catch(Exception e) 
    	{
    		return result.template("/views/ApplicationController/err.ftl.html");
    	}
    }
    public Result regOpen()
    {
    	return result.html().template("/views/ApplicationController/reg.ftl.html");
    }
}
