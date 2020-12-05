package co.edu.javeriana.services.notifications.services;

import co.edu.javeriana.services.notifications.domain.Notification;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class MailServices {

    @Value("${spring.mail.username}")
    private String from;

    @Value("${spring.mail.username}")
    private String templateDir;

    private final JavaMailSender sender;

    private final Configuration config;

    public void sendEmail(Notification data) throws IOException, MessagingException, TemplateException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                                                         MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                                                         StandardCharsets.UTF_8.name());

        helper.addInline("logo.png", new ClassPathResource("memorynotfound-logo.png"));
        helper.addAttachment("logo.png", new ClassPathResource("memorynotfound-logo.png"));

        data.getParams().put("KEY_MESSAGE", data.getBody());
        Template template = config.getTemplate(data.getTemplate());
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, data.getParams());

        helper.setTo(data.getTo());
        helper.setText(html, true);
        helper.setSubject(data.getSubject());
        helper.setFrom(from);

        sender.send(message);
    }

}

