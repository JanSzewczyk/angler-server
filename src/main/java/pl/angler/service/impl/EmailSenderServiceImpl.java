package pl.angler.service.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import pl.angler.dto.MailRequest;
import pl.angler.dto.MailResponse;
import pl.angler.service.EmailSenderService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    @Autowired
    private JavaMailSender sender;
    @Autowired
    private Configuration config;

    //    @Override
//    public MailResponse sendEmail(MailRequest request, Map<String, Object> model) {
//        MailResponse response = new MailResponse();
//        MimeMessage message = sender.createMimeMessage();
//        try {
//            // set mediaType
//            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
//                    StandardCharsets.UTF_8.name());
//            // add attachment
//            helper.addAttachment("logo.png", new ClassPathResource("logo.png"));
//
//            Template t = config.getTemplate("email-template.ftl");
//            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
//
//            helper.setTo(request.getTo());
//            helper.setText(html, true);
//            helper.setSubject(request.getSubject());
//            helper.setFrom(request.getFrom());
//            sender.send(message);
//
//            response.setMessage("mail send to : " + request.getTo());
//            response.setStatus(Boolean.TRUE);
//
//        } catch (MessagingException | IOException | TemplateException e) {
//            response.setMessage("Mail Sending failure : "+e.getMessage());
//            response.setStatus(Boolean.FALSE);
//        }
//
//        return response;
//    }

    @Override
    public void sendConfirmationMail(String userName, String userEmail) throws MessagingException, IOException, TemplateException {
        Map<String, Object> model = new HashMap<>();
        model.put("Name", userName);
        model.put("Email", userEmail);

        // Set template
        Template t = config.getTemplate("confirm-template.ftl");
        String subject = "[Angler] Registration confirmation";
        String from = "angler.support@angler.com";

        this.sendMail(t, model, userEmail, subject, from);
    }

    @Override
    public void sendRetrieveMail(String userName, String userEmail) throws MessagingException, IOException, TemplateException {
        Map<String, Object> model = new HashMap<>();
        model.put("Name", userName);
        model.put("Email", userEmail);

        // Set template
        Template t = config.getTemplate("retrieve-template.ftl");
        String subject = "[Angler] Password reminder";
        String from = "angler.support@angler.com";

        this.sendMail(t, model, userEmail, subject, from);
    }

    private void sendMail(Template template,  Map<String, Object> model, String userEmail, String subject, String from) throws MessagingException, IOException, TemplateException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        helper.setTo(userEmail);
        helper.setText(html, true);
        helper.setSubject(subject);
        helper.setFrom(from);
        sender.send(message);
    }
}
