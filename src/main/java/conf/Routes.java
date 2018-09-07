package conf;


import ninja.AssetsController;

import ninja.Router;
import ninja.application.ApplicationRoutes;
import controllers.AddItImpl;
import controllers.AddMedImpl;
import controllers.ApplicationController;
import controllers.ReduceItImpl;
import controllers.RegIt;
import controllers.TestFacadeImpl;

public class Routes implements ApplicationRoutes {

    @Override
    public void init(Router router) {  
        
        router.GET().route("/").with(ApplicationController::index);
        router.POST().route("/").with(ApplicationController::index);
        router.POST().route("/log").with(TestFacadeImpl.class,"profile");
        router.GET().route("/adds").with(AddItImpl.class,"Incs");
        router.GET().route("/reg").with(RegIt.class,"reg");
        router.POST().route("/reg").with(RegIt.class,"reg");
        router.GET().route("/reduces").with(ReduceItImpl.class,"Redu");
        router.POST().route("/addOk").with(AddItImpl.class,"Inc");
        router.POST().route("/reduceOk").with(ReduceItImpl.class,"Red");
        router.GET().route("/goReg").with(RegIt.class,"regOpen");
        router.POST().route("/goReg").with(RegIt.class,"regOpen");
        
        router.GET().route("/addMed").with(AddMedImpl.class,"addMedOpen");
        router.GET().route("/newMed").with(AddMedImpl.class,"addMed");
        
        
        //router.GET().route("/hello_world.json").with(ApplicationController::helloWorldJson);
        
 
        ///////////////////////////////////////////////////////////////////////
        // Assets (pictures / javascript)
        ///////////////////////////////////////////////////////////////////////    
        router.GET().route("/assets/webjars/{fileName: .*}").with(AssetsController::serveWebJars);
        router.GET().route("/assets/{fileName: .*}").with(AssetsController::serveStatic);
        
        ///////////////////////////////////////////////////////////////////////
        // Index / Catchall shows index page
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/.*").with(ApplicationController::index);
    }

}
