package models;

import javax.persistence.*;

import models.utils.AppException;
import models.utils.Hash;
import play.data.format.Formats;
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
    public String hashPass;

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
    public boolean isDeleted;

    @Column
    public int area; //reach

    @ManyToMany
    public List<Category> skills;

    @Constraints.Required
    public int plan;

    @Column
    public int countOfJobsPerMonth;

    public String confirmationToken;

    @Formats.NonEmpty
    public Boolean validated = false;

    public User(){
        countOfJobsPerMonth = 0;
        skills = new ArrayList();
    }

    public static Finder<String, User> find = new Finder<String, User>(String.class, User.class);

    public static User findByConfirmationToken(String token) {
        return find.where().eq("confirmationToken", token).findUnique();
    }

    public static User authenticate(String login, String clearPassword) throws AppException {

        // get the user with email only to keep the salt password
        User user = find.where().eq("login", login).findUnique();
        if (user != null && !user.isDeleted) {
            // get the hash password from the salt + clear password
            if (Hash.checkPassword(clearPassword, user.hashPass)) {
                return user;
            }
        }
        return null;
    }

    public static List<User> getHelpies(){
        List<User> allUsers = find.all();
        List<User> helpies = new ArrayList<>();
        for(User user: allUsers){
            if(user.skills!=null && !user.isDeleted){
                if (user.skills.size()!=0) {
                    helpies.add(user);
                }
            }
        }
        return helpies;
    }

    public static List<User> getLastHelps(){
        List<User> allHelps = User.getHelpies();
        List<User> lastHelps = new ArrayList<>();
        if(allHelps.size()>0) lastHelps.add(allHelps.remove(allHelps.size()-1));
        if(allHelps.size()>0) lastHelps.add(allHelps.remove(allHelps.size()-1));
        return lastHelps;
    }
}
