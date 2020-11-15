package co.edu.javeriana.services.notifications.domain;

import lombok.Data;

import java.util.Map;

@Data
public class Notification implements java.io.Serializable {

    private String to;
    private String subject;
    private String body;
    private String template;
    private Map<String, Object> params;

}
