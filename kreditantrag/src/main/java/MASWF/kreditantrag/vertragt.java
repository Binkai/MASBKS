package MASWF.kreditantrag;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class vertragt {
	private final static String PDFSpeichern = System.getProperty("user.dir")+"/src/main/resources/KreditvertragT.pdf";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage( page );
		// Create a new font object selecting one of the PDF base fonts
		PDFont font = PDType1Font.HELVETICA_BOLD;

		// Start a new content stream which will "hold" the to be created content
		

		// Define a text content stream using the selected font, moving the cursor and drawing the text "Hello World"
		try {
		PDPageContentStream contentStream = new PDPageContentStream(document, page);
		contentStream.beginText();
		contentStream.setLeading(14.5f);
		String title = "Kreditvertrag";
		int fontSize = 16; // Or whatever font size you want.
		float titleWidth = font.getStringWidth(title) / 1000 * fontSize;
		contentStream.setFont( font, fontSize );
		contentStream.newLineAtOffset((page.getMediaBox().getWidth() - titleWidth) / 2, 700);
		contentStream.showText("Kreditvertrag");
		contentStream.newLineAtOffset(-200,-75);
		contentStream.setFont( font, 12 );
		contentStream.showText("hat am einen Kreditvertrag mit BKS Bank abgeschlossen");
		contentStream.newLine();
		contentStream.showText("Die Höhe des Kredites beläuft sich auf ...");
		contentStream.newLine();
		contentStream.showText("und hat eine Laufzeit von ... Monaten");
		contentStream.newLine();
		contentStream.showText("Anrede Name hat sich dazu verpflichtet jeden Monat ... als Rückzahlung zu begleichen.");
		contentStream.newLine();
		contentStream.showText("Die Rückzahlung beginnt am termin + 1 Monat");
		contentStream.newLine();
		contentStream.showText("Die Rückzahlung ergibt sich aus dem Zinssatz von 0,9% p.M. (RECHNUNG HIER REIN)");
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
		contentStream.showText("TESTlol");
		contentStream.endText();
		// Make sure that the content stream is closed:
		contentStream.close();
		document.save(PDFSpeichern);
		document.close();
		} catch (IOException e) {
			e.getMessage();
		}

		// Save the results and ensure that the document is properly closed:
		
	}

}
