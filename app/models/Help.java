package models;

import javax.persistence.*;

import play.data.validation.Constraints;
import play.db.ebean.Model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Alexx on 28.05.2015.
 */

@Entity
public class Help extends  Model{

    @Id
    @GeneratedValue
    public int id;

    @Constraints.Required
    public String name;

    @Column
    public long latitude;

    @Column
    public long longitude;
    @Column
    public Date creatingDate;
    @Column
    public Date closingDate;
    @Column
    public ArrayList<Date> dates;

    @ManyToOne
    public Category category;

    @ManyToOne
    public User owner;

    @ManyToOne
    public User helpie;

    public boolean done = false;

    public Help (){
        creatingDate = new Date();
    }

    public static Finder<String, Help> find = new Finder<String, Help>(String.class, Help.class);

    public static List<Help> getJobsNotDone(){
        List<Help> allHelps = find.all();
        List<Help> notDoneHelps = new ArrayList<>();
        for (int i=0; i<allHelps.size(); i++){
            if(allHelps.get(i).done==false) notDoneHelps.add(allHelps.get(i));
        }
        return notDoneHelps;
    }
}
