package controllers;

import models.Note;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexx on 28.05.2015.
 */
public class Ranking extends Controller{

    public static Result getAllAsHelpieforUser(User user){

        //TODO
        List<Note> ranking = Note.find.where().eq("helpie", user).findList();

        //JSONArray
        return ok();
    }

}
