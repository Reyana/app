/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.menu.entrepot.planning;

import stock.base.OP;
import stock.util.sql.InstructionSql;
import java.util.List;
import javax.swing.JTable;

    
    /**
 *
 * @author Magency
 */
public class DialogueSuppOP extends javax.swing.JDialog {
    private final FenetreEntrepotPlanning fenMere;
    private final ModelPlanning modelP;
    private final int indexSelectedOP;
    private final List<OP> listOP;
    private final OP selectedOP;
    private String delete_data="";

    /**
     * Creates new form NewJDialog
     */
    public DialogueSuppOP(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        // Place le fenetre au centre de la fenetre parent
        this.setLocationRelativeTo(parent);
        
        // Récupère le modèle de la JTable
        this.fenMere = (FenetreEntrepotPlanning)parent;
        JTable tablePlanning = fenMere.getTablEntrepotEnvoi();
        this.modelP = (ModelPlanning)tablePlanning.getModel();
        this.listOP = modelP.getListOP();

        indexSelectedOP = tablePlanning.convertRowIndexToModel(tablePlanning.getSelectedRow());
        selectedOP = listOP.get(indexSelectedOP);
        
        this.txtNom.setText(this.selectedOP.getNomOP());
        this.txtNb.setText(String.valueOf(this.selectedOP.getNbIpadsPrevu()));
        this.txtDateDebut.setText(this.selectedOP.getStringDateDebut());
        this.txtDateFin.setText(this.selectedOP.getStringDateFin());
        
        delete_data =this.selectedOP.getNomOP();
    }
    
    public DialogueSuppOP(java.awt.Frame parent, boolean modal, int longueur, int largeur) {
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

        labelNomFenetre = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnRetour = new javax.swing.JButton();
        btnOK = new javax.swing.JButton();
        panelCentre = new javax.swing.JPanel();
        labelNom = new javax.swing.JLabel();
        labelNb = new javax.swing.JLabel();
        labelDateDebut = new javax.swing.JLabel();
        labelDateFin = new javax.swing.JLabel();
        txtNom = new javax.swing.JLabel();
        txtNb = new javax.swing.JLabel();
        txtDateDebut = new javax.swing.JLabel();
        txtDateFin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ajout d'une opération");
        setMinimumSize(new java.awt.Dimension(400, 300));

        labelNomFenetre.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        labelNomFenetre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNomFenetre.setText("Suppression d'une OP");
        getContentPane().add(labelNomFenetre, java.awt.BorderLayout.NORTH);

        btnRetour.setText("Non");
        btnRetour.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRetourMouseClicked(evt);
            }
        });
        jPanel1.add(btnRetour);

        btnOK.setText("Oui");
        btnOK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOKMouseClicked(evt);
            }
        });
        jPanel1.add(btnOK);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        java.awt.GridBagLayout panelCentreLayout = new java.awt.GridBagLayout();
        panelCentreLayout.columnWidths = new int[] {0, 0, 0};
        panelCentreLayout.rowHeights = new int[] {0, 20, 0, 20, 0, 20, 0};
        panelCentre.setLayout(panelCentreLayout);

        labelNom.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelNom.setText("Nom de l'OP :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        panelCentre.add(labelNom, gridBagConstraints);

        labelNb.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelNb.setText("Nombre d'iPads prévus :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        panelCentre.add(labelNb, gridBagConstraints);

        labelDateDebut.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelDateDebut.setText("Date de début de l'OP :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        panelCentre.add(labelDateDebut, gridBagConstraints);

        labelDateFin.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelDateFin.setText("Date de fin de l'OP :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        panelCentre.add(labelDateFin, gridBagConstraints);

        txtNom.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        txtNom.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        panelCentre.add(txtNom, gridBagConstraints);

        txtNb.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        txtNb.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        panelCentre.add(txtNb, gridBagConstraints);

        txtDateDebut.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        txtDateDebut.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        panelCentre.add(txtDateDebut, gridBagConstraints);

        txtDateFin.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        txtDateFin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        panelCentre.add(txtDateFin, gridBagConstraints);

        getContentPane().add(panelCentre, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRetourMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRetourMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnRetourMouseClicked

    private void btnOKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOKMouseClicked
        //Ajout de la nouvelle OP dans la JTable
        modelP.removeOP(indexSelectedOP);


        //Suppression de l'OP dans la BDD?
        //delete_data = "mp";
        System.out.println(delete_data);
         InstructionSql sup_op = new InstructionSql();
            sup_op.Delete_Operation(delete_data);           

        //Suppression de la fenetre
        this.dispose();
    }//GEN-LAST:event_btnOKMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOK;
    private javax.swing.JButton btnRetour;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelDateDebut;
    private javax.swing.JLabel labelDateFin;
    private javax.swing.JLabel labelNb;
    private javax.swing.JLabel labelNom;
    private javax.swing.JLabel labelNomFenetre;
    private javax.swing.JPanel panelCentre;
    private javax.swing.JLabel txtDateDebut;
    private javax.swing.JLabel txtDateFin;
    private javax.swing.JLabel txtNb;
    private javax.swing.JLabel txtNom;
    // End of variables declaration//GEN-END:variables
}
