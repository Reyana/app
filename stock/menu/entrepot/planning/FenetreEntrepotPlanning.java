/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.menu.entrepot.planning;

import javax.swing.JTable;
import stock.menu.FenetreMenuPrincipal;
import stock.menu.entrepot.FenetreMenuEntrepot;
import stock.util.sql.SQL_function;


/**
 *
 * @author Magency
 */
public class FenetreEntrepotPlanning extends javax.swing.JFrame {

    /**
     * Creates new form FenetreEntrepotReception
     */
    public FenetreEntrepotPlanning() {
        initComponents();
    }
    
    public FenetreEntrepotPlanning(int longueur, int largeur) {
        initComponents();
        this.setSize(new java.awt.Dimension(longueur, largeur));
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

        btnRetour = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        jScrollPane = new javax.swing.JScrollPane();
        tablEntrepotPlanning = new javax.swing.JTable();
        panelBoutons = new javax.swing.JPanel();
        btnAjout = new javax.swing.JButton();
        btnModifier = new javax.swing.JButton();
        btnSupprimer = new javax.swing.JButton();
        labelNomFenetre = new javax.swing.JLabel();
        panelTexte = new javax.swing.JPanel();
        txtBas1 = new javax.swing.JLabel();
        dtDebut = new com.toedter.calendar.JDateChooser();
        txtBas2 = new javax.swing.JLabel();
        dtFin = new com.toedter.calendar.JDateChooser();
        txtBas3 = new javax.swing.JLabel();
        txtNbiPad = new javax.swing.JLabel();
        txtBas4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        btnRetour.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stock/images/Bouton Retour.png"))); // NOI18N
        btnRetour.setToolTipText("Retour");
        btnRetour.setBorderPainted(false);
        btnRetour.setContentAreaFilled(false);
        btnRetour.setMinimumSize(new java.awt.Dimension(32, 32));
        btnRetour.setPreferredSize(new java.awt.Dimension(32, 32));
        btnRetour.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRetourMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 0);
        getContentPane().add(btnRetour, gridBagConstraints);

        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stock/images/Bouton Home.PNG"))); // NOI18N
        btnHome.setToolTipText("Menu principal");
        btnHome.setBorderPainted(false);
        btnHome.setContentAreaFilled(false);
        btnHome.setMaximumSize(new java.awt.Dimension(32, 32));
        btnHome.setMinimumSize(new java.awt.Dimension(32, 32));
        btnHome.setPreferredSize(new java.awt.Dimension(32, 32));
        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHomeMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        getContentPane().add(btnHome, gridBagConstraints);

        jScrollPane.setMinimumSize(new java.awt.Dimension(512, 512));

        tablEntrepotPlanning.setAutoCreateRowSorter(true);
        tablEntrepotPlanning.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tablEntrepotPlanning.setModel(new stock.menu.entrepot.planning.ModelPlanning());
        tablEntrepotPlanning.setGridColor(new java.awt.Color(102, 102, 102));
        tablEntrepotPlanning.setRowHeight(30);
        tablEntrepotPlanning.setSelectionBackground(new java.awt.Color(153, 204, 255));
        tablEntrepotPlanning.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tablEntrepotPlanning.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablEntrepotPlanningMousePressed(evt);
            }
        });
        jScrollPane.setViewportView(tablEntrepotPlanning);
        this.getTablEntrepotEnvoi().getColumnModel().getColumn(3).setPreferredWidth(25);
        this.getTablEntrepotEnvoi().getColumnModel().getColumn(4).setPreferredWidth(30);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jScrollPane, gridBagConstraints);

        panelBoutons.setLayout(new java.awt.GridBagLayout());

        btnAjout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stock/images/Bouton Ajout OP.PNG"))); // NOI18N
        btnAjout.setToolTipText("Creation d'une opeation dans la base de donnees");
        btnAjout.setBorderPainted(false);
        btnAjout.setContentAreaFilled(false);
        btnAjout.setFocusPainted(false);
        btnAjout.setMinimumSize(new java.awt.Dimension(128, 128));
        btnAjout.setPreferredSize(new java.awt.Dimension(128, 128));
        btnAjout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAjoutMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.33;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        panelBoutons.add(btnAjout, gridBagConstraints);

        btnModifier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stock/images/Bouton Modifier.PNG"))); // NOI18N
        btnModifier.setToolTipText("Modification d'une operation de la base de donnees");
        btnModifier.setBorderPainted(false);
        btnModifier.setContentAreaFilled(false);
        btnModifier.setEnabled(false);
        btnModifier.setMinimumSize(new java.awt.Dimension(128, 128));
        btnModifier.setPreferredSize(new java.awt.Dimension(128, 128));
        btnModifier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnModifierMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.33;
        gridBagConstraints.insets = new java.awt.Insets(20, 10, 20, 10);
        panelBoutons.add(btnModifier, gridBagConstraints);

        btnSupprimer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stock/images/Bouton Supprimer.PNG"))); // NOI18N
        btnSupprimer.setToolTipText("Suppression d'une operation de la base de donnees");
        btnSupprimer.setBorderPainted(false);
        btnSupprimer.setContentAreaFilled(false);
        btnSupprimer.setEnabled(false);
        btnSupprimer.setMinimumSize(new java.awt.Dimension(128, 128));
        btnSupprimer.setPreferredSize(new java.awt.Dimension(128, 128));
        btnSupprimer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSupprimerMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.33;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        panelBoutons.add(btnSupprimer, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(panelBoutons, gridBagConstraints);

        labelNomFenetre.setFont(new java.awt.Font("Lucida Grande", 1, 36)); // NOI18N
        labelNomFenetre.setForeground(new java.awt.Color(51, 51, 51));
        labelNomFenetre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNomFenetre.setText("Entrepôt : Planning");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        getContentPane().add(labelNomFenetre, gridBagConstraints);

        panelTexte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                panelTexteKeyTyped(evt);
            }
        });

        txtBas1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        txtBas1.setText("Entre le");
        txtBas1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        panelTexte.add(txtBas1);

        dtDebut.setDate(java.util.Calendar.getInstance().getTime());
        dtDebut.setDateFormatString("dd/MM/yy");
        dtDebut.setMinimumSize(new java.awt.Dimension(150, 28));
        dtDebut.setPreferredSize(new java.awt.Dimension(150, 28));
        panelTexte.add(dtDebut);

        txtBas2.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        txtBas2.setText("et le ");
        txtBas2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        panelTexte.add(txtBas2);

        dtFin.setDate(java.util.Calendar.getInstance().getTime());
        dtFin.setDateFormatString("dd/MM/yy");
        dtFin.setMinimumSize(new java.awt.Dimension(150, 28));
        dtFin.setPreferredSize(new java.awt.Dimension(150, 28));
        panelTexte.add(dtFin);

        txtBas3.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        txtBas3.setText(": ");
        txtBas3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        panelTexte.add(txtBas3);

        txtNbiPad.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        txtNbiPad.setText("_");
        txtNbiPad.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        panelTexte.add(txtNbiPad);

        txtBas4.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        txtBas4.setText("iPad(s) disponibles");
        txtBas4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        panelTexte.add(txtBas4);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 20, 0);
        getContentPane().add(panelTexte, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRetourMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRetourMouseClicked
        FenetreMenuEntrepot fMenuEntrepot = new FenetreMenuEntrepot(this.getWidth(), this.getHeight());
        fMenuEntrepot.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRetourMouseClicked

    private void btnHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseClicked
        FenetreMenuPrincipal fMenuPrincipal = new FenetreMenuPrincipal(this.getWidth(), this.getHeight());
        fMenuPrincipal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHomeMouseClicked

    private void btnAjoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAjoutMouseClicked
       DialogueAjoutOP dNewOP = new DialogueAjoutOP(this, true, this.getWidth(), this.getHeight());
       dNewOP.setVisible(true);   
    }//GEN-LAST:event_btnAjoutMouseClicked

    private void btnModifierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModifierMouseClicked
        if(this.btnModifier.isEnabled())
        {
            DialogueModifOP dModifOP = new DialogueModifOP(this, true, this.getWidth(), this.getHeight());
            dModifOP.setVisible(true);
        }           
    }//GEN-LAST:event_btnModifierMouseClicked

    private void btnSupprimerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSupprimerMouseClicked
        if(this.btnSupprimer.isEnabled())
        {
            DialogueSuppOP dModifOP = new DialogueSuppOP(this, true, this.getWidth(), this.getHeight());
            dModifOP.setVisible(true);
        }  
    }//GEN-LAST:event_btnSupprimerMouseClicked

    private void tablEntrepotPlanningMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablEntrepotPlanningMousePressed
        this.btnModifier.setEnabled(true);
        this.btnSupprimer.setEnabled(true);
    }//GEN-LAST:event_tablEntrepotPlanningMousePressed

    private void panelTexteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_panelTexteKeyTyped
        // TODO add your handling code here:
         rafraichissement();
    }//GEN-LAST:event_panelTexteKeyTyped

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        // TODO add your handling code here:
        rafraichissement();
    }//GEN-LAST:event_formMouseMoved

    private void rafraichissement(){
        try {
            if (dtDebut.getDate() != null){
                if (dtFin.getDate() == null || dtDebut.getDate().after(dtFin.getDate())){
                    dtFin.setDate(dtDebut.getDate());
                }
                this.txtNbiPad.setText("" + SQL_function.actualisation_nombre_ipad_libre(this.dtDebut.getDate(),this.dtFin.getDate()));
            }
        } catch ( Exception ex) {
            System.out.println(" erreur recuperation nombre ipads libres: " + ex.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAjout;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnModifier;
    private javax.swing.JButton btnRetour;
    private javax.swing.JButton btnSupprimer;
    private com.toedter.calendar.JDateChooser dtDebut;
    private com.toedter.calendar.JDateChooser dtFin;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JLabel labelNomFenetre;
    private javax.swing.JPanel panelBoutons;
    private javax.swing.JPanel panelTexte;
    private javax.swing.JTable tablEntrepotPlanning;
    private javax.swing.JLabel txtBas1;
    private javax.swing.JLabel txtBas2;
    private javax.swing.JLabel txtBas3;
    private javax.swing.JLabel txtBas4;
    private javax.swing.JLabel txtNbiPad;
    // End of variables declaration//GEN-END:variables

    public JTable getTablEntrepotEnvoi() {
        return tablEntrepotPlanning;
    }
}