package MASWF.db.object;

import java.util.Date;

public class Kredit {
	private int KreditID;
	private float Kredithoehe;
	private float rueckzahlungrate;
	private Date Startdatum;
	private long Laufzeit;
	private String Verwendungszweck;
	private int KundenID;

	/**
	 * @return the kreditID
	 */
	public int getKreditID() {
		return KreditID;
	}

	/**
	 * @param kreditID
	 *            the kreditID to set
	 */
	public void setKreditID(int kreditID) {
		KreditID = kreditID;
	}

	/**
	 * @return the kredithoehe
	 */
	public float getKredithoehe() {
		return Kredithoehe;
	}

	/**
	 * @param kredithoehe
	 *            the kredithoehe to set
	 */
	public void setKredithoehe(float kredithoehe) {
		Kredithoehe = kredithoehe;
	}

	/**
	 * @return the rueckzahlungrate
	 */
	public float getRueckzahlungrate() {
		return rueckzahlungrate;
	}

	/**
	 * @param rueckzahlungrate
	 *            the rueckzahlungrate to set
	 */
	public void setRueckzahlungrate(float rueckzahlungrate) {
		this.rueckzahlungrate = rueckzahlungrate;
	}

	/**
	 * @return the startdatum
	 */
	public Date getStartdatum() {
		return Startdatum;
	}

	/**
	 * @param startdatum
	 *            the startdatum to set
	 */
	public void setStartdatum(Date startdatum) {
		Startdatum = startdatum;
	}

	/**
	 * @return the laufzeit
	 */
	public Long getLaufzeit() {
		return Laufzeit;
	}

	/**
	 * @param long1
	 *            the laufzeit to set
	 */
	public void setLaufzeit(Long long1) {
		Laufzeit = long1;
	}

	/**
	 * @return the verwendungszweck
	 */
	public String getVerwendungszweck() {
		return Verwendungszweck;
	}

	/**
	 * @param verwendungszweck
	 *            the verwendungszweck to set
	 */
	public void setVerwendungszweck(String verwendungszweck) {
		Verwendungszweck = verwendungszweck;
	}

	/**
	 * @return the kundenID
	 */
	public int getKundenID() {
		return KundenID;
	}

	/**
	 * @param kundenID
	 *            the kundenID to set
	 */
	public void setKundenID(int kundenID) {
		KundenID = kundenID;
	}
}
