package controllers;

import java.util.*;
import javax.persistence.EntityManager;

import org.h2.engine.Session;

import com.google.inject.Inject;
import com.google.inject.Provider;
import Entity.Contact;
import Entity.Med;
import Entity.Registration;
import ninja.Result;
import ninja.Results;
import ninja.jpa.UnitOfWork;
import controllers.TestFacade;

public class TestFacadeImpl implements TestFacade {
    @Inject
    Provider<EntityManager> entitiyManagerProvider;
    
    Result result=Results.html();
    
    @UnitOfWork
    public Result profile(Contact contact)
    {
        String id=contact.getId();
        String psd=contact.getPass();
        String temp=id;
        System.out.println("Id:"+id+" psd:"+psd);
        if(userAuth(id,psd))
        {
            EntityManager entityManager = entitiyManagerProvider.get();
            System.out.println(temp);
            List<Med> med =entityManager.createQuery("from medicines where id = ?1")
            		.setParameter(1,temp).getResultList();
            List<Registration> registration=entityManager.createQuery("from Reg where id = ?1")
            		.setParameter(1,temp).getResultList(); 
            result.render("registration",registration);
            result.render("med",med);
           return result.template("/views/ApplicationController/profile.ftl.html");
        } 
        else
        	return result.template("/views/ApplicationController/lfail.ftl.html");
	
        
    }

    @UnitOfWork
    public boolean userAuth(String userId, String password)
    {
        EntityManager entityManager = entitiyManagerProvider.get();
        List<Contact> lo = entityManager.createQuery("from logs where id =?1 and pass =?2").setParameter(1, userId).setParameter(2,password).getResultList();
        int flag= (lo.size() > 0) ? 1 : 0;
        if(flag==1)
            return true;
        else 
            return false;
    }
}
