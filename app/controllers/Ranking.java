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

    public static List<Note> getAllFirHelpie(){
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

}
