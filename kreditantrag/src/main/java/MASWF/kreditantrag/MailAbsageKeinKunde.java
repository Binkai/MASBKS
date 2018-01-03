package MASWF.kreditantrag;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class MailAbsageKeinKunde implements JavaDelegate {

	public void execute(DelegateExecution execution) throws Exception {

		String anrede = (String) execution.getVariable("anrede");
		String vorname = (String) execution.getVariable("vorname");
		String nachname = (String) execution.getVariable("nachname");
		String email = (String) execution.getVariable("email");

		String mailtext = "Sehr geehrte/er " + anrede + " " + vorname + " " + nachname + ",\n" + "\nLeider müssen wir Ihre Kreditanfrage ablehnen, da Sie kein Kunde bei unserer Bank sind.\nWerden sie Mitglied bei unserer Bank und stellen Sie einen neuen Kreditwunsch. "
		+ "\n Mit freundlichen Grüßen,\n die Berliner Bank BKS.";

		String subject = "Ihre Kreditanfrage /Absage kein Bankkunde";
		System.out.println(subject);
		sendEmail(mailtext, subject, email);
	}

	public void sendEmail(String mailtext, String subject, String toEmail) throws EmailException {
		//https://anleitungen.rz.htw-berlin.de/de/email/e-mail_programm/

		MultiPartEmail email = new MultiPartEmail();
		email.setCharset("utf-8");
		email.setSSL(true);
		email.setSmtpPort(587);
//		email.setSmtpPort(587) -> diese Informationen sind je Provider unterschiedlich
//		email.setHostName("mail.gmx.net"); -> bei GMX muss z.B. die Verwendung online freigeschaltet werden
//		email.setAuthentication("XXXX@gmx.de", "XXXXXXX");
//		email.addTo(toEmail);
//		email.setFrom("XXXXXX@gmx.de");
		email.setHostName("smtp.web.de");
		email.setAuthentication("bks.bank", "masbks17183");
		email.addTo(toEmail);
		email.setFrom("bks.bank@web.de");
		email.setSubject(subject);
		email.setMsg(mailtext);
		email.send();
	}
}
