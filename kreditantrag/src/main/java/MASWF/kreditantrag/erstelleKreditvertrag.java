package MASWF.kreditantrag;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.FileValue;
/**
 * @author Kai Kuhfeld
 *	Erstellt ein Kreditvertrag im PDF Format und speichert sie 
 *	temporär im user.home Directory mit Hilfe von ApachePDFBox
 *	Siehe dazu auch: https://pdfbox.apache.org/
 */
public class erstelleKreditvertrag implements JavaDelegate {
	private final String PDFSpeichern = System.getProperty("user.home")+"/Kreditvertrag.pdf";
	private PDDocument pdfdoc;

	@Override
	public void execute(DelegateExecution exec) throws Exception {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String Anrede = exec.getVariable("anrede").toString();
		String Nachname = exec.getVariable("nachname").toString();
		String hoehe = exec.getVariable("wunschhoeheKredit").toString();
		String laufzeit = exec.getVariable("wunschlaufzeitKredit").toString();
		String rueckzahlung = exec.getVariable("rueckzahlungsrate").toString();
		//Starttermin Festlegen (Anfang nächster Monat nach Termin)
		Date Termin = (Date) exec.getVariable("termin");
		Calendar cal = new GregorianCalendar();
		cal.setTime(Termin);
		cal.add(cal.MONTH, 1);
		cal.set(cal.DATE, cal.getActualMinimum(cal.DAY_OF_MONTH));
		String startdatum = df.format(cal.getTime());
		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage( page );
		PDFont font = PDType1Font.HELVETICA_BOLD;
		try {
		PDPageContentStream contentStream = new PDPageContentStream(document, page);
		contentStream.beginText();
		contentStream.setLeading(14.5f);
		String title = "Kreditvertrag";
		int fontSize = 16;
		float titleWidth = font.getStringWidth(title) / 1000 * fontSize;
		contentStream.setFont( font, fontSize );
		contentStream.newLineAtOffset((page.getMediaBox().getWidth() - titleWidth) / 2, 700); //Mitte der x-Achse
		contentStream.showText("Kreditvertrag");
		contentStream.newLineAtOffset(-200,-75);
		contentStream.setFont( font, 12 );
		contentStream.showText(Anrede + " " + Nachname + " hat am einen Kreditvertrag mit BKS Bank abgeschlossen");
		contentStream.newLine();
		contentStream.showText("Die Höhe des Kredites beläuft sich auf "+ hoehe +"€ Euro");
		contentStream.newLine();
		contentStream.showText("und hat eine Laufzeit von "+laufzeit+" Monaten");
		contentStream.newLine();
		contentStream.showText("Anrede Name hat sich dazu verpflichtet jeden Monat "+ rueckzahlung+"€ als Rückzahlung zu begleichen.");
		contentStream.newLine();
		contentStream.showText("Die Rückzahlung beginnt am "+ startdatum);
		contentStream.newLine();
		contentStream.showText("Die Rückzahlung ergibt sich aus dem Zinssatz von 0,9% p.M.");
		contentStream.newLineAtOffset(0,-50);
		contentStream.showText("Beide Vertragspartner geben sich mit diesen Dokument einverstanden.");
		contentStream.newLine();
		contentStream.endText();
		contentStream.setLineDashPattern(new float[]{3}, 0);
		contentStream.drawLine(55, 400, 250, 400);
		contentStream.drawLine(350, 400, 545, 400);
		contentStream.beginText();
		contentStream.newLineAtOffset(55,380);
		contentStream.showText("BKS Bank");
		contentStream.endText();
		contentStream.beginText();
		contentStream.newLineAtOffset(350,380);
		contentStream.showText(Anrede + " " + Nachname);
		contentStream.endText();
		contentStream.close();
		} catch (IOException e) {
			e.getMessage();
		}
		document.save(new File(PDFSpeichern));
		document.close();
		FileValue typedFileValue= Variables
				  .fileValue("Kreditvertrag.pdf")
				  .file(new File(PDFSpeichern))
				  .mimeType("application/pdf")
				  .encoding("UTF-8")
				  .create();
		exec.setVariable("kreditvertrag", typedFileValue);
	}
}
