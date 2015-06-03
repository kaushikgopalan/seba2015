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
public class Note extends Model{

    @Id
    @GeneratedValue
    public int id;

    public int rank; //from 1 to 5, for example

    public String description;

    public Date date; //maybe DateTime is better?

    @OneToOne
    public Help help;

    @OneToOne
    public User owner;

    @OneToOne
    public User helpie;

    public static Finder<String, Note> find = new Finder<String, Note>(String.class, Note.class);
}
