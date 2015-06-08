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

        List<Help> allJobs = Help.getJobsNotDone();
        List<Help> lastJobs = new ArrayList<>();
        if (allJobs.size()>0) lastJobs.add(allJobs.remove(allJobs.size()-1));
        if (allJobs.size()>0) lastJobs.add(allJobs.remove(allJobs.size()-1));

        List<User> allHelps = User.getHelpies();
        List<User> lastHelps = new ArrayList<>();
        if(allHelps.size()>0) lastHelps.add(allHelps.remove(allHelps.size()));
        if(allHelps.size()>0) lastHelps.add(allHelps.remove(allHelps.size()));

        return ok(index.render(lastJobs, lastHelps));
    }
    public static Result register() { return ok(register.render("Register")); }

    public static Result signIn() {

        return ok(login.render("Login"));
    }

}
