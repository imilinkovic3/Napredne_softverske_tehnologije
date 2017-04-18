package rs.silab.nst.email;

import javax.mail.*;
import javax.mail.internet.*;

public class EmailService {

    private EmailConfiguration configuration = null;

    private Authenticator auth = null;

    public EmailService(EmailConfiguration configuration) {
        this.configuration = configuration;
        this.auth = this.buildSmtpAuthenticator();
    }

    private Authenticator buildSmtpAuthenticator() {
        String emailId = configuration.getProperty(EmailConfiguration.SMTP_AUTH_USER);
        String password = configuration.getProperty(EmailConfiguration.SMTP_AUTH_PWD);
        return new SMTPAuthenticator(emailId, password);
    }

    public void sendEmail(Email email) {
        Session session = Session.getDefaultInstance(this.configuration.getProperties(), auth);
        boolean debug = Boolean.valueOf(this.configuration.getProperty(EmailConfiguration.DEBUG));
        session.setDebug(debug);

        try {
            Message msg = this.buildEmailMessage(session, email);
            Transport.send(msg);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private Message buildEmailMessage(Session session, Email email) throws MessagingException {
        Message msg = new MimeMessage(session);
        msg.setSubject(email.getSubject());
        this.addRecievers(msg, email);
        Multipart multipart = new MimeMultipart();
        this.addMessageBodyPart(multipart, email);
        msg.setContent(multipart);
        return msg;
    }

    private void addRecievers(Message msg, Email email) throws MessagingException {
        InternetAddress from = new InternetAddress(email.getFrom());
        msg.setFrom(from);

        InternetAddress[] to = this.getInternetAddresses(email.getTo());
        msg.setRecipients(Message.RecipientType.TO, to);

        InternetAddress[] cc = this.getInternetAddresses(email.getCc());
        msg.setRecipients(Message.RecipientType.CC, cc);

        InternetAddress[] bcc = this.getInternetAddresses(email.getBcc());
        msg.setRecipients(Message.RecipientType.BCC, bcc);

    }

    private void addMessageBodyPart(Multipart multipart, Email email) throws MessagingException {
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(email.getText(), email.getMimeType());
        multipart.addBodyPart(messageBodyPart);
    }

    private InternetAddress[] getInternetAddresses(String... addresses)
            throws AddressException {
        if (addresses == null || addresses.length == 0) {
            return null;
        }
        InternetAddress[] iAddresses = new InternetAddress[addresses.length];
        for (int i = 0; i < addresses.length; i++) {
            iAddresses[i] = new InternetAddress(addresses[i]);
        }
        return iAddresses;
    }
}

class SMTPAuthenticator extends javax.mail.Authenticator {
    private String username;
    private String password;

    public SMTPAuthenticator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
}