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

public class AddItImpl implements AddIt 
{
	@Inject
    Provider<EntityManager> entitiyManagerProvider;
    
    Result result=Results.html();
    @com.google.inject.persist.Transactional
    public Result Inc(Med add)
    {
    	//try {
    	int i=0;
        String id=add.getId();
        String mid=add.getMid();
        int countInc=add.getCounts();
        System.out.println("Id:"+id+" Mid:"+mid+" Counts:"+countInc);
        EntityManager entityManager = entitiyManagerProvider.get();
        int c=(Integer)entityManager.createQuery("select counts from medicines where id=?1 and mid=?2").setParameter(1,id).setParameter(2, mid).getSingleResult();
        System.out.println("Returned Counts:"+(int)c);
        countInc+=(int)c;
         int a =entityManager.createQuery("UPDATE medicines SET counts=?1 where id=?2 and mid=?3")
           		.setParameter(1, countInc).setParameter(2,id).setParameter(3,mid).executeUpdate();
        List<Med> med=entityManager.createQuery("from medicines where id=?1").setParameter(1,id).getResultList();
        for (Iterator iterator = med.iterator(); iterator.hasNext();) {
        	System.out.println("Row"+i++);
			Med med2 = (Med) iterator.next();
			System.out.println("Id:"+ med2.getId());
			System.out.println("Mid:"+ med2.getMid());
			System.out.println("Mname:"+ med2.getMname());
			System.out.println("Counts"+ med2.getCounts());
			
		}
        List<Registration> registration=entityManager.createQuery("from Reg where id=?1").setParameter(1,id).getResultList();
        result.render("med",med);
        result.render("registration",registration);
        return result.template("/views/ApplicationController/profile.ftl.html");
    	/*}
    	catch(Exception e) 
    	{
    		return result.template("/views/ApplicationController/err.ftl.html");
    	} */
        
    }
    
    public Result Incs()
    {
 	   System.out.println("Inside Inc");
 	   return Results.html().template("/views/ApplicationController/add.ftl.html");
    }

}
