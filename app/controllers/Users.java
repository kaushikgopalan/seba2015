package controllers;

import models.Help;
import models.User;
import models.utils.AppException;
import models.utils.Hash;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

/**
 * Created by Alexx on 28.05.2015.
 */
public class Users extends Controller{

    public static Result testDB(){
        User user = new User();
        user.area = 10;
        user.login = "login";
        try {
            user.hashPass = Hash.createPassword("123");
        } catch (AppException e) {
            e.printStackTrace();
            return ok("AppException");
        }
        user.firstName ="user";
        user.lastName ="test";
        user.countOfJobsPerMonth = 0;
        user.plan = 0;
        user.description = "yahoo";
        user.save();

        return ok("test done");
    }

    public static Result signUp(){

        Form<User> form = Form.form(User.class).bindFromRequest();
        User user = form.get();
        String clearPass = user.hashPass;
        try {
            user.hashPass = Hash.createPassword(clearPass);
        } catch (AppException e) {
            e.printStackTrace();
            return badRequest();
        }
        user.save();


        return ok(login.render("Login"));
    }

    public static Result signIn(){

        DynamicForm requestData = Form.form().bindFromRequest();
        String sLogin = requestData.get("login");
        String sPass = requestData.get("password");
        //User user = User.find.where().eq("login", sLogin).findUnique();

        User user = null;
        try {
            user = User.authenticate(sLogin, sPass);
        } catch (AppException e) {
            e.printStackTrace();
            return ok("AppException");
        }
        if(user!=null){
            session("login", sLogin);
            return ok(play.libs.Json.toJson(user));
        }

        return ok("user is null");
    }

    public static Result changePlan(){

        DynamicForm requestData = Form.form().bindFromRequest();
        String sLogin = requestData.get("login");
        String sPlan = requestData.get("plan");
        User user = User.find.where().eq("login", sLogin).findUnique();
        user.plan = Integer.parseInt(sPlan);
        return ok();
    }

    public static Result signOut(){
        session().clear();
        //return ok(index.render());
        return redirect(routes.Application.index());
    }
}
