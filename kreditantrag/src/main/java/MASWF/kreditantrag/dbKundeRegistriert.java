package MASWF.kreditantrag;

import java.util.Date;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import MASWF.db.DAO.KundeDaoImpl;
import MASWF.db.object.Kunde;
/**
 * nach Eingang des Kreditantrags wird mit Hilfe der KundenDao geprüft ob 
 * der Kunde bereits registriert ist.
 * @author kaikuhfeld
 *
 */

public class dbKundeRegistriert implements JavaDelegate {

	@Override
	public void execute(DelegateExecution exec) throws Exception {
		KundeDaoImpl kdao = new KundeDaoImpl();
		String Vorname = exec.getVariable("vorname").toString();
		String Nachname = exec.getVariable("nachname").toString();
		Date Geburtsdatum =  (Date) exec.getVariable("geburtsdatum");
		Kunde kunde = kdao.getKunde(Vorname, Nachname, Geburtsdatum);
		if(kunde != null){
			exec.setVariable("istKunde", true);
			exec.setVariable("Kunde", kunde);
		}else{
			exec.setVariable("istKunde", false);
		}
		
	}
}
