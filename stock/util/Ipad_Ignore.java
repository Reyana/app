/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.util;

/**
 *
 * @author magency
 */
public class Ipad_Ignore {
    private String rfid;
    private String etat;
    
    public Ipad_Ignore(String rfid, String etat)
    {
        this.etat = etat;
        this.rfid = rfid;
    }

    public Ipad_Ignore(Ipad_Ignore iPad_noAdd)
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
