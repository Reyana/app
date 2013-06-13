/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.menu.op.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import stock.base.Ipad;
import stock.base.OP;
import stock.menu.FenetreMenuPrincipal;
import stock.menu.op.FenetreMenuOP;
import stock.util.Gestion_Duree_Garantie;
import stock.util.connexion.connexion_bdd;
import stock.util.sql.SQL_function;

/**
 *
 * @author Magency
 */
public class FenetreOPListeiPads extends javax.swing.JFrame {
    private List<Ipad> listIpad = new ArrayList<Ipad>();
    private OP op_courante = null;
    private int etatAffiche = 1;        // 0 = Tous, 1 = Fonctionnels, 2 = Manquants, 3 = Casses
    /**
     * Creates new form FenetreEntrepotReception
     */
    public FenetreOPListeiPads(OP op) {
        this.op_courante = op;
        initComponents();
        refreshJTable();
        init_textOP();
    }

    public FenetreOPListeiPads(int longueur, int largeur,OP op) {
        this(op);
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

        bgEtat = new javax.swing.ButtonGroup();
        btnRetour = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        panelTitre = new javax.swing.JPanel();
        labelNomFenetre = new javax.swing.JLabel();
        txtNomOP = new javax.swing.JLabel();
        jScrollPane = new javax.swing.JScrollPane();
        tabIpads = new javax.swing.JTable();
        panelBoutons = new javax.swing.JPanel();
        btnReporterPb = new javax.swing.JButton();
        panelTexte = new javax.swing.JPanel();
        txtBas1 = new javax.swing.JLabel();
        txtNbAffiches = new javax.swing.JLabel();
        txtBas2 = new javax.swing.JLabel();
        panelEtat = new javax.swing.JPanel();
        rbFonctionnels = new javax.swing.JRadioButton();
        rbManquant = new javax.swing.JRadioButton();
        rbCasses = new javax.swing.JRadioButton();
        rbTousEtat = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        btnRetour.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stock/images/Bouton Retour.png"))); // NOI18N
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

        panelTitre.setLayout(new java.awt.GridBagLayout());

        labelNomFenetre.setFont(new java.awt.Font("Lucida Grande", 1, 36)); // NOI18N
        labelNomFenetre.setForeground(new java.awt.Color(51, 51, 51));
        labelNomFenetre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNomFenetre.setText(" : Liste Ipads");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        panelTitre.add(labelNomFenetre, gridBagConstraints);

        txtNomOP.setFont(new java.awt.Font("Lucida Grande", 1, 36)); // NOI18N
        txtNomOP.setForeground(new java.awt.Color(51, 51, 51));
        txtNomOP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNomOP.setText("_");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        panelTitre.add(txtNomOP, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        getContentPane().add(panelTitre, gridBagConstraints);

        jScrollPane.setMinimumSize(new java.awt.Dimension(512, 512));

        tabIpads.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tabIpads.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numéro", "Numéro de série", "UDID", "Code RFID", "Etiquette", "Date d'achat", "Durée de garantie"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabIpads.setAutoCreateRowSorter(true);
        tabIpads.setGridColor(new java.awt.Color(102, 102, 102));
        tabIpads.setRowHeight(30);
        tabIpads.setSelectionBackground(new java.awt.Color(153, 204, 255));
        tabIpads.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane.setViewportView(tabIpads);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jScrollPane, gridBagConstraints);

        panelBoutons.setLayout(new java.awt.GridBagLayout());

        btnReporterPb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stock/images/Bouton Casse.PNG"))); // NOI18N
        btnReporterPb.setToolTipText("Indication d'un problème sur un iPad");
        btnReporterPb.setBorderPainted(false);
        btnReporterPb.setContentAreaFilled(false);
        btnReporterPb.setFocusPainted(false);
        btnReporterPb.setMinimumSize(new java.awt.Dimension(128, 128));
        btnReporterPb.setPreferredSize(new java.awt.Dimension(128, 128));
        btnReporterPb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReporterPbMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        panelBoutons.add(btnReporterPb, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(panelBoutons, gridBagConstraints);

        txtBas1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        txtBas1.setText("Total : ");
        txtBas1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        panelTexte.add(txtBas1);

        txtNbAffiches.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        txtNbAffiches.setText("_");
        txtNbAffiches.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        panelTexte.add(txtNbAffiches);

        txtBas2.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        txtBas2.setText("iPad(s) disponibles");
        txtBas2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        panelTexte.add(txtBas2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 20, 0);
        getContentPane().add(panelTexte, gridBagConstraints);

        panelEtat.setLayout(new java.awt.GridBagLayout());

        bgEtat.add(rbFonctionnels);
        rbFonctionnels.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        rbFonctionnels.setSelected(true);
        rbFonctionnels.setText("Fonctionnels");
        rbFonctionnels.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rbFonctionnelsMousePressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        panelEtat.add(rbFonctionnels, gridBagConstraints);

        bgEtat.add(rbManquant);
        rbManquant.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        rbManquant.setText("Manquants");
        rbManquant.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rbManquantMousePressed(evt);
            }
        });
        panelEtat.add(rbManquant, new java.awt.GridBagConstraints());

        bgEtat.add(rbCasses);
        rbCasses.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        rbCasses.setText("Cassés");
        rbCasses.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rbCassesMousePressed(evt);
            }
        });
        panelEtat.add(rbCasses, new java.awt.GridBagConstraints());

        bgEtat.add(rbTousEtat);
        rbTousEtat.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        rbTousEtat.setText("Tous");
        rbTousEtat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rbTousEtatMousePressed(evt);
            }
        });
        panelEtat.add(rbTousEtat, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        getContentPane().add(panelEtat, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRetourMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRetourMouseClicked
        FenetreMenuOP fMenuOP = new FenetreMenuOP(this.op_courante,
                                                this.getWidth(), this.getHeight());
        fMenuOP.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRetourMouseClicked

    private void btnHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseClicked
        FenetreMenuPrincipal fMenuPrincipal = new FenetreMenuPrincipal(this.getWidth(), this.getHeight());
        fMenuPrincipal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHomeMouseClicked

    private void btnReporterPbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReporterPbMouseClicked
        DialogueReporterProbleme dReportCasse = new DialogueReporterProbleme(this, true, this.getWidth(), this.getHeight());
        dReportCasse.setVisible(true);
        refreshJTable();
    }//GEN-LAST:event_btnReporterPbMouseClicked

    private void rbFonctionnelsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbFonctionnelsMousePressed
        this.etatAffiche = 1;
        refreshJTable();
    }//GEN-LAST:event_rbFonctionnelsMousePressed

    private void rbManquantMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbManquantMousePressed
        this.etatAffiche = 2;
        refreshJTable();
    }//GEN-LAST:event_rbManquantMousePressed

    private void rbCassesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbCassesMousePressed
        this.etatAffiche = 3;
        refreshJTable();
    }//GEN-LAST:event_rbCassesMousePressed

    private void rbTousEtatMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbTousEtatMousePressed
        this.etatAffiche = 0;
        refreshJTable();
    }//GEN-LAST:event_rbTousEtatMousePressed

       
    
    /**
     * @brief Initialise le texte du titre pour y mettre le nom de l'op
     */
    
    private void init_textOP(){
        this.txtNomOP.setText(this.op_courante.getNomOP());
        
    }
    
    
    
  
    /**
     * Remets a jour la liste des iPads affiches
     */
    public void refreshJTable()
    {
        connexion_bdd bdd = new connexion_bdd();
        switch(this.etatAffiche)
        {
            case 0:
                // Charger la liste de tous les iPads de la BDD
                this.listIpad = SQL_function.getListIpadsBDD_Local(bdd.getlocal().getstatement());
                break;
                
            case 1:
                // Charger la liste de tous les iPads fonctionnels de la BDD
                this.listIpad = SQL_function.getListIpadsFonctionnelsBDD_Local(bdd.getlocal().getstatement());
                break;
                     
            case 2:
                // Charger la liste de tous les iPads manquants de la BDD
                this.listIpad = SQL_function.getListIpadsManquantsBDD_Local(bdd.getlocal().getstatement());
                break;
                         
            case 3:
                // Charger la liste de tous les iPads casses de la BDD
                this.listIpad = SQL_function.getListIpadsCassesBDD_Local(bdd.getlocal().getstatement());
                break;
        }
        bdd.closeall();
        
        // Affiche la liste dans la JTable
        afficheListIpads();
    }
    
    
        /**
     * @brief Affiche dans la JTable de la fenetre l'iPad passe en parametre (place en fin de liste)
     * 
     * @param iPad iPad a ajouter dans la liste d'affichage
     */
    private void afficheIpad(Ipad iPad)
    {
        // Recupere le modele pour ajouter de nouvelles lignes
        DefaultTableModel dtm = (DefaultTableModel)this.tabIpads.getModel();
        // Dimensions reinitialisees par l'appel ci-dessus, donc redimension
        tabIpads.getColumnModel().getColumn(0).setPreferredWidth(30);
        tabIpads.getColumnModel().getColumn(0).setMaxWidth(55);
        tabIpads.getColumnModel().getColumn(1).setPreferredWidth(100);
        tabIpads.getColumnModel().getColumn(1).setMaxWidth(200);
        tabIpads.getColumnModel().getColumn(5).setPreferredWidth(55);
        tabIpads.getColumnModel().getColumn(5).setMaxWidth(75);
        tabIpads.getColumnModel().getColumn(6).setPreferredWidth(40);
        tabIpads.getColumnModel().getColumn(6).setMaxWidth(105);
        
        // Gestion de la durée exacte a afficher
        Gestion_Duree_Garantie duree_exacte = new Gestion_Duree_Garantie();
        String duree_restante = duree_exacte.donne_duree_restant(iPad.getDateAchat());
        
        
        // Mise en vecteur des infos des iPads de la liste
        Vector row = new Vector();
        row.add(iPad.getDbNum());
        row.add(iPad.getNumSerie());
        row.add(iPad.getUdid());
        row.add(iPad.getRfid());
        row.add(iPad.getEtiquette());
        row.add(iPad.getStringDateAchat());
        row.add(duree_restante);
        row.add(iPad.getNomOp());
        
        // Ajout d'une nouvelle ligne avec les donnees de l'iPad ci-dessus
        dtm.addRow(row);
        dtm.fireTableRowsInserted(dtm.getRowCount() - 1, dtm.getRowCount() - 1);
    }
    
    
    
    /**
     * Actualise l'affichage de la JTable
     */
    private void afficheListIpads()
    {
        int i;
        
        //Efface le contenu de la JTable
        clearJTable();
        
        // Affichage de chaque iPad de la liste dans la JTable
        for(i = 0; i < this.listIpad.size(); i++)
        {
            afficheIpad(this.listIpad.get(i));
        }

        //MAJ nb Ipads affiches
        this.txtNbAffiches.setText(String.valueOf(this.tabIpads.getRowCount()));
    }
    
    
    
    /**
     * @brief Efface le contenu de la JTable
     */
    public void clearJTable()
    {
        int i;
        // Recupere le modele pour supprimer chaque ligne
        DefaultTableModel dtm = (DefaultTableModel)this.tabIpads.getModel();
        if(dtm.getRowCount() > 0)
        {
            for(i = dtm.getRowCount() - 1; i >= 0 ; i--)
            {
                dtm.removeRow(i);
                dtm.fireTableRowsDeleted(i - 1, i - 1);
            }
        }
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgEtat;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnReporterPb;
    private javax.swing.JButton btnRetour;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JLabel labelNomFenetre;
    private javax.swing.JPanel panelBoutons;
    private javax.swing.JPanel panelEtat;
    private javax.swing.JPanel panelTexte;
    private javax.swing.JPanel panelTitre;
    private javax.swing.JRadioButton rbCasses;
    private javax.swing.JRadioButton rbFonctionnels;
    private javax.swing.JRadioButton rbManquant;
    private javax.swing.JRadioButton rbTousEtat;
    private javax.swing.JTable tabIpads;
    private javax.swing.JLabel txtBas1;
    private javax.swing.JLabel txtBas2;
    private javax.swing.JLabel txtNbAffiches;
    private javax.swing.JLabel txtNomOP;
    // End of variables declaration//GEN-END:variables
}
