package controllers;

import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

/**
 * Created by Alexx on 28.05.2015.
 */
public class Users extends Controller{

    public static Result signUp(){

        //TODO

        return ok();
    }

    public static Result signIn(){

        //TODO

        return ok();
    }

    public static Result changePlan(User user, int plan){

        user.plan = plan;
        return ok();
    }
}
