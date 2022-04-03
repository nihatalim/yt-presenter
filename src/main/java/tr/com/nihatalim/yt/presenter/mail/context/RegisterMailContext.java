package tr.com.nihatalim.yt.presenter.mail.context;

import tr.com.nihatalim.yt.presenter.mail.MailContext;

public class RegisterMailContext implements MailContext {
    private final String mail;

    public RegisterMailContext(String mail) {
        this.mail = mail;
    }

    @Override
    public String to() {
        return mail;
    }

    @Override
    public String subject() {
        return "Registration Successful for YtDownloader";
    }

    @Override
    public String body() {
        return "This is an inform for you to use our system with login registered mail and password.";
    }
}
