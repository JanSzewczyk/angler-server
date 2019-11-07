package pl.angler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import pl.angler.repository.UserRepository;
import pl.angler.service.EmailSenderService;


@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailSenderService emailSender;
    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    public EmailController(EmailSenderServiceImpl emailSender, TemplateEngine templateEngine){
//        this.emailSender = emailSender;
//        this.templateEngine = templateEngine;
//    }

    @PostMapping("")
    public String send() {
//
//        Context context = new Context();
//        context.setVariable("header", "Nowy artykuł na CodeCouple");
//        context.setVariable("title", "#8 Spring Boot – email - szablon i wysyłanie");
//        context.setVariable("description", "Tutaj jakis opis...");
//        String body = templateEngine.process("template", context);
//        emailSender.sendEmail("jan.szewczyk1997@gmail.com", "CodeCouple Newsletter", body);

//        userRepository.deleteById(Long.valueOf(129));

        return "index";
    }
}