/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.util;

/**
 *
 * @author Magency / Reyana
 */
public class Serveur_Ignore {
    private String rfid;
    private String etat;
    
    public Serveur_Ignore(String rfid, String etat)
    {
        this.etat = etat;
        this.rfid = rfid;
    }

    public Serveur_Ignore(Serveur_Ignore sErveur_noAdd)
    {
        this.rfid = new String(sErveur_noAdd.getRfid());
        this.etat = new String(sErveur_noAdd.getEtat());       
    }    
    
    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }
    
}
