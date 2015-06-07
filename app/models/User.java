package models;

import javax.persistence.*;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DIRRIS on 25.05.2015.
 */
@Entity
public class User extends  Model{

    @Id
    public String login;

    @Constraints.Required
    public long hashPass;

    @Constraints.Required
    public String firstName;

    @Constraints.Required
    public String lastName;

    @Column
    public String description;

    @Column
    public long latitude;

    @Column
    public long longitude;

    @Column
    public int area; //reach

    @ManyToMany
    public List<Category> skills;

    @Constraints.Required
    public int plan;

    @Column
    public int countOfJobsPerMonth;

    public User(){
        countOfJobsPerMonth = 0;
    }

    public static Finder<String, User> find = new Finder<String, User>(String.class, User.class);
}
