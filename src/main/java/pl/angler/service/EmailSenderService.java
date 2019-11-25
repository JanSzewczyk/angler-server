package pl.angler.service;

import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;


public interface EmailSenderService {

    void sendConfirmationMail(String userName, String email) throws MessagingException, IOException, TemplateException;
    void sendRetrieveMail(String userName, String userEmail) throws MessagingException, IOException, TemplateException;
}
