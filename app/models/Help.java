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

    public ArrayList<Date> dates;

    @ManyToOne
    public Category cathegory;

    @ManyToOne
    @Constraints.Required
    public User owner;

    @ManyToOne
    public User helpie;
}
