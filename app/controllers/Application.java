package controllers;

import models.*;
import play.*;
import play.data.*;
import play.db.ebean.Model;
import play.mvc.*;

import views.html.*;

import java.util.ArrayList;
import java.util.List;

import static play.libs.Json.toJson;

public class Application extends Controller {



    public static Result index() {



        return ok(index.render(Help.getLastJobs(), User.getLastHelps()));
    }
    public static Result about() { return ok(about.render("Our Team")); }

    public static Result contact() { return ok(contact.render("Contact Form")); }

    public static Result register() { return ok(register.render("Register")); }

    public static Result signIn() {

        return ok(login.render("Login"));
    }

    public static Result postjob(){

        List<Category> categories = Category.find.all();
        return ok(postHelp.render(categories));
    }

    public static Result profile(){
        return ok(userMainPage.render("Profile"));
    }
    public static Result findJob(){
        return ok();
    }
    //for testing
    public static Result GetAllNotifications(){
        List<Notification> list = Notification.GetAllNotifications();
        return ok(toJson(list));
    }

}
