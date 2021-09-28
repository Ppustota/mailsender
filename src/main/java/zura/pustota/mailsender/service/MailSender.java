package zura.pustota.mailsender.service;

public interface MailSender {
    void sendMail(String to, String content);
}
