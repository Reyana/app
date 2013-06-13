/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.util.dial;

import java.awt.Frame;
import java.awt.Image;
import javax.swing.ImageIcon;
import stock.base.OP;
import stock.menu.FenetreMenuPrincipal;
import stock.menu.entrepot.FenetreMenuEntrepot;
import stock.menu.entrepot.envoi.FenetreEntrepotEnvoi;
import stock.menu.entrepot.recep.FenetreEntrepotReception;
import stock.menu.op.FenetreMenuOP;
import stock.menu.op.retour.FenetreOPRetour;
import stock.util.sql.SQL_function;


/**
 *
 * @author Magency
 */
public class DialogueChoixImport extends javax.swing.JDialog {
    private Frame fenMere;
    private String dbname;
    private String nomFenPrec;
    private OP op;
    ImageIcon imgIconNon = new ImageIcon(getClass().getResource("/stock/images/Bouton Non.PNG"));
    ImageIcon imgIconOui = new ImageIcon(getClass().getResource("/stock/images/Bouton Oui.PNG"));

    /**
     * Creates new form NewJDialog
     */
    public DialogueChoixImport(java.awt.Frame parent, boolean modal, 
                                OP op, String dbname, String fenetrePrec) {
        super(parent, modal);
        initComponents();
        // Place le fenetre au centre de la fenetre parent
        this.setLocationRelativeTo(parent);
        
        this.nomFenPrec = fenetrePrec;
        chargeTextes();
        
        if(this.nomFenPrec.equalsIgnoreCase("OPReception"))
        {
            this.fenMere = (FenetreMenuPrincipal)parent;
        }
        else if(this.nomFenPrec.equalsIgnoreCase("entrepotReception"))
        {
            this.fenMere = (FenetreMenuEntrepot)parent;
        }
        else if(this.nomFenPrec.equalsIgnoreCase("entrepotEnvoi"))
        {
            this.fenMere = (FenetreEntrepotEnvoi)parent;
        }
        else if(this.nomFenPrec.equalsIgnoreCase("retourEntrepot"))
        {
            this.fenMere = (FenetreOPRetour)parent;
        }
        
        this.op = op;
        this.dbname = dbname;
        
        // Donne la bonne taille aux images des booutons
        redimensionnerFenetre();
        
        
    }
    
    public DialogueChoixImport(java.awt.Frame parent, boolean modal, 
                                int longueur, int largeur, 
                                OP op, String dbname, String fenetrePrec) {
        this(parent, modal, op, dbname, fenetrePrec);
        this.setSize(new java.awt.Dimension(longueur * 3 / 4, largeur * 3 / 4));
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
        panelCentre = new javax.swing.JPanel();
        panelBoutonsOuiNon = new javax.swing.JPanel();
        btnNon = new javax.swing.JButton();
        btnOui = new javax.swing.JButton();
        panelInstructions = new javax.swing.JPanel();
        txtInstr1 = new javax.swing.JTextArea();
        txtInstr2 = new javax.swing.JTextArea();
        panelBoutonRetour = new javax.swing.JPanel();
        btnRetour = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Choix d'import/export");
        setMinimumSize(new java.awt.Dimension(600, 450));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        labelNomFenetre.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        labelNomFenetre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNomFenetre.setText("Choix d'import");
        getContentPane().add(labelNomFenetre, java.awt.BorderLayout.NORTH);

        panelCentre.setLayout(new java.awt.GridBagLayout());

        panelBoutonsOuiNon.setLayout(new java.awt.GridBagLayout());

        btnNon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stock/images/Bouton Non.PNG"))); // NOI18N
        btnNon.setBorderPainted(false);
        btnNon.setContentAreaFilled(false);
        btnNon.setFocusPainted(false);
        btnNon.setMinimumSize(new java.awt.Dimension(128, 128));
        btnNon.setPreferredSize(new java.awt.Dimension(128, 128));
        btnNon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNonMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 20);
        panelBoutonsOuiNon.add(btnNon, gridBagConstraints);

        btnOui.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stock/images/Bouton Oui.PNG"))); // NOI18N
        btnOui.setBorderPainted(false);
        btnOui.setContentAreaFilled(false);
        btnOui.setFocusPainted(false);
        btnOui.setMinimumSize(new java.awt.Dimension(128, 128));
        btnOui.setPreferredSize(new java.awt.Dimension(128, 128));
        btnOui.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOuiMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 10);
        panelBoutonsOuiNon.add(btnOui, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelCentre.add(panelBoutonsOuiNon, gridBagConstraints);

        panelInstructions.setLayout(new java.awt.GridBagLayout());

        txtInstr1.setBackground(new java.awt.Color(238, 238, 238));
        txtInstr1.setColumns(20);
        txtInstr1.setEditable(false);
        txtInstr1.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        txtInstr1.setLineWrap(true);
        txtInstr1.setWrapStyleWord(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelInstructions.add(txtInstr1, gridBagConstraints);

        txtInstr2.setBackground(new java.awt.Color(238, 238, 238));
        txtInstr2.setColumns(20);
        txtInstr2.setEditable(false);
        txtInstr2.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        txtInstr2.setLineWrap(true);
        txtInstr2.setWrapStyleWord(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelInstructions.add(txtInstr2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 20);
        panelCentre.add(panelInstructions, gridBagConstraints);

        getContentPane().add(panelCentre, java.awt.BorderLayout.CENTER);

        btnRetour.setText("Retour");
        btnRetour.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRetourMouseClicked(evt);
            }
        });
        panelBoutonRetour.add(btnRetour);

        getContentPane().add(panelBoutonRetour, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRetourMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRetourMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnRetourMouseClicked

    private void btnNonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNonMouseClicked
        if(this.nomFenPrec.equalsIgnoreCase("OPReception"))
        {
            // Va directement dans le menu de l'OP
            FenetreMenuOP fMenuOP = new FenetreMenuOP(SQL_function.getdataop_local(), 
                                        this.fenMere.getWidth(), this.fenMere.getHeight());
            fMenuOP.setVisible(true);
            this.fenMere.dispose();
            this.dispose();
        }
        else if(this.nomFenPrec.equalsIgnoreCase("entrepotReception"))
        {
            FenetreEntrepotReception fEntrepotReception = new FenetreEntrepotReception(this.op,
                                        this.fenMere.getWidth(), this.fenMere.getHeight());
            fEntrepotReception.setVisible(true);
            this.fenMere.dispose();
            this.dispose();
        }
        else if(this.nomFenPrec.equalsIgnoreCase("entrepotEnvoi"))
        {
            FenetreMenuPrincipal fMenuPrincipal = new FenetreMenuPrincipal(this.fenMere.getWidth(), this.fenMere.getHeight());
            fMenuPrincipal.setVisible(true);
            this.fenMere.dispose();
            this.dispose();
        }
        else if(this.nomFenPrec.equalsIgnoreCase("retourEntrepot"))
        {
            FenetreMenuPrincipal fMenuPrincipal = new FenetreMenuPrincipal(this.fenMere.getWidth(), this.fenMere.getHeight());
            fMenuPrincipal.setVisible(true);
            this.fenMere.dispose();
            this.dispose();
        }
    }//GEN-LAST:event_btnNonMouseClicked

    private void btnOuiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOuiMouseClicked
        // Va dans le dialogue de recherche de zip
        DialogueRechercheZip dzip = new DialogueRechercheZip(this.nomFenPrec, 
                            this.fenMere , true, this.fenMere.getWidth(), this.fenMere.getHeight(),
                            dbname, op);
        dzip.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnOuiMouseClicked

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        redimensionnerFenetre();
    }//GEN-LAST:event_formComponentResized
    
    
    
    private void redimensionnerFenetre()
    {
        Image imgOui = imgIconOui.getImage();
        int dim = Math.min(btnOui.getWidth(), btnOui.getHeight());
        Image newImgOui = imgOui.getScaledInstance(dim, dim, Image.SCALE_DEFAULT);
        btnOui.setIcon(new ImageIcon(newImgOui));

        Image imgNon = imgIconNon.getImage();
        Image newImgNon = imgNon.getScaledInstance(dim, dim, Image.SCALE_DEFAULT);
        btnNon.setIcon(new ImageIcon(newImgNon));

        labelNomFenetre.setFont(labelNomFenetre.getFont().deriveFont(labelNomFenetre.getHeight()));
    }
    
    
    
    private void chargeTextes()
    {
        if(this.nomFenPrec.equalsIgnoreCase("OPReception"))
        {
            this.labelNomFenetre.setText("Choix d'import");
            this.txtInstr1.setText("Voulez-vous importer les données depuis une archive?");
            this.txtInstr2.setText("(seulement en debut d'une OP envoyée depuis un autre ordinateur)");
        }
        else if(this.nomFenPrec.equalsIgnoreCase("entrepotReception"))
        {
            this.labelNomFenetre.setText("Choix d'import");
            this.txtInstr1.setText("Voulez-vous importer les données depuis une archive?");
            this.txtInstr2.setText("(si l'OP a été renvoyée depuis un autre ordinateur)");
        }
        else if(this.nomFenPrec.equalsIgnoreCase("entrepotEnvoi"))
        {
            this.labelNomFenetre.setText("Choix d'export");
            this.txtInstr1.setText("Base de données locale créee.\nVoulez-vous également exporter les données sous forme d'archive?");
            this.txtInstr2.setText("(si l'OP a été renvoyée depuis un autre ordinateur)");
        }
        else if(this.nomFenPrec.equalsIgnoreCase("retourEntrepot"))
        {
            this.labelNomFenetre.setText("Choix d'export");
            this.txtInstr1.setText("Base de données locale mise à jour.\nVoulez-vous également exporter les données sous forme d'archive?");
            this.txtInstr2.setText("(pour réceptionner cette opération depuis un autre ordinateur)");
        }
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNon;
    private javax.swing.JButton btnOui;
    private javax.swing.JButton btnRetour;
    private javax.swing.JLabel labelNomFenetre;
    private javax.swing.JPanel panelBoutonRetour;
    private javax.swing.JPanel panelBoutonsOuiNon;
    private javax.swing.JPanel panelCentre;
    private javax.swing.JPanel panelInstructions;
    private javax.swing.JTextArea txtInstr1;
    private javax.swing.JTextArea txtInstr2;
    // End of variables declaration//GEN-END:variables
}
