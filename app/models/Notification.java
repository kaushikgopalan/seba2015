package models;

import javax.persistence.*;

import com.avaje.ebean.Expr;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.mvc.Result;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static play.libs.Json.toJson;

/**
 * Created by Aaron on 24/06/15.
 */

@Entity
public class Notification extends Model{

    @Id
    @GeneratedValue
    public int id;

    @Column
    public String title;

    @Column
    public String text;

    @Column
    public Date creatingDate;


    @ManyToOne
    public User sender;

    @ManyToOne
    public User receiver;

    @ManyToOne
    public Help help;


    public Notification (){
        creatingDate = new Date();
    }

    public static Finder<String, Notification> find = new Finder<String, Notification>(String.class, Notification.class);

    public static Notification findByHelpID(String HelpID) {
        return find.where().eq("help", HelpID).findUnique();
    }

    public static Notification findByJobID(String JobID) {
        return find.where().eq("asociatedJob", JobID).findUnique();
    }

    //All Nofitifactions sent and received from and to the same user.
    public static List<Notification> getAllNotificationsByUserId(User user){
        List<Notification> filterList = new ArrayList<>();

        if (user != null && user.login != ""){
            filterList = (List<Notification>) find.where().or(Expr.like("sender",user.login),Expr.like("receiver",user.login));
        }
        return filterList;
    }

    public static List<Notification> GetAllNotifications() {
        List<Notification> list = Notification.find.all();
        return list;
    }
}
