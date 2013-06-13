/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.menu.entrepot.envoi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import stock.base.Conteneur;
import stock.util.connexion.connexion_bdd;
import stock.util.sql.InstructionSql;
import stock.util.sql.SQL_function;



/**
 *
 * @author Magency
 */
public class DialogueSupprimerConteneur extends javax.swing.JDialog {
    private List<Conteneur> listConteneur = new ArrayList<Conteneur>();
    /**
     * Creates new form NewJDialog
     */
    public DialogueSupprimerConteneur(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        // Place le fenetre au centre de la fenetre parent
        this.initListes();
        this.setLocationRelativeTo(parent);
    }
    
    public DialogueSupprimerConteneur(java.awt.Frame parent, boolean modal, int longueur, int largeur) {
        this(parent, modal);
        this.setSize(new java.awt.Dimension(longueur / 2, largeur / 2));
        this.setLocationRelativeTo(parent);
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panelTitre = new javax.swing.JPanel();
        labelTitre = new javax.swing.JLabel();
        panelCentre = new javax.swing.JPanel();
        panelOP = new javax.swing.JPanel();
        cbConteneur = new javax.swing.JComboBox();
        labelConteneur = new javax.swing.JLabel();
        panelBoutons = new javax.swing.JPanel();
        btnRetour = new javax.swing.JButton();
        btnOK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Charger un conteneur");
        setMinimumSize(new java.awt.Dimension(400, 300));

        labelTitre.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelTitre.setText("Suppression d'un Conteneur: ");
        labelTitre.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        panelTitre.add(labelTitre);

        getContentPane().add(panelTitre, java.awt.BorderLayout.NORTH);

        panelCentre.setLayout(new java.awt.GridBagLayout());

        panelOP.setLayout(new java.awt.GridBagLayout());

        cbConteneur.setMinimumSize(new java.awt.Dimension(100, 27));
        cbConteneur.setPreferredSize(new java.awt.Dimension(100, 27));
        cbConteneur.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbConteneurItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        panelOP.add(cbConteneur, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        panelCentre.add(panelOP, gridBagConstraints);

        labelConteneur.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelConteneur.setText("Choisir le conteneur à supprimer :");
        labelConteneur.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        panelCentre.add(labelConteneur, new java.awt.GridBagConstraints());

        getContentPane().add(panelCentre, java.awt.BorderLayout.CENTER);

        btnRetour.setText("Retour");
        btnRetour.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRetourMouseClicked(evt);
            }
        });
        panelBoutons.add(btnRetour);

        btnOK.setText("OK");
        btnOK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOKMouseClicked(evt);
            }
        });
        panelBoutons.add(btnOK);

        getContentPane().add(panelBoutons, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRetourMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRetourMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnRetourMouseClicked

    private void btnOKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOKMouseClicked
        String contenur_a_supprimer;
        contenur_a_supprimer = this.cbConteneur.getSelectedItem().toString();
                    InstructionSql modif_op = new InstructionSql();
            modif_op.Supp_Conteneur(contenur_a_supprimer);   
             initListes();
            //Suppression de la fenetre
            this.dispose();
    }//GEN-LAST:event_btnOKMouseClicked

    private void cbConteneurItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbConteneurItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbConteneurItemStateChanged
    

    private void initListes()
    {
        int i;
        listConteneur.clear();
        connexion_bdd bdd = new connexion_bdd();
        ResultSet result;

            result = SQL_function.getdataconteneur_libre(bdd.getserver().getstatement());

        try {
            while (result.next()){  
                Conteneur conteneurs = new Conteneur(result.getString("nom_conteneur"));
                listConteneur.add(conteneurs);
                this.cbConteneur.addItem(conteneurs.getName());
            }
            bdd.closeall();
        } catch (SQLException ex) {
            System.out.println("erreur data op (dialoguechoisOPenvoi): " + ex.getMessage());
        }      
    }
    
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOK;
    private javax.swing.JButton btnRetour;
    private javax.swing.JComboBox cbConteneur;
    private javax.swing.JLabel labelConteneur;
    private javax.swing.JLabel labelTitre;
    private javax.swing.JPanel panelBoutons;
    private javax.swing.JPanel panelCentre;
    private javax.swing.JPanel panelOP;
    private javax.swing.JPanel panelTitre;
    // End of variables declaration//GEN-END:variables
}
