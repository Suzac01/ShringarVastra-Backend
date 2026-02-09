package com.example.demo.service.emailService;

import com.example.demo.dto.client.order.orderConfirmationDTO.OrderConfirmationDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
         this.mailSender = mailSender;
    }

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

    public void sendOrderConfirmationEmail(String toEmail, OrderConfirmationDTO orderDetails)
    {
        try
        {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(toEmail);
            helper.setSubject("Order Confirmation - Order #" + orderDetails.getOrderId());

            String htmlContent = createOrderConfirmationHtml(orderDetails);
            helper.setText(htmlContent, true);

            mailSender.send(message);
        }

        catch(MessagingException e) {
            throw new RuntimeException("Failed to send order confirmation email",
                    e);
        }
    }

    private String createOrderConfirmationHtml(OrderConfirmationDTO order) {
        return "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "    <style>" +
                "        body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; }" +
                "        .container { max-width: 600px; margin: 0 auto; padding: 20px; }" +
                "        .header { background-color: #4CAF50; color: white; padding: 20px; text-align: center; }" +
                "        .content { padding: 30px; background-color: #f9f9f9; }" +
                "        .order-details { background-color: white; padding: 20px; border-radius: 5px; margin: 20px 0; }" +
                "        .detail-row { display: flex; justify-content: space-between; margin: 10px 0; }" +
                "        .total { font-size: 18px; font-weight: bold; color: #4CAF50; }" +
                "        .footer { text-align: center; margin-top: 30px; color: #666; font-size: 14px; }" +
                "    </style>" +
                "</head>" +
                "<body>" +
                "    <div class='container'>" +
                "        <div class='header'>" +
                "            <h1>Order Confirmed!</h1>" +
                "        </div>" +
                "        <div class='content'>" +
                "            <p>Dear " + order.getCustomerName() + ",</p>" +
                "            <p>Thank you for your order! We're excited to let you know that we've received your order and it is being processed.</p>" +
                "            " +
                "            <div class='order-details'>" +
                "                <h3>Order Details</h3>" +
                "                <div class='detail-row'>" +
                "                    <span>Order ID:</span>" +
                "                    <span>" + order.getOrderId() + "</span>" +
                "                </div>" +
                "                <div class='detail-row'>" +
                "                    <span>Order Date:</span>" +
                "                    <span>" + order.getOrderDate() + "</span>" +
                "                </div>" +
                "                <div class='detail-row'>" +
                "                    <span>Payment Method:</span>" +
                "                    <span>" + order.getPaymentMethod() + "</span>" +
                "                </div>" +
                "                <div class='detail-row'>" +
                "                    <span>Shipping Address:</span>" +
                "                    <span>" + order.getShippingAddress() + "</span>" +
                "                </div>" +
                "                <hr>" +
                "                <h4>Items Ordered</h4>" +
                "                <div class='detail-row'>" +
                "                    <span>" + order.getProductName() + " (Qty: " + order.getQuantity() + ")</span>" +
                "                    <span>Rs. " + order.getProductPrice() + "</span>" +
                "                </div>" +
                "                <hr>" +
                "                <div class='detail-row'>" +
                "                    <span>Subtotal:</span>" +
                "                    <span>Rs. " + order.getSubtotal() + "</span>" +
                "                </div>" +
                "                <div class='detail-row'>" +
                "                    <span>Tax:</span>" +
                "                    <span>Rs. " + order.getTax() + "</span>" +
                "                </div>" +
                "                <div class='detail-row'>" +
                "                    <span>Shipping:</span>" +
                "                    <span>Rs. " + order.getShippingCost() + "</span>" +
                "                </div>" +
                "                <div class='detail-row total'>" +
                "                    <span>Total:</span>" +
                "                    <span>Rs. " + order.getTotalAmount() + "</span>" +
                "                </div>" +
                "            </div>" +
                "            " +
                "            <p>We'll notify you once your order ships. You can track your order status anytime.</p>" +
                "            <p>Thank you for shopping with us!</p>" +
                "            <p><strong>ShringarVastra Team</strong></p>" +
                "        </div>" +
                "        <div class='footer'>" +
                "            <p>© 2024 ShringarVastra. All rights reserved.</p>" +
                "            <p>If you have any questions, contact us at support@shringarvastra.com</p>" +
                "        </div>" +
                "    </div>" +
                "</body>" +
                "</html>";
    }
}


