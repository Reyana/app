/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.util;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Magency
 */
public class ColorTableRenderer extends DefaultTableCellRenderer{

    public ColorTableRenderer() {
        super();
    }

    @Override
    public Component getTableCellRendererComponent (JTable table, Object value, 
                                           boolean isSelected, boolean hasFocus,
                                           int row, int column) 
    {
	// Appel de la méthode de la classe mère  
	Component comp = super.getTableCellRendererComponent(
                                table, value, isSelected, hasFocus, row, column);

        DefaultTableModel dtm = (DefaultTableModel)table.getModel();
        String txtEtat = (String)dtm.getValueAt(table.convertRowIndexToModel(row), 4);
        
        if(txtEtat.equalsIgnoreCase("En trop"))
        {
            comp.setBackground(Color.yellow.brighter().brighter().brighter());
        }
        else if(txtEtat.equalsIgnoreCase("Manquant"))
        {
            comp.setBackground(Color.red.brighter().brighter().brighter());
        }
        else
        {
            comp.setBackground(Color.white);
        }   
        
        return comp;
                
    }
}
