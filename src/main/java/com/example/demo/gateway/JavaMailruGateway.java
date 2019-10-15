package com.example.demo.gateway;

import com.example.demo.domain.DepositOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

@Component
public class JavaMailruGateway implements IMailGateway {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private String port;

    @Value("${spring.mail.protocol}")
    private String protocol;

    @Override
    @Async
    public void send(String emailTo, DepositOperation currentDeposit) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject("Current deposit status");
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(0);
        df.setGroupingUsed(false);
        String message = "Dear, " + currentDeposit.getCurrentBorrower().getFirstName() + ' ' + currentDeposit.getCurrentBorrower().getSurname() +
                " .Your current deposit today is: " + df.format(currentDeposit.getTotalAmount()) + " with deposit percent: " + currentDeposit.getPercent() + "%.";
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }
}
