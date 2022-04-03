package tr.com.nihatalim.yt.presenter.mail;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class MailService {

    @Value("${app.mail.sendgrid.api.key}")
    private String sendGridApiKey;

    @Value("${app.mail.sendgrid.from.mail}")
    private String fromMail;

    public Response sendMail(MailContext mailContext) {
        Email from = new Email(fromMail);
        String subject = mailContext.subject();
        Email to = new Email(mailContext.to());
        Content content = new Content("text/plain", mailContext.body());
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            return sg.api(request);
        } catch (IOException e) {
            log.error("[MailService] [sendMail] An exception occured while sending mail", e);
            return null;
        }
    }
}
