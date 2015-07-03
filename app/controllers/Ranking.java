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
import views.html.*;


import java.lang.Integer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Alexx on 28.05.2015.
 */
public class Ranking extends Controller{

    public static List<Note> getAllForHelpie(){
        List<Note> ranking = new ArrayList<>();
        String login = ctx().session().get("login");
        User user = User.find.byId(login);
        if(user!=null){
            ranking = Note.find.where().eq("helpie", user).findList();
        }
        return  ranking;
    }

    public static List<Note> getAllForOwner(){
        List<Note> ranking = new ArrayList<>();
        String login = ctx().session().get("login");
        User user = User.find.byId(login);
        if(user!=null){
            ranking = Note.find.where().eq("owner", user).findList();
        }
        return  ranking;
    }

    public static Result newNote(){
        //Form<Note> form = Form.form(Note.class).bindFromRequest();
        DynamicForm requestData = Form.form().bindFromRequest();
        String jobId = requestData.get("jobId");
        try{

        System.out.println(requestData.get("jobId")+" is the jobid");
        int rank = Integer.parseInt( requestData.get("rankId"));
        String description = requestData.get("description");

        Help help = Help.find.byId(jobId);



        Note note = new Note();
        note.rank= rank;
        note.description= description;
        note.help= help;
        note.owner = help.owner;
        note.helpie= help.helpie;
        note.save();
        }
        catch(Exception e){
            return redirect(routes.Ranking.newRating(jobId));
        }
        return redirect(routes.Application.index());
    }

    // written for Rating redirect to rate page.
    public static Result newRating(String jobId){
        //TODO:
        /*
            first check signed in. Then check if he did the job.

         */
        if (ctx().session().get("login")==null) {
            ctx().session().put("error","Error: Not logged in. You need to login to rate someone.");
            System.out.println("insdie newRating and no login");
            return redirect(routes.Application.index());
                    //ok(index.render(Help.getLastJobs(), User.getLastHelps(),null));
        } else {
            System.out.println(jobId+" is the job id");
            Help help = Help.find.where().eq("id", Integer.parseInt(jobId)).findUnique();
            //Form<Help> myForm = form(Help.class);
            System.out.println("help id:"+ help.id);
            return ok(rating.render(help));
                    // return ok(rating.render("Rating"));

        }

    }

}
