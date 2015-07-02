package controllers;

import models.*;
import org.apache.commons.logging.Log;
import play.*;
import play.data.*;
import play.db.ebean.Model;
import play.mvc.*;

import scala.Console;
import views.html.*;


import java.lang.System;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

import static play.libs.Json.toJson;

public class Application extends Controller {

    private static Boolean islogged(){
        return ctx().session().get("login") != null;
    }
    public static User getLoggedUser(){
        if(islogged()){
            return User.find.byId(ctx().session().get("login"));
        }
        return null;
    }

    public static Result index(){

        if(ctx().session().get("error")!=null)
            {
                String error=ctx().session().get("error");
                ctx().session().remove("error");
                return ok(index.render(Help.getLastJobs(), User.getLastHelps(),error));

            }
        if (ctx().session().get("login")==null) {
            return ok(index.render(Help.getLastJobs(), User.getLastHelps(),null));
        } else {
            return ok(index.render(Help.getJobsNotDone(), User.getHelpies(),null));
        }
    }
    //testing if this works.. will be returning this index in case there is error
//    public static Result index(String error){
//        // right now, this is returned that no login found and hence with error.
//        // the other one just returns the the empty thing.
//        System.out.println("here at other index method.Error: "+error);
//        return ok(index.render(Help.getLastJobs(), User.getLastHelps(),error));
//
//    }
    public static Result about() { return ok(about.render("Our Team :")); }

    public static Result contact() { return ok(contact.render("Contact Form :")); }

    public static Result register() { return ok(register.render("Register")); }

    public static Result signIn() {

        return ok(login.render("Login"));
    }

    public static Result postjob(){
        System.out.println("inside postjob");
        String login = ctx().session().get("login");
        if(login==null){
            ctx().session().put("error","Error: Not logged in");
            System.out.println("insdie post job and error no login");
            return redirect(routes.Application.index());
            //return ok(postHelp.render(categories,"error: not logged in"));
        }
        List<Category> categories = Category.find.all();
        return ok(postHelp.render(categories,null));
    }

    public static Result profile(){
        return ok(userMainPage.render("Profile"));
    }

    public static Result findJob(){
        List<Category> categories = Category.find.all();
        List<Help> jobs = Help.find.all();
        List<Help> list = new ArrayList<Help>();
        for(Help help : jobs){
            if(!help.isDeleted) list.add(help);
        }
        return ok(findJob.render(categories, list));
    }

    //for testing
/*    public static Result GetAllNotifications(){
        List<Notification> list = Notification.GetAllNotifications();
        return ok(toJson(list));
    }*/

    public static User getUserFromSession(){

        if(!islogged()){
            return new User();
        }
        String login = ctx().session().get("login");
        User user = User.find.byId(login);

        return user;

    }

}
