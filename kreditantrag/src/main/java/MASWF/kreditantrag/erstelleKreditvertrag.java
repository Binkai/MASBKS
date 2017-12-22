package MASWF.kreditantrag;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class erstelleKreditvertrag implements JavaDelegate {
	private final String PDFLocation = "C:/Users/gelbe/git/MASBKS/kreditantrag/src/main/resources/formtest.pdf";
	private final String PDFSpeichern = "C:/Users/gelbe/Desktop/formfilled.pdf";
	@Override
	public void execute(DelegateExecution exec) throws Exception {
		PDDocument pdfdoc = PDDocument.load(new File(PDFLocation));
		PDDocumentCatalog cat = pdfdoc.getDocumentCatalog();
		PDAcroForm acro = cat.getAcroForm();
		if (acro != null) {
			PDField field = (PDField) acro.getField("vorname");
			field.setValue(exec.getVariable("vorname").toString());
		}
		pdfdoc.save(new File(PDFSpeichern));
		pdfdoc.close();
	}

}
