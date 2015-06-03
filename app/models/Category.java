package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by Alexx on 28.05.2015.
 */
@Entity
public class Category extends Model{

    @Id
    @GeneratedValue
    public int id;

    @Constraints.Required
    public String name;

    @ManyToOne
    public Category parent;

    public static Finder<String, Category> find = new Finder<String, Category>(String.class, Category.class);

}
