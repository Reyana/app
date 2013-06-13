/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.base;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Magency / Reyana
 */
public class OP {
    
    private String nomOP = "";
    private Integer nbIpadsPrevu = 0;
    private Integer nbIpadsReel = 0;
    private Integer nbServeursPrevu = 0;
    private Integer nbServeursReel = 0;
    private Date dateDebut;
    private Date dateFin;
    private List<Conteneur> listConteneur = new ArrayList<Conteneur>();
    private int envoyee = 0;

    /* 
     * Constructors
     */
    public OP(String nomOP)
    {
        this.nomOP = nomOP;
    }
    
    public OP(String nomOP, Integer nbIpads, Integer nbServeurs)
    {
        this.nomOP = nomOP;
        this.nbIpadsPrevu = nbIpads;
        this.nbServeursPrevu = nbServeurs;
    }
    
    public OP(String nomOP, Integer nbIpads, Integer nbServeurs, Date dateDebut)
    {
        this.nomOP = nomOP;
        this.nbIpadsPrevu = nbIpads;
        this.nbServeursPrevu = nbServeurs;
        this.dateDebut = dateDebut;
    }
    
    public OP(String nomOP, Integer nbIpads, Integer nbServeurs, Date dateDebut, Date dateFin)
    {
        this.nomOP = nomOP;
        this.nbIpadsPrevu = nbIpads;
        this.nbServeursPrevu = nbServeurs;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }
    
        public OP(String nomOP, Date dateDebut)
    {
        this.nomOP = nomOP;
        this.dateDebut = dateDebut;
    }
    
    public OP(String nomOP, Date dateDebut, Date dateFin)
    {
        this.nomOP = nomOP;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }
    
    public OP(OP op)
    {
        this.dateDebut = op.getDateDebut() == null? null:(Date)op.getDateDebut();
        this.dateFin = op.getDateFin() == null? null:(Date)op.getDateFin().clone();
        this.listConteneur = new ArrayList<Conteneur>(op.getListConteneur());
        this.nbIpadsPrevu = new Integer(op.getNbIpadsPrevu());
        this.nbIpadsReel = new Integer(op.getNbIpadsReel());
	this.nbServeursPrevu = new Integer(op.getNbServeursPrevu());
	this.nbServeursReel = new Integer(op.getNbServeursReel());
        this.nomOP = new String(op.getNomOP()); 
   }

    public OP(String nomOP,  Integer nbIpads, Integer nbServeurs, Date dateDebut, Date dateFin,int conf_env)
    {
        this.nomOP = nomOP;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.envoyee =conf_env;
        this.nbIpadsPrevu = nbIpads;
        this.nbServeursPrevu = nbServeurs;
    }
        public OP(String nomOP, Integer nbIpads, Integer nbServeurs, Integer nbIpadreel, Integer nbServeurReel, Date dateDebut, Date dateFin,int conf_env)
    {
        this.nomOP = nomOP;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.envoyee =conf_env;
        this.nbIpadsPrevu = nbIpads;
        this.nbIpadsReel = nbIpadreel;
 	this.nbServeursPrevu = nbServeurs;
        this.nbIpadsReel = nbServeurReel;
    }


    
    public void addConteneur(Conteneur conteneur) {
        this.listConteneur.add(conteneur);
        // MAJ du nb d'iPad associés à l'OP
        this.nbIpadsReel += conteneur.getNbIpad();
	// MAJ du nb de Serveurs associés à l'OP
        this.nbServeursReel += conteneur.getNbServeur();
    }
    
    public void retirerConteneur(Conteneur conteneur) {
        this.listConteneur.remove(conteneur);
        // MAJ du nb d'iPad associés à l'OP
        this.nbIpadsReel -= conteneur.getNbIpad();
	// MAJ du nb de Serveurs associés à l'OP
        this.nbServeursReel -= conteneur.getNbServeur();
    }
    
    
    
    /*
     * Getters & setters 
     */
    public Date getDateDebut() {
        return dateDebut;
    }
    
    public String getStringDateDebut() {
        return (dateDebut == null ? "": new SimpleDateFormat("dd/MM/yy", Locale.FRENCH).format(dateDebut));
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }
    
    public String getStringDateFin() {
        return (dateFin == null ? "": new SimpleDateFormat("dd/MM/yy", Locale.FRENCH).format(dateFin));
    }
    

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    
    public String getNomOP() {
        return nomOP;
    }

    public void setNomOP(String nomOP) {
        this.nomOP = nomOP;
    }
    
    public List<Conteneur> getListConteneur() {
        return listConteneur;
    }
    
    
    public void setconfenv(int confenv) {
        this.envoyee = confenv;
    }
    
    public int getconfenv() {
        return envoyee;
    }

    /* Pour Ipad*/
    public Integer getNbIpadsPrevu() {
        return nbIpadsPrevu;
    }

    public void setNbIpadsPrevu(Integer nbIpads) {
        this.nbIpadsPrevu = nbIpads;
    }

    public Integer getNbIpadsReel() {
        return nbIpadsReel;
    }

    public void setNbIpadsReel(Integer nbIpadsReel) {
        this.nbIpadsReel = nbIpadsReel;
    }

    /* Pour Serveur*/
    public Integer getNbServeursPrevu() {
        return nbServeursPrevu;
    }

    public void setNbServeursPrevu(Integer nbServeurs) {
        this.nbServeursPrevu = nbServeurs;
    }

    public Integer getNbServeursReel() {
        return nbServeursReel;
    }

    public void setNbServeursReel(Integer nbServeursReel) {
        this.nbServeursReel = nbServeursReel;
    }

    
    
}
