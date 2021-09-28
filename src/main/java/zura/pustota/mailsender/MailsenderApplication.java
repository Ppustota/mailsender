package zura.pustota.mailsender;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import zura.pustota.mailsender.service.MailSender;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableAsync
public class MailsenderApplication {
    @SneakyThrows
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MailsenderApplication.class, args);
        MailSender sender = context.getBean(MailSender.class);
        sender.sendMail("z.vash22@gmail.com","One more email from Spring boot app");
        context.close();
    }

}
