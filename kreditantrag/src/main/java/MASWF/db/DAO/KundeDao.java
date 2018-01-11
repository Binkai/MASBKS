package MASWF.db.DAO;

import java.util.Date;

import MASWF.db.object.Kunde;
/**
 * KundeDao Klasse die auf eine Datenbank mit Kundendaten zugreifen kann.
 * @author kaikuhfeld
 *
 */
public interface KundeDao {
	/**
	 * Entnimmt einen Kunden aus der Datenbank und erstellt ein Kundenobjekt
	 * @param Vorname Vorname des Kundens
	 * @param Nachname Nachname des Kundens
	 * @param Geburtsdatum Geburtsdatum des Kundens
	 * @return Kundenobjekt mit allen Information über ihn.
	 */
	public Kunde getKunde(String Vorname, String Nachname, Date Geburtsdatum);
}
