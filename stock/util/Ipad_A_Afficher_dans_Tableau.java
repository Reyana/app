/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.util;

/**
 *
 * @author magency
 */
public class Ipad_A_Afficher_dans_Tableau {
    private String rfid;
    private String etat;
    
    public Ipad_A_Afficher_dans_Tableau(String rfid, String etat)
    {
        this.etat = etat;
        this.rfid = rfid;
    }

    public Ipad_A_Afficher_dans_Tableau(Ipad_A_Afficher_dans_Tableau iPad_noAdd)
    {
        this.rfid = new String(iPad_noAdd.getRfid());
        this.etat = new String(iPad_noAdd.getEtat());       
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
