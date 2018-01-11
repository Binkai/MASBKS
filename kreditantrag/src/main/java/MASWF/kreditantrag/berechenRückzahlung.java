package MASWF.kreditantrag;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import MASWF.db.object.Kunde;
/**
 * Berechnet die Rückzahlung mithilfe der Laufzeit und Kredithoehe sowie des monatl. Einkommen	
 * @author kaikuhfeld
 *
 */
public class berechenRückzahlung implements JavaDelegate {

	@Override
	public void execute(DelegateExecution exec) throws Exception {
		Float rate;
		Long kredithoehe = (Long) exec.getVariable("wunschhoeheKredit");
		Long laufzeit = (Long) exec.getVariable("wunschlaufzeitKredit");
		Float monateinkommen = ((Kunde) exec.getVariable("Kunde")).getMonateinkommen();
		do {
			rate = (float) (kredithoehe / laufzeit) + (float) (kredithoehe * 0.009);
			laufzeit++;
		} while (rate > monateinkommen / 2);
		laufzeit--;
		exec.setVariable("rueckzahlungsrate", rate);
		exec.setVariable("wunschlaufzeitKredit", laufzeit);
	}

}
