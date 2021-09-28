package zura.pustota.mailsender.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ResourceUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MailSenderImpl implements MailSender {
    JavaMailSender javaMailSender;

    @SneakyThrows
    @Override
    @Async
    public void sendMail(String to, String content) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true, "utf-8");


        try {
            helper.setText(content, true);
            helper.setTo(to);
            helper.setSubject("Production");
            helper.setFrom("piotr.pustota123@gmail.com");
            FileSystemResource file = new FileSystemResource(new File("index.jpg"));
            helper.addAttachment("index.jpg", ResourceUtils.getFile("classpath:index.jpg"));
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
        }
    }
}
