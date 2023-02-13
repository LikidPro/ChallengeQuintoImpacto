package ChallengeBackend.QuintoImpacto.servicios;

public interface EmailService {

//    public boolean sendEmail(EmailBody emailBody);

    public boolean sendEmailTool(String textMessage, String email, String subject);

}
