package controllers;

import models.Note;
import models.User;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import java.util.List;

/**
 * Created by Alexx on 28.05.2015.
 */
public class Ranking extends Controller{

    public static Result getAllFirHelpie(){

        Form<User> form = Form.form(User.class).bindFromRequest();
        User user = form.get();
        List<Note> ranking = Note.find.where().eq("helpie", user).findList();

        return ok(Json.toJson(ranking));
    }

    public static Result getAllForOwner(){
        Form<User> form = Form.form(User.class).bindFromRequest();
        User user = form.get();
        List<Note> ranking = Note.find.where().eq("owner", user).findList();

        return ok(Json.toJson(ranking));
    }

    public static Result newNote(){
        Form<Note> form = Form.form(Note.class).bindFromRequest();
        Note note = form.get();
        note.save();

        return ok();
    }

}
