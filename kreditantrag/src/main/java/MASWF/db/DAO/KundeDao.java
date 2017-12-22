package MASWF.db.DAO;

import java.util.Date;

import MASWF.db.object.Kunde;

public interface KundeDao {
	public Kunde getKunde(String Vorname, String Nachname, Date Geburtsdatum);
}
