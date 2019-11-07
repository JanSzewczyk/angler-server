package pl.angler.service;

public interface EmailSenderService {

    void sendEmail(String to, String subject, String content);
}
