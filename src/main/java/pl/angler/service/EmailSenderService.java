package pl.angler.service;

import freemarker.template.TemplateException;
import pl.angler.dto.MailRequest;
import pl.angler.dto.MailResponse;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;

public interface EmailSenderService {

    MailResponse sendEmail(MailRequest request, Map<String, Object> model);
    void sendConfirmationMail(Long userId, String userName, String email) throws MessagingException, IOException, TemplateException;
}
