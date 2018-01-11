package MASWF.kreditantrag;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import MASWF.db.object.Kunde;
/**
 * Entnimmt dem Kundenobjekt den Score und speichert ihn ab.
 * @author kaikuhfeld
 *
 */
public class dbKundeScore implements JavaDelegate{

	@Override
	public void execute(DelegateExecution exec) throws Exception {
		Kunde kunde = (Kunde) exec.getVariable("Kunde");
		exec.setVariable("score", kunde.getScore());
	}

}
