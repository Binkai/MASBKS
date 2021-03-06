package MASWF.db.object;

import java.sql.Date;
/**
 * Kundenklasse die alle Information über die Kunden enthält.
 * @author kaikuhfeld
 *
 */
public class Kunde {
	private int KundeID;
	private String Vorname;
	private String Nachname;
	private Date Geburtsdatum;
	private float Score;
	private float monateinkommen;
	/**
	 * @return the vorname
	 */
	public String getVorname() {
		return Vorname;
	}
	/**
	 * @param vorname the vorname to set
	 */
	public void setVorname(String vorname) {
		Vorname = vorname;
	}
	/**
	 * @return the nachname
	 */
	public String getNachname() {
		return Nachname;
	}
	/**
	 * @param nachname the nachname to set
	 */
	public void setNachname(String nachname) {
		Nachname = nachname;
	}
	/**
	 * @return the geburtsdatum
	 */
	public Date getGeburtsdatum() {
		return Geburtsdatum;
	}
	/**
	 * @param geburtsdatum the geburtsdatum to set
	 */
	public void setGeburtsdatum(Date geburtsdatum) {
		Geburtsdatum = geburtsdatum;
	}
	public float getScore() {
		return Score;
	}
	public void setScore(float score) {
		Score = score;
	}
	public int getKundeID() {
		return KundeID;
	}
	public void setKundeID(int kundeID) {
		KundeID = kundeID;
	}
	public float getMonateinkommen() {
		return monateinkommen;
	}
	public void setMonateinkommen(float monateinkommen) {
		this.monateinkommen = monateinkommen;
	}
}
