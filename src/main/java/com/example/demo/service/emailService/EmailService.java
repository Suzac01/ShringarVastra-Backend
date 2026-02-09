package com.example.demo.service.emailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordResetEmail(String toEmail, String resetLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Password Reset Request");

        String emailBody = "Dear User,\n\n" +
                "You have requested to reset your password.\n\n" +
                "Please click the link below to reset your password:\n" +
                resetLink + "\n\n" +
                "This link will expire in 30 minutes.\n\n" +
                "If you didn't request this, please ignore this email.\n\n" +
                "Best regards,\n" +
                "Your Application Team";

        message.setText(emailBody);

        try {
            mailSender.send(message);
            System.out.println("Password reset email sent to: " + toEmail);
        } catch (Exception e) {
            System.err.println("Failed to send email: " + e.getMessage());
            throw new RuntimeException("Failed to send email");
        }
    }

    public void sendPasswordChangedConfirmation(String toEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Password Changed Successfully");

        String emailBody = "Dear User,\n\n" +
                "Your password has been successfully changed.\n\n" +
                "If you didn't make this change, please contact our support team immediately.\n\n" +
                "Best regards,\n" +
                "Your Application Team";

        message.setText(emailBody);

        try {
            mailSender.send(message);
            System.out.println("Password change confirmation sent to: " + toEmail);
        } catch (Exception e) {
            System.err.println("Failed to send confirmation email: " + e.getMessage());
        }
    }
}