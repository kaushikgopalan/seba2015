package controllers;

import models.Notification;
import models.Category;
import models.Help;
import models.User;
import models.utils.AppException;
import models.utils.Hash;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.util.Date;

/**
 * Created by Alexx on 28.05.2015.
 */
public class Users extends Controller{

    public static Result testDB(){

        Notification not1 = new Notification();
        not1.id = 20;
        not1.title = "I need your help";
        not1.text = "Only I have a problem with the PC...";

        Category cat1 = new Category();
        Category cat2 = new Category();
        Category cat3 = new Category();
        Category cat4 = new Category();
        cat1.name = "Plumber";
        cat2.name = "Gardner";
        cat3.name = "Computing";
        cat4.name = "Lawyer";
        cat1.save();
        cat2.save();
        cat3.save();
        cat4.save();

        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        User user4 = new User();

        user1.login = "user1@tum.de";
        user2.login = "user2@tum.de";
        user3.login = "user3@tum.de";
        user4.login = "user4@tum.de";

        try {
            user1.hashPass = Hash.createPassword("user1");
            user2.hashPass = Hash.createPassword("user2");
            user3.hashPass = Hash.createPassword("user3");
            user4.hashPass = Hash.createPassword("user4");
        } catch (AppException e) {
            e.printStackTrace();
            return ok("AppException");
        }

        user1.firstName = "Kaushik";
        user2.firstName = "Aaron Perez";
        user3.firstName = "Alexander";
        user4.firstName = "Sebastian";

        user1.lastName = "Gopalan";
        user2.lastName = "Martin";
        user3.lastName = "Tumanin";
        user4.lastName = "Dirrigl";

        user1.skills.add(cat1);
        user1.skills.add(cat2);
        user1.skills.add(cat3);
        user1.skills.add(cat4);

        user2.skills.add(cat1);
        user2.skills.add(cat2);
        user2.skills.add(cat3);

        user3.skills.add(cat1);
        user3.skills.add(cat2);

        user4.skills.add(cat3);

        user1.description = "When the going gets tough, the tough get going.";
        user2.description = "Fortune favors the bold.";
        user3.description = "You can't make an omelet without breaking a few eggs.";
        user4.description = "A picture is worth a thousand words.";

        user1.plan = 0;
        user2.plan = 0;
        user3.plan = 0;
        user4.plan = 0;

        user1.countOfJobsPerMonth = 0;
        user2.countOfJobsPerMonth = 0;
        user3.countOfJobsPerMonth = 0;
        user4.countOfJobsPerMonth = 0;

        user1.area = 1;
        user2.area = 2;
        user3.area = 3;
        user4.area = 4;

        user1.save();
        user2.save();
        user3.save();
        user4.save();

        Help help1 = new Help();
        Help help2 = new Help();
        Help help3 = new Help();
        Help help4 = new Help();

        help1.name = "Catch the elephant";
        help2.name = "Cut the rope";
        help3.name = "Patch KDE2 under FreeBSD";
        help4.name = "Cool painter needed";

        help1.category = cat1;
        help2.category = cat2;
        help3.category = cat3;
        help4.category = cat4;

        help1.owner = user1;
        help2.owner = user1;
        help3.owner = user1;
        help4.owner = user3;

        help3.done = true;
        help3.helpie = user2;

        help3.closingDate = new Date();

        help1.latitude = 48.134293;
        help1.longitude = 11.555912;

        help2.latitude = 28.2686;
        help2.longitude = 16.6056;

        help3.latitude = 48.134293;
        help3.longitude = 11.555912;


        help4.latitude = 28.2686;
        help4.longitude = 16.6056;

        help1.save();
        help2.save();
        help3.save();
        help4.save();

        return ok("test done");
    }

    public static Result signUp(){


        DynamicForm requestData = Form.form().bindFromRequest();
        String sLogin = requestData.get("login");
        String sFirstName = requestData.get("firstName");
        String sLastName = requestData.get("lastName");
        String sDescription = requestData.get("description");
        String sPass = requestData.get("hashPass");
        String sPass2 = requestData.get("hashPass2");

        /*if ((sPass != sPass2 ) || sPass == "" || sPass2 == ""){
            return ok("Password confirmation is not the same or empty");
        }*/

        User user = new User();
        user.login = sLogin;
        user.firstName = sFirstName;
        user.lastName = sLastName;
        user.description = sDescription;
        try {
            user.hashPass = Hash.createPassword(sPass);
        } catch (AppException e) {
            e.printStackTrace();
            return ok("We have an AppException");
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
            return ok(index.render(Help.getLastJobs(), User.getLastHelps()));
        }

        return ok("user is null: "+sLogin+", "+sPass);
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
