package controllers;

import models.Note;
import models.User;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
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
        Form<Note> form = Form.form(Note.class).bindFromRequest();
        Note note = form.get();
        note.save();

        return ok();
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
            return ok(rating.render());
                    // return ok(rating.render("Rating"));
                    //ok(index.render(Help.getJobsNotDone(), User.getHelpies(),null));
        }

    }

}
