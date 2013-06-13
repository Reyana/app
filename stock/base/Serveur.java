/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.base;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Magency / Reyana
 */
public class Serveur {

    private int dbNum;
    private final String numSerie;
    private final String udid;
    private String rfid;
    private String etiquette = "";
    private Date dateAchat;
    private String dureeGarantie = "1 an";
    private String nomOp = "";
    private String etat="";
    
    
    public Serveur(String numSerie, String udid, String rfid)
    {
        this.numSerie = numSerie;
        this.udid = udid;
        this.rfid = rfid;
    }
    
        public Serveur(String numSerie, String udid, String rfid,String etiquette)
    {
        this(numSerie, udid, rfid);
        this.etiquette = etiquette;
    }
       
    public Serveur(int dbNum,String numSerie, String udid, String rfid,String etiquette)
    {
        this(dbNum,numSerie, udid, rfid);
        this.etiquette = etiquette;
    }
        
    public Serveur(int dbNum,String numSerie, String udid, String rfid,String etiquette, String etat)
    {
        this(dbNum,numSerie, udid, rfid);
        this.etiquette = etiquette;
        this.etat = etat;
    } 
    
    public Serveur(int dbNum, String numSerie, String udid, String rfid)
    {
        this(numSerie, udid, rfid);
        this.dbNum = dbNum;
    }
    
    public Serveur(String numSerie, String udid, String rfid, 
                String etiquette, Date dateAchat, String dureeGarantie)
    {
        this(numSerie, udid, rfid);
        this.etiquette = etiquette;
        this.dateAchat = dateAchat;
        this.dureeGarantie = dureeGarantie;
    }
    
    public Serveur(int dbNum, String numSerie, String udid, String rfid, 
                String etiquette, Date dateAchat, String dureeGarantie)
    {
        this(dbNum, numSerie, udid, rfid);
        this.etiquette = etiquette;
        this.dateAchat = dateAchat;
        this.dureeGarantie = dureeGarantie;
    }
    
    public Serveur(Serveur sErveur)
    {
        this.dbNum = new Integer(sErveur.getDbNum());
        this.numSerie = new String(sErveur.getNumSerie());
        this.udid = new String(sErveur.getUdid());
        this.rfid = new String(sErveur.getRfid());
        this.etiquette = new String(sErveur.getEtiquette());
        this.dateAchat = sErveur.getDateAchat() == null? null:(Date)sErveur.getDateAchat().clone();
        this.dureeGarantie = new String(sErveur.getDureeGarantie());
        this.nomOp = new String(sErveur.getNomOp());
        
    }
    


    
    
    /*Getters & setters */
    public Date getDateAchat() {
        return dateAchat;
    }
    
    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }
    
    public String getStringDateAchat() {
        return (dateAchat == null ? "": new SimpleDateFormat("dd/MM/yy", Locale.FRENCH).format(dateAchat));
    }
    

    public int getDbNum() {
        return dbNum;
    }
    
    public void setDbNum(int dbNum) {
        this.dbNum = dbNum;
    }
    

    public String getDureeGarantie() {
        return dureeGarantie;
    }
    
    public void setDureeGarantie(String dureeGarantie) {
        this.dureeGarantie = dureeGarantie;
    }
    
    
    public String getEtiquette() {
        return etiquette;
    }
    
    public void setEtiquette(String etiquette) {
        this.etiquette = etiquette;
    }

    
    public String getNomOp() {
        return nomOp;
    }
    
    public void setNomOp(String nomOp) {
        this.nomOp = nomOp;
    }

    
    public String getNumSerie() {
        return numSerie;
    }
    

    public String getRfid() {
        return rfid;
    }
    
    public void setRfid(String rfid) {
        this.rfid = rfid;
    }
    

    public String getUdid() {
        return udid;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
      @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Serveur))return false;
        // Test de chacune des propriétés uniques
        Serveur serveur = (Serveur)obj;
        if (serveur.getDbNum() != this.dbNum) return false;
        if (!serveur.getNumSerie().equals(this.numSerie)) return false;
        if (!serveur.getUdid().equals(this.udid)) return false;
        if (!serveur.getRfid().equals(this.rfid)) return false;
        return true;
    }
    
    
    
}

