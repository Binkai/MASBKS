package MASWF.kreditantrag;

import java.io.InputStream;

import javax.activation.DataHandler;
import javax.mail.internet.MimeBodyPart;

import org.apache.commons.mail.ByteArrayDataSource;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.value.FileValue;

public class MailKreditvertragSenden implements JavaDelegate{
	public void execute(DelegateExecution execution) throws Exception {

		String anrede = (String) execution.getVariable("anrede");
		String vorname = (String) execution.getVariable("vorname");
		String nachname = (String) execution.getVariable("nachname");
		long wunschhoeheKredit = ((Long)execution.getVariable("wunschhoeheKredit")) ;		
		long wunschlaufzeitKredit = (Long)execution.getVariable("wunschlaufzeitKredit");
		String toEmail = (String) execution.getVariable("email");
		
		
		String subject = "Ihr Kreditvertrag";
		String mailtext = "Sehr geehrte/er " + anrede + " " + vorname + " " + nachname + ",\n" + "\nder Kredit in Hoehe von "+wunschhoeheKredit+" wurde fuer einen Zeitraum von "+ wunschlaufzeitKredit+" gestattet."+
		"\nEine Kopie des Kreditvertrags befindet sich im Anhang dieser Mail."
		+ "\nBitte prüfen Sie den Kreditvertrag sehr genau und geben uns binnen 14 Tagen eine Rückmeldung ob Korrekturen notwendig sind."
		+ "\nVielen Dank das Sie sich fuer die Berliner Bank BKS entschieden haben. " + "\n\nMit freundlichen Grüßen,\n die Berliner Bank BKS";
	
		// https://docs.camunda.org/manual/7.5/user-guide/process-engine/variables/

		FileValue retrievedTypedFileValue = execution.getVariableTyped("kreditvertrag");
		InputStream fileContent = retrievedTypedFileValue.getValue(); // bytestream
		String fileName = retrievedTypedFileValue.getFilename(); // filename
		String mimeType = retrievedTypedFileValue.getMimeType(); // memetype
		String encoding = retrievedTypedFileValue.getEncoding(); // encodung

		MimeBodyPart attachment = new MimeBodyPart();
		ByteArrayDataSource ds = new ByteArrayDataSource(fileContent, mimeType);

		attachment.setDataHandler(new DataHandler(ds));
		attachment.setFileName(fileName);

		sendEmail(mailtext, subject, toEmail, ds, fileName, encoding);
	}

	public void sendEmail(String mailtext, String subject, String toEmail, ByteArrayDataSource ds, String fileName, String encoding) throws EmailException {
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
		email.setHostName("mail.htw-berlin.de");
		email.setAuthentication("s0000000", "PASSWORT");
		email.addTo(toEmail);
		email.setFrom("s0000000@htw-berlin.de");
		email.setSubject(subject);
		email.setMsg(mailtext);
		email.attach(ds, fileName, encoding);
		email.send();
	}
}
