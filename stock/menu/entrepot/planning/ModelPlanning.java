/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.menu.entrepot.planning;

import stock.util.sql.SQL_function;
import stock.util.connexion.connexion_bdd;
import stock.base.OP;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Magency
 */

public class ModelPlanning extends AbstractTableModel {

    private  List<OP> listOP = new ArrayList<OP>();
    private final String[] entetes = {"OP", "iPads prévus", "iPads envoyés", "Date envoi", "Date retour"};

    public ModelPlanning(){
        super();
        try 
        {
            //Remplir listOP avec la BDD
            //listOP.add( /*OP √† rajouter*/);
            boolean baseinformation = this.getbaseinformation();

            
            
        }
        catch (Exception e)
        {
            System.out.println("Date au mauvais format");
        }
    }

    @Override
    public int getRowCount() {
        return listOP.size();
    }

    @Override
    public int getColumnCount() {
        return entetes.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return listOP.get(rowIndex).getNomOP();
            case 1:
                return listOP.get(rowIndex).getNbIpadsPrevu();
            case 2:
                return listOP.get(rowIndex).getNbIpadsReel();
            case 3:
                return listOP.get(rowIndex).getStringDateDebut();
            case 4:
                return listOP.get(rowIndex).getStringDateFin();
            default:
                
                return null; //Ne devrait jamais arriver
        }
    }

    public void addOP(OP newOP) {
        listOP.add(newOP);
        fireTableRowsInserted(listOP.size() -1, listOP.size() -1);
    }

    public void removeOP(int rowIndex) {
        listOP.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    public boolean getbaseinformation() throws SQLException{
        listOP.clear();
        connexion_bdd bdd = new connexion_bdd();
        ResultSet result = SQL_function.getdataop(bdd.getserver().getstatement());

        while(result.next()){
            
            OP mon_op = new OP(result.getString("nom_op"),
                               result.getInt("nombre_ipads"),
                               result.getInt("nb_ipads_env"),
                               result.getDate("date_debut"),
                               result.getDate("date_fin"),
                               result.getInt("confirmation_envoi"));
            this.addOP(mon_op);
        }
        bdd.closeall();
        return true; 
        
    }

    
    /* Getters & Setters */
    public List<OP> getListOP() {
        return listOP;
    }
}
