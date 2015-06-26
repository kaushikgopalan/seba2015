package controllers;

import models.Notification;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.userProfileMessage;

import java.util.Date;

/**
 * Created by Aaron on 24/06/15.
 */
public class Notifications extends Controller{

    public static Result newNotification(){

        DynamicForm requestData = Form.form().bindFromRequest();
        //String sId = requestData.get("id");
        String sTitle = requestData.get("title");
        String sText = requestData.get("text");

        Notification noti = new Notification();
        //noti.id = 20;
        noti.title = sTitle;
        noti.text = sText;
        noti.creatingDate = new Date();

        noti.save();

        return ok("Notification stored");
    }
/*
    public static Result getNotificationForOwner(){
        List<Notification> list = Notification.getAllNotificationsByUserId()

    }

    public static Result getNotificationDetails(String  id){
        Notification noti = Notification.find.byId(id);
        List<Notification> list = Notification.getAllNotificationsByUserId()
        return ok(userProfileMessage.render(noti));
    }*/

}
