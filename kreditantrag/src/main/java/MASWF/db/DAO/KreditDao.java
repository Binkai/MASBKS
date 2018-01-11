package MASWF.db.DAO;

import MASWF.db.object.Kredit;
/**
 * KreditDao Klasse die auf eine Datenbank mit Kreditdaten zugreift
 * @author kaikuhfeld
 *
 */
public interface KreditDao {
/**
 * Fügt einen bereits erstellten Kredit in die Datenbank ein
 * @param kredit ein Kreditobjekt
 * @return boolean ob Kredit erfolgreich hinzugefügt wurde.
 */
public boolean insertKredit(Kredit kredit);
}
