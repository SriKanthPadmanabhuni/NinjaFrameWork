package controllers;

import java.util.List;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Provider;

import Entity.Contact;
import Entity.Med;
import Entity.Registration;
import ninja.Result;
import ninja.Results;

public class AddMedImpl implements AddMed 
{
	@Inject
    Provider<EntityManager> entitiyManagerProvider;
    
    Result result=Results.html();
    
    @com.google.inject.persist.Transactional
    public Result addMed(Med n)
    {
    	//try {
    	n=new Med();
    	String id=n.getId();
        String mid=n.getMid();
        String mname=n.getMname();
        int counts=n.getCounts();
        n.setId(id);
        n.setMid(mid);
        n.setMname(mname);
        n.setCounts(counts);
        EntityManager entityManager = entitiyManagerProvider.get();
        entityManager.persist(n);
    	//int m=entityManager.createQuery("insert into medicines(id,mid,mname,counts)"+"select id,mid,mname,counts from medicines").executeUpdate();
    	//System.out.println("New Row Added:"+m);
    	List<Med> med=entityManager.createQuery("from medicines where id=?1").setParameter(1,id).getResultList();
    	List<Registration> registration=entityManager.createQuery("from reg where id=?1").setParameter(1,id).getResultList();
    	result.render("med",med);
    	result.render("registration",registration);
    	return result.template("/views/ApplicationController/profile.ftl.html");
    	/* }
    	catch(Exception e) 
    	{
    		return result.template("/views/ApplicationController/err.ftl.html");
    	} */
    }
    public Result addMedOpen()
    {
    	return result.template("/views/ApplicationController/addMed.ftl.html");
    }
    
}
