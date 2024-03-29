/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.menu.entrepot.list;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import stock.base.Serveur;
import stock.util.Gestion_Duree_Garantie;
import stock.util.connexion.connexion_bdd;
import stock.util.dial.DialogueAvertissement;
import stock.util.sql.InstructionSql;
import stock.util.sql.SQL_function;

/**
 *
 * @author Magency
 */
public class DialogueModifServeur extends javax.swing.JDialog {

    private final FenetreEntrepotListesErveurs fenMere;
    private Serveur sErveur;
    private boolean isNumSerieOK = false;
    private boolean isUdidOK = false;

    /**
     * Creates new form NewJDialog
     */
    public DialogueModifServeur(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        // Place le fenetre au centre de la fenetre parent
        this.setLocationRelativeTo(parent);
        this.fenMere = (FenetreEntrepotListesErveurs) parent;
    }

    public DialogueModifServeur(java.awt.Frame parent, boolean modal, int longueur, int largeur) {
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
        panelCentre = new javax.swing.JPanel();
        labelSN = new javax.swing.JLabel();
        txtSN = new javax.swing.JTextField();
        panelUdid = new javax.swing.JPanel();
        labelUdid = new javax.swing.JLabel();
        txtUdid = new javax.swing.JTextField();
        labelRfid = new javax.swing.JLabel();
        txtRfid = new javax.swing.JTextField();
        labelEtiquette = new javax.swing.JLabel();
        txtEtiquette = new javax.swing.JTextField();
        labelDateAchat = new javax.swing.JLabel();
        txtDureeGarantie = new javax.swing.JTextField();
        labelDateAchat1 = new javax.swing.JLabel();
        dtAchat = new com.toedter.calendar.JDateChooser();
        panelBoutons = new javax.swing.JPanel();
        btnRetour = new javax.swing.JButton();
        btnOK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modifier un Serveur");
        setMinimumSize(new java.awt.Dimension(400, 300));

        labelNomFenetre.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        labelNomFenetre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNomFenetre.setText("Modifier un Serveur");
        getContentPane().add(labelNomFenetre, java.awt.BorderLayout.NORTH);

        java.awt.GridBagLayout panelCentreLayout = new java.awt.GridBagLayout();
        panelCentreLayout.columnWidths = new int[] {0, 0, 0};
        panelCentreLayout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        panelCentre.setLayout(panelCentreLayout);

        labelSN.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelSN.setText("Numéro de série :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        panelCentre.add(labelSN, gridBagConstraints);

        txtSN.setEnabled(false);
        txtSN.setPreferredSize(new java.awt.Dimension(100, 28));
        txtSN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSNKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        panelCentre.add(txtSN, gridBagConstraints);

        panelUdid.setLayout(new java.awt.GridBagLayout());

        labelUdid.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelUdid.setText("UDID :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        panelUdid.add(labelUdid, gridBagConstraints);

        txtUdid.setEnabled(false);
        txtUdid.setPreferredSize(new java.awt.Dimension(100, 28));
        txtUdid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUdidKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelUdid.add(txtUdid, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        panelCentre.add(panelUdid, gridBagConstraints);

        labelRfid.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelRfid.setText("Scannez le Serveur a modifier :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 0);
        panelCentre.add(labelRfid, gridBagConstraints);

        txtRfid.setPreferredSize(new java.awt.Dimension(100, 28));
        txtRfid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRfidKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        panelCentre.add(txtRfid, gridBagConstraints);

        labelEtiquette.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelEtiquette.setText("Etiquette :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        panelCentre.add(labelEtiquette, gridBagConstraints);

        txtEtiquette.setEnabled(false);
        txtEtiquette.setPreferredSize(new java.awt.Dimension(100, 28));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        panelCentre.add(txtEtiquette, gridBagConstraints);

        labelDateAchat.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelDateAchat.setText("Date d'achat :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        panelCentre.add(labelDateAchat, gridBagConstraints);

        txtDureeGarantie.setEnabled(false);
        txtDureeGarantie.setPreferredSize(new java.awt.Dimension(100, 28));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        panelCentre.add(txtDureeGarantie, gridBagConstraints);

        labelDateAchat1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelDateAchat1.setText("Durée garantie :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        panelCentre.add(labelDateAchat1, gridBagConstraints);

        dtAchat.setDate(java.util.Calendar.getInstance().getTime());
        dtAchat.setDateFormatString("dd/MM/yy");
        dtAchat.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        panelCentre.add(dtAchat, gridBagConstraints);

        getContentPane().add(panelCentre, java.awt.BorderLayout.CENTER);

        btnRetour.setText("Retour");
        btnRetour.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRetourMouseClicked(evt);
            }
        });
        panelBoutons.add(btnRetour);

        btnOK.setText("OK");
        btnOK.setEnabled(false);
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
        int test = 0;
        if (this.btnOK.isEnabled()) {
            // Modification du Serveur selectionne dans la BDD
            String donnee = "", oldonnee = "";// use to add data in database
            // Vérifie si changement du numéro de série
            String etiquette = this.txtEtiquette.getText();
            oldonnee = "code_rfid+" + sErveur.getRfid() + "+";
            if (!etiquette.equals("")) {
                if (!sErveur.getEtiquette().equals(etiquette)) {
                    //this.sErveur.se(this.txtSN.getText());
                    donnee = "etiquette+" + etiquette + "+";
                    test = 1;
                }
            }

            // Vérifie si changement de date d'achat
            Date dateAchat = this.dtAchat.getDate();
            if ((sErveur.getDateAchat() == null) && ((dateAchat == null))) {
                test = 0;
            } else {
                if (!(dateAchat == null)) {
                    if (sErveur.getDateAchat() == null) {
                        System.out.println("ok1");
                        donnee = formatDate(donnee, dateAchat, "date_achat+", test);
                        test = 2;
                    } else {
                        if (!sErveur.getDateAchat().equals(dateAchat)) {
                            System.out.println("ok2");
                            donnee = formatDate(donnee, dateAchat, "date_achat+", test);
                            test = 2;
                        }
                    }

                } else {
                    System.out.println("ok3");
                    donnee = formatDate(donnee, dateAchat, "date_achat+", test);
                    test = 2;
                }
            }


            //Récupère la duree de garantie, 0 sinon
            int garantie;
           if (!this.txtDureeGarantie.getText().equals("")&&!sErveur.
                   getDureeGarantie().equals(this.txtDureeGarantie.getText())) {
                    //garantie = Integer.parseInt(this.txtDureeGarantie.getText());
                    Gestion_Duree_Garantie rgx = new Gestion_Duree_Garantie();
                    garantie = rgx.regex(this.txtDureeGarantie.getText());
                    if (garantie < 0) {
                        DialogueAvertissement warning = new DialogueAvertissement(this, true);
                        warning.setVisible(true);
                        //this.dispose();
                    } else {
                        if ((test == 1) || (test == 2)) {
                            donnee = donnee + "duree_garantie+" + garantie + "+";
                        } else {
                            donnee = "duree_garantie+" + garantie + "+";
                        }
                    }
            }

            //Modification de la nouvelle OP dans la BDD?
            InstructionSql modif_sErveur = new InstructionSql();
            modif_sErveur.Update_Serveur(donnee, oldonnee);
            
            // MAJ de l'affichage des sErveurs dans la JTable
            this.fenMere.refreshJTable();
            //Suppression de la fenetre
            this.dispose();
        }
    }//GEN-LAST:event_btnOKMouseClicked

    public String formatDate(String donnee, Date date, String nomdate, int test) {
        if ((test == 1)) {
            return donnee + nomdate + (date == null ? ""
                    : new SimpleDateFormat("yy/MM/dd").format(date)) + "+";
        } else {
            return nomdate + (date == null ? ""
                    : new SimpleDateFormat("yy/MM/dd").format(date)) + "+";
        }
    }

    private void txtRfidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRfidKeyReleased
        boolean rfidTrouve = false;
        int i = 0;
        if (this.txtRfid.getText().equals("")){
            this.txtDureeGarantie.setText(null);
            this.txtUdid.setText(null);
            this.txtSN.setText(null);
            this.txtEtiquette.setText(null);
            this.dtAchat.setDate(null);
        }else if (evt.getKeyChar() == '\n'){
            //Si le champ RFID n'est pas vide
            this.txtUdid.grabFocus();

                // Recuperation du sErveur avec ce champ RFID dans la BDD s'il existe
                // A activer seulement si un sErveur a bien ete trouve
                //recherche dans la base du sErveur

                    //Charger la liste de sErveur depuis la BDD
                    connexion_bdd bdd = new connexion_bdd();
                    List<Serveur> listServeur = SQL_function.getListServeursBDD(bdd.getserver().getstatement());
                    bdd.closeall();

                    if(!listServeur.isEmpty())
                    {
                        // On recherche dans la liste le Serveur avec le code RFID entre dans la fenetre
                        while(i < listServeur.size() && !rfidTrouve)
                        {
                            Serveur sErveurBDD = listServeur.get(i);
                            if(sErveurBDD.getRfid().equalsIgnoreCase(this.txtRfid.getText()))
                            {
                                this.sErveur = sErveurBDD;
                                rfidTrouve = true;
                            }
                            i++;
                        }
                        if(rfidTrouve)
                        {
                            afficheChampsServeur(this.sErveur);
                            this.isNumSerieOK = true;
                            this.isUdidOK = true;
                            setOK(true);
                        }
                        else
                        {
                            DialogueAvertissement warning = new DialogueAvertissement(this, true, "Le code RFID entré N'existe pas");
                            warning.setVisible(true);
                        }   
                    }
            } 
            else 
            {
                setOK(false);
            }
        
    }//GEN-LAST:event_txtRfidKeyReleased

    private void txtSNKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSNKeyReleased
        // Numero de serie : 11 ou 12 caracteres
        if (this.txtSN.getText().length() == 11 || this.txtSN.getText().length() == 12) {
            this.isNumSerieOK = true;
        } else {
            this.isNumSerieOK = false;
        }
        checkActivationOK();
    }//GEN-LAST:event_txtSNKeyReleased

    private void txtUdidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUdidKeyReleased
        // Udid : 40 caracteres
        if (this.txtUdid.getText().length() == 40) {
            this.isUdidOK = true;
        } else {
            this.isUdidOK = false;
        }
        checkActivationOK();
    }//GEN-LAST:event_txtUdidKeyReleased

    /**
     * @brief Affiche dans chaque champ de la boite de dialogue les informations
     * du Serveur passe en arametre
     *
     * @param Serveur {@link Serveur} dont les onformations sont recuperees
     */
    private void afficheChampsServeur(Serveur sErveur) {
        this.txtSN.setText(sErveur.getNumSerie());
        this.txtUdid.setText(sErveur.getUdid());
        this.txtEtiquette.setText(sErveur.getEtiquette());
        this.dtAchat.setDate(sErveur.getDateAchat());
        this.txtDureeGarantie.setText(sErveur.getDureeGarantie());
    }

    /**
     * @brief Active/desactives les differents champs et le bouton OK selon
     * l'etat du champ RFID
     *
     * @param bln {@link boolean} responsable de l'activation (true) ou de la
     * desactivation des champs
     */
    private void setOK(boolean bln) {
        this.txtSN.setEnabled(false);
        this.txtUdid.setEnabled(false);
        this.txtEtiquette.setEnabled(bln);
        this.dtAchat.setEnabled(bln);
        this.txtDureeGarantie.setEnabled(bln);
        this.btnOK.setEnabled(bln);
    }

    /**
     * @brief Actives le bouton OK si les champs obligatoires sont remplis
     */
    private void checkActivationOK() {
        //System.out.println(String.valueOf(isNumSerieOK) + String.valueOf(isUdidOK) + String.valueOf(isRfidOK));
        if (this.isNumSerieOK && this.isUdidOK) {
            this.btnOK.setEnabled(true);
        } else {
            this.btnOK.setEnabled(false);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOK;
    private javax.swing.JButton btnRetour;
    private com.toedter.calendar.JDateChooser dtAchat;
    private javax.swing.JLabel labelDateAchat;
    private javax.swing.JLabel labelDateAchat1;
    private javax.swing.JLabel labelEtiquette;
    private javax.swing.JLabel labelNomFenetre;
    private javax.swing.JLabel labelRfid;
    private javax.swing.JLabel labelSN;
    private javax.swing.JLabel labelUdid;
    private javax.swing.JPanel panelBoutons;
    private javax.swing.JPanel panelCentre;
    private javax.swing.JPanel panelUdid;
    private javax.swing.JTextField txtDureeGarantie;
    private javax.swing.JTextField txtEtiquette;
    private javax.swing.JTextField txtRfid;
    private javax.swing.JTextField txtSN;
    private javax.swing.JTextField txtUdid;
    // End of variables declaration//GEN-END:variables
}
