package controllers;

import models.Category;
import models.Help;
import models.Note;
import models.User;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Alexx on 28.05.2015.
 */
public class Helps extends Controller{

    public static Result newHelp(){

        Form<Help> form = Form.form(Help.class).bindFromRequest();
        Help help = form.get();
        help.save();

        return ok();
    }

    public static Result setHelpie(){

        DynamicForm requestData = Form.form().bindFromRequest();
        String sHelp = requestData.get("help");
        String sHelpie = requestData.get("helpie");
        Help help = Help.find.where().eq("id", Integer.parseInt(sHelp)).findUnique();
        User helpie = User.find.where().eq("login", sHelpie).findUnique();
        help.helpie = helpie;

        return ok();
    }

    public static Result setDone(){

        Form<Help> form = Form.form(Help.class).bindFromRequest();
        Help help = form.get();
        help.save();

        return ok();
    }

    public static Result getHelpsForOwner(){

        Form<User> form = Form.form(User.class).bindFromRequest();
        User owner = form.get();
        List<Help> helps = Help.find.where().eq("owner", owner).findList();
        return ok(Json.toJson(helps));
    }

    public static Result getHelpsForHelpie(){

        Form<User> form = Form.form(User.class).bindFromRequest();
        User helpie = form.get();
        List<Help> helps = Help.find.where().eq("helpie", helpie).findList();
        return ok(Json.toJson(helps));
    }
}
