package MASWF.kreditantrag;

import java.util.Date;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import MASWF.db.DAO.KreditDaoImpl;
import MASWF.db.object.Kredit;
import MASWF.db.object.Kunde;

public class dbKreditInsert implements JavaDelegate {

	@Override
	public void execute(DelegateExecution exec) throws Exception {
		Kredit kredit = new Kredit();
		Kunde kunde = (Kunde) exec.getVariable("Kunde");

		kredit.setKredithoehe((Long) exec.getVariable("wunschhoeheKredit"));
		kredit.setKundenID(kunde.getKundeID());
		kredit.setLaufzeit((Long) exec.getVariable("wunschlaufzeitKredit"));
		kredit.setRueckzahlungrate((Float) exec.getVariable("rueckzahlungsrate"));
		kredit.setStartdatum((Date) exec.getVariable("termin"));
		kredit.setVerwendungszweck(exec.getVariable("verwendungszweck").toString());
		KreditDaoImpl kdao = new KreditDaoImpl();
		if(kdao.insertKredit(kredit) == true){
			System.out.println("Erfolgreich hinzugefügt");
		} else {
			System.out.println("ACHTUNG FEHLER DB");
		}
	}

}
