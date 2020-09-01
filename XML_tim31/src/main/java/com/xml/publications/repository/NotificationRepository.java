package com.xml.publications.repository;

import com.xml.publications.model.Notification.Notification;
import com.xml.publications.utils.Database.DatabaseService;
import com.xml.publications.utils.Database.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationRepository {

    @Autowired
    private Validator validator;

    @Autowired
    private DatabaseService databaseService;

    public String saveNotification(Notification notification) throws Exception {

        if (!validator.validateNotificationAgainstSchema(notification))
            return "Given notification is not valid (schema validation)";
        databaseService.saveNotification(notification);

        return "Notification saved successfully";
    }


}
