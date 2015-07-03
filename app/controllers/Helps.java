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
import views.html.findJob;
import views.html.helpDetails;
import views.html.index;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Alexx on 28.05.2015.
 */
public class Helps extends Controller{

    public static Result newHelp(){
        //Form<Help> form = Form.form(Help.class).bindFromRequest();
        //Help help = form.get();

        DynamicForm requestData = Form.form().bindFromRequest();
        String sName = requestData.get("name");
        String sCat = requestData.get("category");
        String sDesc = requestData.get("description");
        String sPreis = requestData.get("price");
        Help help = new Help();
        help.name = sName;
        help.category = Category.find.where().eq("name", sCat).findUnique();
        help.description = sDesc;
        help.longitude = Double.parseDouble(requestData.get("longitude"));
        help.latitude = Double.parseDouble(requestData.get("latitude"));

        try {
            help.price = Integer.valueOf(sPreis);
        } catch (NumberFormatException e) {
            help.price = 1;
        }

        String login = ctx().session().get("login");
        User user = User.find.byId(login);
        if(user!=null){
            help.owner=user;
            help.save();
            return redirect(routes.Application.index());
        }

        return ok("Please login!");
    }
    public static Result updateHelp(String id){
        Help newHelp = Form.form(Help.class).bindFromRequest().get();

        Help oldHelp = Help.find.byId(id);

        return ok("Help updated");
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

    /*public static Result getHelpsForOwner(){

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
    }*/

    public static Result details(String  id){
        Help help = Help.find.byId(id);
        return ok(helpDetails.render(help));
    }

    public static List<Help> getHelpsForOwner(){
        String login = ctx().session().get("login");
        if(login == null){
            return new ArrayList<>();
        }
        User user = User.find.where().eq("login", login).findUnique();

        List<Help> userHelps = new ArrayList<>();
        if(user!=null){
            userHelps.addAll(Help.getHelpsForOwner(user));
        }
        return userHelps;
    }
    public static List<Help> getHelpsForHelpie(){
        String login = ctx().session().get("login");
        if(login == null){
            return new ArrayList<>();
        }
        User user = User.find.byId(login);
        List<Help> userHelps = new ArrayList<>();
        if(user!=null){
            userHelps.addAll(Help.getHelpsForHelpie(user));
        }
        return userHelps;
    }

    public static List<Help> getHelpsWithUserFilter(){
        String login = ctx().session().get("login");
        if(login == null){
            return new ArrayList<>();
        }
        User user = User.find.byId(login);
        List<Help> filteredUserHelps = new ArrayList<>();
        if(user !=null){
            filteredUserHelps.addAll(Help.getHelpsWithUserFilter(user));
        }
        return filteredUserHelps;
    }

    public static List<Category> getAllCategories(){
        return (List<Category>)Category.find.all();
    }

    public static Result delete(String id){
        Help help = Help.find.byId(id);
        help.isDeleted = true;
        help.update();
        return ok(index.render(Help.getJobsNotDone(), User.getHelpies(), null));
    }

    public static Result getAllJobsWithCategory(String selectedCategory){
        List<Help> allJobs = Help.find.all();
        List<Help> allJobsWithCategory = new ArrayList<>();

        for (Help help : allJobs){
            if (help.category.name.equals(selectedCategory)) {
                allJobsWithCategory.add(help);
            }
        }
        return ok(findJob.render(getAllCategories(), allJobsWithCategory));

    }

}
