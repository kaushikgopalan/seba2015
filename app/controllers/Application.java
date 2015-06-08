package controllers;

import models.*;
import play.*;
import play.data.*;
import play.mvc.*;

import views.html.*;

import java.util.ArrayList;
import java.util.List;

public class Application extends Controller {



    public static Result index() {



        return ok(index.render(Help.getLastJobs(), User.getLastHelps()));
    }
    public static Result register() { return ok(register.render("Register")); }

    public static Result signIn() {

        return ok(login.render("Login"));
    }

    public static Result postjob(){

        List<Category> categories = Category.find.all();
        return ok(postHelp.render(categories));
    }

}
