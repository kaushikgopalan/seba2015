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

/**
 * Created by Kaushik on 28.06.2015.
 */
public class Navbar extends Controller {
    public static Result showNavbar(){
        String login = ctx().session().get("login");
        User user = User.find.byId(login);
        if(user!=null){

            //return ok(showNavbar(),User);
        }
        else{
            return ok(navBar.render());
        }
        return ok(navBar.render());
    }
}