/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.menu.op.recep;

import java.util.*;
import stock.base.Conteneur;
import stock.base.Ipad;
import stock.util.Ipad_Ignore;
import stock.util.dial.DialogueAvertissement;
import stock.util.connexion.connexion_bdd;
import stock.util.sql.SQL_function;

/**
 *
 * @author Magency
 */
public class DialogueDecharger extends javax.swing.JDialog {
    
    private FenetreOPReception fenMere;
    private final Conteneur conteneur;

    private List<Ipad_Ignore> listIpad_non_add = new ArrayList<Ipad_Ignore>(); 
    private Ipad_Ignore ipad_inconnu;
    
    /**Gestion des iPads que l'on vient de scanné en choisissant un chariot
     *De la liste des iPads que l'on a scanné au total (scan des différent chariot)
     * Liste des iPads dans la base local
     */
    private List<Ipad> listIpadLocale_scannee = new ArrayList<Ipad>();
    private List<Ipad> listIpadsOP = new ArrayList<Ipad>();
    private List<Ipad> listIpad_baseLocal;
    
    String data, affiche, ipad_test;
    int test_si_ipad_inconnu = 0, test_si_ipad_add = 0;
    boolean res_ipad, rfid_retrouve;

    /**
     * Creates new form NewJDialog
     */
    public DialogueDecharger(java.awt.Frame parent, boolean modal, Conteneur conteneur, List<Ipad> listIpadsOP) {
        super(parent, modal);
        initComponents();

        // Place le fenetre au centre de la fenetre parent
        this.setLocationRelativeTo(parent);
        
        this.fenMere = (FenetreOPReception) parent;

        // Recupere le conteneur selectionne
        this.conteneur = conteneur;
        
        this.listIpadsOP = listIpadsOP;

        // Recuperation du numero de la ligne du conteneur selectionne 
        this.txtNomConteneur.setText(conteneur.getName().toUpperCase());
    }
    
    public DialogueDecharger(java.awt.Frame parent, boolean modal, Conteneur conteneur,
            int longueur, int largeur, List<Ipad> listIpadsOP) {
        this(parent, modal, conteneur, listIpadsOP);
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
        txtNomConteneur = new javax.swing.JLabel();
        panelCentre = new javax.swing.JPanel();
        panelRfid = new javax.swing.JPanel();
        labelRfid = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtRfid = new javax.swing.JTextArea();
        panelTexteScannes = new javax.swing.JPanel();
        labelBas1 = new javax.swing.JLabel();
        txtNbiPad = new javax.swing.JLabel();
        labelBas2 = new javax.swing.JLabel();
        panelBoutons = new javax.swing.JPanel();
        btnRetour = new javax.swing.JButton();
        btnOK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Decharger un conteneur");
        setMinimumSize(new java.awt.Dimension(400, 300));

        labelTitre.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelTitre.setText("DECHARGER : ");
        labelTitre.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        panelTitre.add(labelTitre);

        txtNomConteneur.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        txtNomConteneur.setText("_");
        txtNomConteneur.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        panelTitre.add(txtNomConteneur);

        getContentPane().add(panelTitre, java.awt.BorderLayout.NORTH);

        panelCentre.setLayout(new java.awt.GridBagLayout());

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
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 20);
        panelCentre.add(panelRfid, gridBagConstraints);

        labelBas1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelBas1.setText("Total scanné(s) : ");
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
        gridBagConstraints.gridy = 1;
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
        
        int nbScans = 0;

        /*
         * Recupération de la liste sans doublon des
         */
        
        Set Tableau_ipads = new HashSet();
        if (affiche == null) 
        {
            // Dialogue indiquant que il n'y a rien dans la box
            String avert = "Aucun iPad scanné";
            DialogueAvertissement warning = new DialogueAvertissement(this, true, avert);
            warning.setVisible(true);
            
        } 
        else  
        {
            Scanner scanne = new Scanner(affiche).useDelimiter("\\n");
            while (scanne.hasNext()) {
                Tableau_ipads.add(scanne.next());
            }

            /*
             * Comapraison avec la dbb
             */
            res_ipad = comparaison_ipad_scanner_et_ipadBdd(Tableau_ipads);
            
           
            if (!this.listIpadLocale_scannee.isEmpty()) {
                // Retire chaque iPad scanne trouve dans la BDD au conteneur , sauf s'il contient deja le meme iPad
                for (int i = 0; i < this.listIpadLocale_scannee.size(); i++) {
                    Ipad iPadScanne = this.listIpadLocale_scannee.get(i);
                    // System.out.println("rfid: "+iPadScanne.getRfid()+" udid: "+ iPadScanne.getUdid()+" etiquette: "+iPadScanne.getEtiquette());
                    //this.conteneur.retirerIpad(iPadScanne);
                    this.listIpadsOP.add(iPadScanne);
                     System.out.println("nb d'iPads dans listIpadsOP : " + this.listIpadsOP.size());
                    nbScans++;
                }
            }
            // Si un iPad scanne n'appartient pas au chariot passe en parametre, l'iPad n'est pas pris en compte
            if (res_ipad == true) {
                String txt="Les iPads suivant ne sont pas disponibles";
                DialogueAvertissement warning = new DialogueAvertissement(this, true,this.listIpad_non_add, txt);
                warning.setVisible(true);
            }
        }
        // Ajoute et affiche le conteneur dans la fenetre Entrepot Envoi
        this.fenMere.refreshNbScansConteneur(this.conteneur, nbScans);
        System.out.println("OK8");
        
        this.dispose();
        
    }//GEN-LAST:event_btnOKMouseClicked

    /*
     * Verifie que les chariots d'une opé n'ont pas le même ipad enregistré
     */
    private boolean comparaison_ipad_scanner_et_ipadBdd(Set liste_ipads) {
        String etat_ipad = null;
        Iterator liste_test_locale = liste_ipads.iterator();
        Ipad newIpad;
        int test_inconnu = 0;
        
        try {
            connexion_bdd bdd = new connexion_bdd();
            listIpad_baseLocal = SQL_function.getdataipads_libre_et_dans_ipad_nondeployables_en_local(
                    bdd.getlocal().getstatement());
            
            while (liste_test_locale.hasNext()) // tant qu'on a un suivant
            {
                ipad_test = (String) liste_test_locale.next();
                System.out.println("l'ipad testé est: " + ipad_test);
                int i = 0;
                while (i < listIpad_baseLocal.size()) {
                    System.out.println(" l'ipad en comparaison est : " + listIpad_baseLocal.get(i).getRfid());
                    if (ipad_test.equals(listIpad_baseLocal.get(i).getRfid()) && verif_ipad_pas_dans_op(ipad_test)) {
                        etat_ipad = listIpad_baseLocal.get(i).getEtat();
                        if (etat_ipad == null) {
                            listIpad_baseLocal.get(i).setEtat("Fonctionnel");
                            newIpad = new Ipad(listIpad_baseLocal.get(i).getDbNum(),
                                    listIpad_baseLocal.get(i).getNumSerie(),
                                    listIpad_baseLocal.get(i).getUdid(),
                                    listIpad_baseLocal.get(i).getRfid(),
                                    listIpad_baseLocal.get(i).getEtiquette(), listIpad_baseLocal.get(i).getEtat());
                        } else {
                            newIpad = new Ipad(listIpad_baseLocal.get(i).getDbNum(), listIpad_baseLocal.get(i).getNumSerie(),
                                    listIpad_baseLocal.get(i).getUdid(),
                                    listIpad_baseLocal.get(i).getRfid(),
                                    listIpad_baseLocal.get(i).getEtiquette(),
                                    listIpad_baseLocal.get(i).getEtat());
                            
                        }
                        this.listIpadLocale_scannee.add(newIpad);
                        test_si_ipad_add = 2;
                        //i++;
                        break;
                    }
                    //rfid_retrouve = false;
                    i++;
                }
                i = 0;
                
                if (test_si_ipad_add != 2) {
                 if( !verification_si_dans_listLocal(ipad_test,listIpad_baseLocal))
                 {
                      etat_ipad = "Inconnu";
                  
                  //Ajout de l'ipad non libre dans un Objet de type Ipad_Ignore
                    ipad_inconnu = new Ipad_Ignore(ipad_test,etat_ipad);
                    listIpad_non_add.add(ipad_inconnu);
                    liste_test_locale.remove();
                    test_inconnu = 1;
                 }
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
        
//        if (!this.listIpadsOP.isEmpty()) {
//            for (Ipad ipad : this.listIpadsOP) {
//                if (ipad.getRfid().equals(tmp)) {
//                    rfid_retrouve = false;
//                } else {
//                    rfid_retrouve = true;
//                }
//            }
//        } else {
//            rfid_retrouve = true;
//        }
//        return rfid_retrouve;
        if (!this.listIpadsOP.isEmpty()) {
                        int i = 0;
                   while (i < this.listIpadsOP.size()) {
                    if (tmp.equals(this.listIpadsOP.get(i).getRfid())) {
                             return false;
                    }
                    i++;
                }
        }
     return true;
    }
    
    private boolean verification_si_dans_listLocal(String ipad,List<Ipad> All_Ipad_BDD){
                int i = 0;
                   while (i < All_Ipad_BDD.size()) {
                    if (ipad.equals(All_Ipad_BDD.get(i).getRfid())) {
                             return true;
                    }
                    i++;
                }
     return false;
    }
    
    private void txtRfidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRfidKeyPressed
        Set Tableau_ipads_scanner = new HashSet();
        int nbLignes = 0;
        String[] ipad_afficher = new String[900];

        // Retour a la ligne => nouvel iPad rentr√©
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) // Touche entree, peut-etre marchera avec le lecteur RFID; a voir
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOK;
    private javax.swing.JButton btnRetour;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelBas1;
    private javax.swing.JLabel labelBas2;
    private javax.swing.JLabel labelRfid;
    private javax.swing.JLabel labelTitre;
    private javax.swing.JPanel panelBoutons;
    private javax.swing.JPanel panelCentre;
    private javax.swing.JPanel panelRfid;
    private javax.swing.JPanel panelTexteScannes;
    private javax.swing.JPanel panelTitre;
    private javax.swing.JLabel txtNbiPad;
    private javax.swing.JLabel txtNomConteneur;
    private javax.swing.JTextArea txtRfid;
    // End of variables declaration//GEN-END:variables
}
