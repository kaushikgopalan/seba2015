package models;

import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Alexx on 25.06.2015.
 */
@Entity
public class Message extends Model {

    @Id
    @GeneratedValue
    public int id;

    @Column
    public String description;

    @Column
    public User sender;

    @Column
    public User receiver;

    @Column
    public Help help;

    @Column
    public Date creatingDate;

    public Message(){
        creatingDate = new Date();
    }
    public static Finder<String, Message> find = new Finder<String, Message>(String.class, Message.class);
}
