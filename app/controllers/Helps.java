package controllers;

import models.Category;
import models.Help;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Alexx on 28.05.2015.
 */
public class Helps extends Controller{

    public static Result newHelp(User owner, String name, Long[] coordinates, Category category, ArrayList<Date> dates){

        Help help = new Help();
        help.name = name;
        help.owner = owner;
        help.category = category;
        help.coordinates = coordinates;
        help.dates = dates;
        help.creatingDate = new Date();

        return ok();
    }

    public static Result setHelpie(Help help, User helpie){

        help.helpie = helpie;

        return ok();
    }

    public static Result setDone(Help help){

        help.done = true;
        help.closingDate = new Date();

        return ok();
    }

    public static Result getHelpsForOwner(User owner){

        //TODO
        return ok();
    }

    public static Result getHelpsForHelpie(User helpie){

        //TODO
        return ok();
    }
}
