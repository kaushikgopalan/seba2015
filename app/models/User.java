package models;

import javax.persistence.*;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import java.util.ArrayList;

/**
 * Created by DIRRIS on 25.05.2015.
 */
@Entity
public class User extends  Model{

    @Id
    @GeneratedValue
    public int id;

    @Constraints.Required
    public String login;

    @Constraints.Required
    public long hashPass;

    @Constraints.Required
    public String firstName;

    @Constraints.Required
    public String lastName;

    public String description;

    public Long[] coordinates;

    public int area; //reach

    @ManyToMany
    public ArrayList<Category> skills;

    @Constraints.Required
    public int plan;

    public int countOfJobsPerMonth;

    public User(){
        countOfJobsPerMonth = 0;
    }

    public static Finder<String, User> find = new Finder<String, User>(String.class, User.class);
}
