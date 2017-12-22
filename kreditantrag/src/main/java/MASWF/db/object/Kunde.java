package MASWF.db.object;

import java.sql.Date;

public class Kunde {
	private String Vorname;
	private String Nachname;
	private Date Geburtsdatum;
	private float Score;
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
}
