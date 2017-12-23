package MASWF.kreditantrag;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class erstelleKreditvertrag implements JavaDelegate {
	private final String PDFLocation = "C:/Users/gelbe/git/MASBKS/kreditantrag/src/main/resources/Kreditvertrag.pdf";
	private final String PDFSpeichern = "C:/Users/gelbe/Desktop/formfilled.pdf";
	private PDDocument pdfdoc;

	@Override
	public void execute(DelegateExecution exec) throws Exception {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		pdfdoc = PDDocument.load(new File(PDFLocation));
		PDDocumentCatalog cat = pdfdoc.getDocumentCatalog();
		PDAcroForm acro = cat.getAcroForm();
		if (acro != null) {
			acro.getField("anrede").setValue(exec.getVariable("anrede").toString());
			acro.getField("nachname").setValue(exec.getVariable("nachname").toString());

			acro.getField("termin").setValue(df.format((Date) exec.getVariable("termin")));
			acro.getField("kredithoehe").setValue(exec.getVariable("wunschhoeheKredit").toString());

			acro.getField("laufzeit").setValue(exec.getVariable("wunschlaufzeitKredit").toString());

			acro.getField("rueckzahlung").setValue(exec.getVariable("rueckzahlungsrate").toString());
			// TexField...? mit formatf arbeiten...?
		}
		pdfdoc.save(new File(PDFSpeichern));
		pdfdoc.close();
		exec.setVariable("kreditvertrag", new File(PDFSpeichern));
	}
	// private void setField(String fieldname, String Inhalt){
	// PDDocumentCatalog cat = pdfdoc.getDocumentCatalog();
	// PDAcroForm acro = cat.getAcroForm();
	// if (acro != null) {
	// PDField field = (PDField) acro.getField(fieldname);
	// try {
	// field.setValue(Inhalt);
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	//
	// }
}
