package controllers;

import models.*;
import play.*;
import play.data.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {



        return ok(index.render());
    }
    public static Result register() { return ok(register.render("Register")); }
    public static Result signIn() {

        return ok(login.render("Login"));
    }

}
