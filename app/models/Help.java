package models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Alexx on 28.05.2015.
 */
public class Help {

    private String id;
    private String name;
    private Long[] coordinates;
    private ArrayList<Date> dates;
    private int cathegory;
    private User owner;
    private User helpie;
}
