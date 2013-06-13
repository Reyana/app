/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.menu.op.retour;

import java.util.*;
import stock.base.Conteneur;
import stock.base.Ipad;
import stock.base.Serveur;
import stock.base.OP;
import stock.util.Ipad_Ignore;
import stock.util.Serveur_Ignore;
import stock.util.connexion.connexion_bdd;
import stock.util.dial.DialogueAvertissement;
import stock.util.sql.SQL_function;

/**
 *
 * @author Magency
 */
public class DialogueCharger extends javax.swing.JDialog {

    boolean res_iPad;
    int test_si_ipad_add = 0, test_inconnu = 0;
    
    String data, affiche, ipad_test;
    String[] ipad_afficher = new String[900];
    
    private final OP op_cour;
    private FenetreOPRetour fenMere;
    
    private List<Ipad_Ignore> listIpad_non_add = new ArrayList<Ipad_Ignore>(); 
    private Ipad_Ignore ipad_inconnu;
    
    private List<Serveur_Ignore> listServeur_non_add = new ArrayList<Serveur_Ignore>(); 
    private Serveur_Ignore serveur_inconnu;

    
    private List<Ipad> listIpadBdd_scanne = new ArrayList<Ipad>();
    private List<Ipad> listIpad_libre_BDD;
    
    private List<Conteneur> listConteneursCbBox = new ArrayList<Conteneur>();    
    private Conteneur conteneur = null;

    /**
     * Creates new form NewJDialog
     */
    public DialogueCharger(java.awt.Frame parent, boolean modal, OP op, String nomSelectedConteneur) {
        super(parent, modal);
        initComponents();
        this.op_cour = op;

        // Place le fenetre au centre de la fenetre parent
        this.setLocationRelativeTo(parent);

        this.fenMere = (FenetreOPRetour) parent;

        initListe(nomSelectedConteneur);
    }

    public DialogueCharger(java.awt.Frame parent, boolean modal,
                            int longueur, int largeur, OP op, String nomSelectedConteneur)
    {
        this(parent, modal, op, nomSelectedConteneur);
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
        labelOP = new javax.swing.JLabel();
        cbConteneur = new javax.swing.JComboBox();
        panelRfid = new javax.swing.JPanel();
        labelRfid = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtRfid = new javax.swing.JTextArea();
        panelTexteScannes = new javax.swing.JPanel();
        labelBas1 = new javax.swing.JLabel();
        txtNbiPad = new javax.swing.JLabel();
        txtNbsErveur = new javax.swing.JLabel();

        labelBas2 = new javax.swing.JLabel();
        panelBoutons = new javax.swing.JPanel();
        btnRetour = new javax.swing.JButton();
        btnOK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Charger un conteneur");
        setMinimumSize(new java.awt.Dimension(400, 300));

        labelTitre.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelTitre.setText("CHARGEMENT ");
        labelTitre.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        panelTitre.add(labelTitre);

        getContentPane().add(panelTitre, java.awt.BorderLayout.NORTH);

        panelCentre.setLayout(new java.awt.GridBagLayout());

        panelOP.setLayout(new java.awt.GridBagLayout());

        labelOP.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelOP.setText("Choix du conteneur :");
        labelOP.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        panelOP.add(labelOP, gridBagConstraints);

        cbConteneur.setMinimumSize(new java.awt.Dimension(100, 27));
        cbConteneur.setPreferredSize(new java.awt.Dimension(100, 27));
        cbConteneur.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbConteneurItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        panelOP.add(cbConteneur, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        panelCentre.add(panelOP, gridBagConstraints);

        panelRfid.setLayout(new java.awt.GridBagLayout());

        labelRfid.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelRfid.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRfid.setText("Scannez ici les iPads SVP :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        panelRfid.add(labelRfid, gridBagConstraints);

        txtRfid.setColumns(20);
        txtRfid.setRows(5);
        txtRfid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRfidKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(txtRfid);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        panelRfid.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 20);
        panelCentre.add(panelRfid, gridBagConstraints);

        labelBas1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelBas1.setText("Total scannés : ");
        labelBas1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        panelTexteScannes.add(labelBas1);

        txtNbiPad.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        txtNbiPad.setText("0");
        txtNbiPad.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        panelTexteScannes.add(txtNbiPad);

        labelBas2.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelBas2.setText("iPad(s)");
        labelBas2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        panelTexteScannes.add(labelBas2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        panelCentre.add(panelTexteScannes, gridBagConstraints);

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
            /*
         * Recupération de la liste sans doublon des
         */
        Set Tableau_ipads = new HashSet();
        if (affiche == null) {
            // Dialogue indiquant que il n'y a rien dans la box
            String avert = "Aucun iPad scanné";
            DialogueAvertissement warning = new DialogueAvertissement(this, true, avert);
            warning.setVisible(true);

        } else {
            Scanner scanne = new Scanner(affiche).useDelimiter("\\n");
            while (scanne.hasNext()) {
                Tableau_ipads.add(scanne.next());
            }

            /*
             * Comapraison avec la dbb
             */
            res_iPad = comparaison_ipad_scanner_et_ipadBdd(Tableau_ipads);
            if (!this.listIpadBdd_scanne.isEmpty()) {
                for (int i = 0; i < this.listIpadBdd_scanne.size(); i++) {
                    Ipad iPadAjoute = this.listIpadBdd_scanne.get(i);
                    this.conteneur.ajouterIpad(iPadAjoute);
                }
                // Ajoute et affiche le conteneur dans la fenetre Entrepot Envoi
                this.fenMere.chargeConteneur(this.conteneur);
            }
            if (res_iPad == true) {
                String txt="Les iPads suivants ne sont pas disponibles";
                DialogueAvertissement warning = new DialogueAvertissement(this, true, this.listIpad_non_add, txt);
                warning.setVisible(true);
            }
        }

        //Suppression de la fenetre
        this.dispose();
    }//GEN-LAST:event_btnOKMouseClicked

    /*
     * Verifie que les chariots d'une opé n'ont pas le même ipad enregistré
     *///getdataipads_libre_et_dans_ipad_nondeployables_en_local
    //getListIpadsBDD_Local
    private boolean comparaison_ipad_scanner_et_ipadBdd(Set liste_ipads) {
   String etat_ipad = null;
        //boolean verif = verif_ipad_pas_dans_op(liste_ipads);
        Iterator bdd_test = liste_ipads.iterator(); // on crée un Iterator pour parcourir notre HashSet 
        try {
            connexion_bdd bdd = new connexion_bdd();
            listIpad_libre_BDD = SQL_function.getdataipads_libre_et_non_dans_ipad_nondeployablesManquant_en_local(
                    bdd.getlocal().getstatement());
            List<Ipad> All_Ipad_BDD = SQL_function.getListIpadsBDD_AvecEtat_Local(
                    bdd.getlocal().getstatement());
            Ipad newIpad;
            
            while (bdd_test.hasNext()) // tant qu'on a un suivant
            {
                ipad_test = (String) bdd_test.next();
                System.out.println("listIpad_libre_BDD taille: " + listIpad_libre_BDD.size());
                int i = 0;
                while (i < listIpad_libre_BDD.size()) {
                    System.out.println("l'ipad en comparaison est: " + listIpad_libre_BDD.get(i).getRfid());
                    if (ipad_test.equals(listIpad_libre_BDD.get(i).getRfid()) && !verif_ipad_pas_dans_op(ipad_test)) {
                            newIpad = new Ipad(listIpad_libre_BDD.get(i).getDbNum(),
                                    listIpad_libre_BDD.get(i).getNumSerie(),
                                    listIpad_libre_BDD.get(i).getUdid(),
                                    listIpad_libre_BDD.get(i).getRfid(),
                                    listIpad_libre_BDD.get(i).getEtiquette());
                            this.listIpadBdd_scanne.add(newIpad);
                            System.out.println("OK2");
                            test_si_ipad_add = 2;
                             i++;
                            break;
                    }
                    i++;
                }
                if (test_si_ipad_add != 2) {
                    
                  if( "Inconnu".equals(verification_si_ipad_daansDBB_Local(ipad_test,All_Ipad_BDD)))
                      etat_ipad = "Inconnu";
                  else 
                  {    
                          etat_ipad = verification_si_ipad_daansDBB_Local(ipad_test,All_Ipad_BDD);
                  }
                  //Ajout de l'ipad non libre dans un Objet de type Ipad_Ignore
                    ipad_inconnu = new Ipad_Ignore(ipad_test,etat_ipad);
                    listIpad_non_add.add(ipad_inconnu);
                    bdd_test.remove();
                    test_inconnu = 1;
                }
                test_si_ipad_add = 0;

            }

            bdd.closeall();

            if (test_inconnu == 1) {
                System.out.println("OK3");
                return true;
            } else {
                System.out.println("OK4");
                return false;
            }

        } catch (Exception ex) {
            System.out.println(" une erreur dans la comparaison : " + ex.getMessage());
            return true;
        }

    }

    private boolean verif_ipad_pas_dans_op(String tmp) {
        //liste_ipads.remove("\n");
        //Iterator ipad_a_test = liste_ipads.iterator(); // on crée un Iterator pour parcourir notre HashSet 
        //while (ipad_a_test.hasNext()) // tant qu'on a un suivant
        //{
        //String temps = (String) ipad_a_test.next();
        for (Conteneur C : this.op_cour.getListConteneur()) {
            for (Ipad ipad : C.getListIpad()) {
                if (ipad.getRfid().equalsIgnoreCase(tmp)) {
                    //ipad_a_test.remove();
                    return true;
                }
            }
        }

        // }
        return false;
    }

 private String   verification_si_ipad_daansDBB_Local (String ipad, List<Ipad> All_Ipad_BDD ) {
                int i = 0;
                   while (i < All_Ipad_BDD.size()) {
                    if (ipad.equals(All_Ipad_BDD.get(i).getRfid())) {
                            return  All_Ipad_BDD.get(i).getEtat(); 
                    }
                    i++;
                }
     return "Inconnu";
     
 }
 
    private void txtRfidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRfidKeyPressed
        Set Tableau_ipads_scanner = new HashSet();
        int nbLignes = 0;
        // Retour a la ligne => nouvel iPad rentre
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) // Touche entr√©e, peut-etre marchera avec le lecteur RFID; a voir
        {
            data = this.txtRfid.getText();
            Scanner scan = new Scanner(data).useDelimiter("\\n");
            while (scan.hasNext()) {
                Tableau_ipads_scanner.add(scan.next());
            }
            affiche = "";
            Iterator box_test = Tableau_ipads_scanner.iterator();
            while (box_test.hasNext()) // tant qu'on a un suivant
            {
                ipad_afficher[nbLignes] = (String) box_test.next();
                if (!ipad_afficher[nbLignes].equalsIgnoreCase("")) {
                    affiche = affiche + ipad_afficher[nbLignes] + "\n";
                    nbLignes++;
                }
            }
            this.txtRfid.removeAll();
            this.txtNbiPad.setText(String.valueOf(nbLignes));
            this.txtRfid.setText(affiche);

        }

    }//GEN-LAST:event_txtRfidKeyPressed

    private void cbConteneurItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbConteneurItemStateChanged
        this.conteneur = this.listConteneursCbBox.get(this.cbConteneur.getSelectedIndex());
    }//GEN-LAST:event_cbConteneurItemStateChanged

    private void initListe(String nomSelectedConteneur) 
    {
        // Prendre dans la BDD la liste des conteneurs qui ne sont pas sur des OPs
        connexion_bdd bdd = new connexion_bdd();
        this.listConteneursCbBox = SQL_function.getListConteneurs_Local(bdd.getlocal().getstatement());
        bdd.closeall();

        // Initialises la liste des conteneurs selectionnables dans la combobox
        int i;
        for (i = 0; i < listConteneursCbBox.size(); i++)
        {
            this.cbConteneur.addItem(this.listConteneursCbBox.get(i).getName());
        }
        
        // Selectionne le conteneur selectionne dans la fentre precedente
        this.cbConteneur.setSelectedItem(nomSelectedConteneur);
        if(this.cbConteneur.getSelectedIndex() == -1)
        {
            if (this.listConteneursCbBox.get(0) != null)
            {
                this.conteneur = this.listConteneursCbBox.get(this.cbConteneur.getSelectedIndex());
            }
        }
        
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOK;
    private javax.swing.JButton btnRetour;
    private javax.swing.JComboBox cbConteneur;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelBas1;
    private javax.swing.JLabel labelBas2;
    private javax.swing.JLabel labelOP;
    private javax.swing.JLabel labelRfid;
    private javax.swing.JLabel labelTitre;
    private javax.swing.JPanel panelBoutons;
    private javax.swing.JPanel panelCentre;
    private javax.swing.JPanel panelOP;
    private javax.swing.JPanel panelRfid;
    private javax.swing.JPanel panelTexteScannes;
    private javax.swing.JPanel panelTitre;
    private javax.swing.JLabel txtNbiPad;
    private javax.swing.JLabel txtNbsErveur;
    private javax.swing.JTextArea txtRfid;
    // End of variables declaration//GEN-END:variables
}
