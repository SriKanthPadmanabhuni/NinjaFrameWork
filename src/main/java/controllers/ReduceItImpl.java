package controllers;

import java.util.List;
import javax.persistence.EntityManager;

import org.eclipse.jetty.server.session.JDBCSessionManager.Session;

import com.google.inject.Inject;
import com.google.inject.Provider;
import Entity.Med;
import Entity.Registration;
import ninja.Result;
import ninja.Results;

public class ReduceItImpl implements ReduceIt
{

	@Inject
    Provider<EntityManager> entitiyManagerProvider;
    
    Result result=Results.html();
    
    @com.google.inject.persist.Transactional
    public Result Red(Med r)
    {
    	//try {
        String id=r.getId();
        String mid=r.getMid();
        int countInc=r.getCounts();
        System.out.println("ID:"+id+" Mid:"+mid+" Counts:"+countInc);
        EntityManager entityManager = entitiyManagerProvider.get();
        int c=(Integer)entityManager.createQuery("select counts from medicines where id=?1 and mid=?2").setParameter(1,id).setParameter(2, mid).getSingleResult();
        System.out.println("Returned Counts:"+c);
        countInc-=c;
        int r1 =entityManager.createQuery("UPDATE medicines SET counts=?1 where id=?2 and mid=?3")
           		.setParameter(1, countInc).setParameter(2,id).setParameter(3,mid).executeUpdate();
        List<Med> med=entityManager.createQuery("from medicines where id=?1").setParameter(1,id).getResultList();
        List<Registration> registration=entityManager.createQuery("from Reg where id=?1").setParameter(1,id).getResultList();
        result.render("registration",registration);
        result.render("med",med);
        return result.template("/views/ApplicationController/profile.ftl.html");
    	/* }
    	catch(Exception e)
    	{
    		return result.template("/views/ApplicationController/err.ftl.html");
    	} */
        
    }
    public Result Redu()
    {
 	   return Results.html().template("/views/ApplicationController/reduce.ftl.html");
    }
}
