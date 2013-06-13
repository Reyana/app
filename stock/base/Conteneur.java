/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.base;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Magency / Reyana
 */
public class Conteneur {
    private String name;// name de ??
    private List<Ipad> listIpad = new ArrayList<Ipad>();
    private List<Serveur> listServeur = new ArrayList<Serveur>();
    private int nbIpad = 0;
     private int nbServeur = 0;

    public Conteneur(String name)
    {
        this.name = name;
    }
    
    public Conteneur(Conteneur conteneur)
    {
        this.name = new String(conteneur.getName());
        this.nbIpad = new Integer(conteneur.getNbIpad());
        this.nbServeur = new Integer(conteneur.getNbServeur());
        int i;
        this.listIpad = new ArrayList<Ipad>();
        this.listServeur = new ArrayList<Serveur>();
        for(i = 0; i < conteneur.getListIpad().size(); i++)
        {
            this.listIpad.add(new Ipad(conteneur.getListIpad().get(i)));
        }
        for(i = 0; i < conteneur.getListServeur().size(); i++)
        {
            this.listServeur.add(new Serveur(conteneur.getListServeur().get(i)));
        }
    }
    
    
    public void ajouterIpad(Ipad newIpad)
    {
        if(!this.listIpad.contains(newIpad))
        {
            this.listIpad.add(newIpad);
            this.nbIpad++;
        }
    }
    
    public void retirerIpad(Ipad newIpad)
    {
        if(this.listIpad.contains(newIpad))
        {
            this.listIpad.remove(newIpad);
            this.nbIpad--;
        }
    }
    
      public void ajouterServeur(Serveur newServeur)
    {
        if(!this.listServeur.contains(newServeur))
        {
            this.listServeur.add(newServeur);
            this.nbServeur++;
        }
    }
    
    public void retirerServeur(Serveur newServeur)
    {
        if(this.listServeur.contains(newServeur))
        {
            this.listServeur.remove(newServeur);
            this.nbServeur--;
        }
    }
    
    /* Getters & setters*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
	
    /* Pour Ipad*/
    public List<Ipad> getListIpad() {
        return listIpad;
    }

    public int getNbIpad() {
        return nbIpad;
    }

    public void setNbIpad(int nbIpad) {
        this.nbIpad = nbIpad;
    }
    
    /* Pour Serveur*/
    public List<Serveur> getListServeur() {
        return listServeur;
    }

    public int getNbServeur() {
        return nbServeur;
    }

    public void setNbServeur(int nbServeur) {
        this.nbServeur = nbServeur;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Conteneur))return false;
        // Test de chacune des propriétés 
        Conteneur conteneur = (Conteneur)obj;
        if (!conteneur.getName().equals(this.name)) return false;
        /*if (conteneur.getNbIpad() != this.nbIpad) return false;
        if (!conteneur.getListIpad().equals(this.listIpad)) return false;*/
        return true;
    }
}
