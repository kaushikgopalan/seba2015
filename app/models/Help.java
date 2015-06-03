package models;

import javax.persistence.*;

import play.data.validation.Constraints;
import play.db.ebean.Model;
import java.util.ArrayList;
import java.util.Date;

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

    public Long[] coordinates;

    public Date creatingDate;

    public Date closingDate;

    public ArrayList<Date> dates;

    @ManyToOne
    public Category category;

    @ManyToOne
    @Constraints.Required
    public User owner;

    @ManyToOne
    public User helpie;

    public boolean done = false;

    public static Finder<String, Help> find = new Finder<String, Help>(String.class, Help.class);
}
