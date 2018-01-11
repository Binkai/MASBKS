package MASWF.kreditantrag;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
/**
 * Bereitet Absage-Mail vor und entnimmt die Begruendung aus der
 * DMN Tabelle
 * @author kaikuhfeld
 *
 */
public class MailAbsageUnwuerdig implements JavaDelegate {

	public void execute(DelegateExecution execution) throws Exception {

		String anrede = (String) execution.getVariable("anrede");
		String vorname = (String) execution.getVariable("vorname");
		String nachname = (String) execution.getVariable("nachname");
		String email = (String) execution.getVariable("email");
		String begruendung= (String) execution.getVariable("begruendung");

		String mailtext = "Sehr geehrte/er " + anrede + " " + vorname + " " + nachname + ",\n" + begruendung + "\n Mit freundlichen Grüßen,\n die Berliner Bank BKS.";

		String subject = "Ihre Kreditanfrage /Absage unzulaessiger Score";
		System.out.println(subject);
		sendEmail(mailtext, subject, email);
	}

	public void sendEmail(String mailtext, String subject, String toEmail) throws EmailException {
		//https://anleitungen.rz.htw-berlin.de/de/email/e-mail_programm/

		MultiPartEmail email = new MultiPartEmail();
		email.setCharset("utf-8");
		email.setSSL(true);
		
//		email.setSmtpPort(587) -> diese Informationen sind je Provider unterschiedlich
//		email.setHostName("mail.gmx.net"); -> bei GMX muss z.B. die Verwendung online freigeschaltet werden
//		email.setAuthentication("XXXX@gmx.de", "XXXXXXX");
//		email.addTo(toEmail);
//		email.setFrom("XXXXXX@gmx.de");
		email.setSmtpPort(587); //-> diese Informationen sind je Provider unterschiedlich
		email.setHostName("mail.gmx.net"); //-> bei GMX muss z.B. die Verwendung online freigeschaltet werden
		email.setAuthentication("bks.bank@gmx.de", "masbks17183");
		email.addTo(toEmail);
		email.setFrom("bks.bank@gmx.de");
		email.setSubject(subject);
		email.setMsg(mailtext);
		email.send();
	}
}
