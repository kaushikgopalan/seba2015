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
    //public static Result login() { return ok(login.render("Yeahh")); }
    public static Result login() { return ok(views.html.login.render("asd")); }

}
