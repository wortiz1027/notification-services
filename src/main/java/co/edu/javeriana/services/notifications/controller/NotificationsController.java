package co.edu.javeriana.services.notifications.controller;

import co.edu.javeriana.services.notifications.domain.Notification;
import co.edu.javeriana.services.notifications.domain.Response;
import co.edu.javeriana.services.notifications.domain.Status;
import co.edu.javeriana.services.notifications.services.MailServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class NotificationsController {

    private final MailServices mail;

    @PostMapping("/email")
    public ResponseEntity<Response> enviar(@RequestBody Notification request) {
        Response response = new Response();
        try {

            if (request == null) {
                response.setCode(Status.ERROR.name());
                response.setDescription(String.format("The email notifications has not been send to e-mail"));
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            if (request.getTo().isEmpty()) {
                response.setCode(Status.ERROR.name());
                response.setDescription(String.format("The email notifications has not been send to e-mail, to params is mandatory"));
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            mail.sendEmail(request);
            response.setCode(Status.SUCCESS.name());
            response.setDescription(String.format("The email notifications has been send to e-mail: %s", request.getTo()));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            response.setCode(Status.ERROR.name());
            response.setDescription(String.format("There is an error sending email notification %s", e.getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
