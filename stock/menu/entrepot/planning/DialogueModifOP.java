/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.menu.entrepot.planning;

import stock.base.OP;
import stock.util.sql.InstructionSql;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author Magency
 */
public class DialogueModifOP extends javax.swing.JDialog {

    private final FenetreEntrepotPlanning fenMere;
    private final ModelPlanning modelP;
    private List<OP> listOP;
    private final int indexSelectedOP;
    private OP selectedOP;
    int test = 0;

    /**
     * Creates new form NewJDialog
     */
    public DialogueModifOP(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        // Place le fenetre au centre de la fenetre parent
        this.setLocationRelativeTo(parent);

        // Récupère le modèle de la JTable pour y ajouter les nouvelles données
        this.fenMere = (FenetreEntrepotPlanning) parent;
        JTable tablePlanning = fenMere.getTablEntrepotEnvoi();
        this.modelP = (ModelPlanning) tablePlanning.getModel();
        this.listOP = modelP.getListOP();

        indexSelectedOP = tablePlanning.convertRowIndexToModel(tablePlanning.getSelectedRow());
        selectedOP = listOP.get(indexSelectedOP);
        this.txtNom.setText(selectedOP.getNomOP());
        this.txtNb.setText(String.valueOf(selectedOP.getNbIpadsPrevu()));
        this.dtDebut.setDate(selectedOP.getDateDebut());
        this.dtFin.setDate(selectedOP.getDateFin());
    }

    public DialogueModifOP(java.awt.Frame parent, boolean modal, int longueur, int largeur) {
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
        txtNom = new javax.swing.JTextField();
        txtNb = new javax.swing.JTextField();
        dtDebut = new com.toedter.calendar.JDateChooser();
        dtFin = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modifier une opération");
        setMinimumSize(new java.awt.Dimension(400, 300));

        labelNomFenetre.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        labelNomFenetre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNomFenetre.setText("Modifier une OP");
        getContentPane().add(labelNomFenetre, java.awt.BorderLayout.NORTH);

        btnRetour.setText("Retour");
        btnRetour.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRetourMouseClicked(evt);
            }
        });
        jPanel1.add(btnRetour);

        btnOK.setText("OK");
        btnOK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOKMouseClicked(evt);
            }
        });
        jPanel1.add(btnOK);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        java.awt.GridBagLayout panelCentreLayout = new java.awt.GridBagLayout();
        panelCentreLayout.columnWidths = new int[] {0, 0, 0, 0, 0};
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

        txtNom.setPreferredSize(new java.awt.Dimension(100, 28));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        panelCentre.add(txtNom, gridBagConstraints);

        txtNb.setPreferredSize(new java.awt.Dimension(100, 28));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        panelCentre.add(txtNb, gridBagConstraints);

        dtDebut.setDateFormatString("dd/MM/yy");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        panelCentre.add(dtDebut, gridBagConstraints);

        dtFin.setDateFormatString("dd/MM/yy");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        panelCentre.add(dtFin, gridBagConstraints);

        getContentPane().add(panelCentre, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRetourMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRetourMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnRetourMouseClicked

    private void btnOKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOKMouseClicked

        String donnee = "", oldonnee = "";// use to add data in database

        boolean changed = false;
        // Vérifie si changement de nom d'OP
        String nomOP = this.txtNom.getText();
        oldonnee = "nom_op+" + selectedOP.getNomOP() + "+";
        if (!nomOP.equals("")) {
            if (!selectedOP.getNomOP().equals(nomOP)) {
                this.selectedOP.setNomOP(this.txtNom.getText());
                donnee = "nom_op+" + this.txtNom.getText() + "+";
                test = 1;
                changed = true;
            }
        }

        //Récupère le nombre d'iPad alloués à l'OP si correct, 0 sinon
        int nbIpads = -1;
        if (!this.txtNb.getText().equals("")) {
            try {
                nbIpads = Integer.parseInt(this.txtNb.getText());
            } catch (Exception e) {
                System.out.println("L'argument rentre n'est pas un entier");
            }
        }

        // Vérifie si changement de nb d'iPad
        if (!selectedOP.getNbIpadsPrevu().equals(nbIpads)) {
            if (nbIpads >= 0) {
                this.selectedOP.setNbIpadsPrevu(nbIpads);
                if (test == 1) {
                    donnee = donnee + "nombre_ipads+" + this.txtNb.getText() + "+";
                    test = 2;
                } else {
                    donnee = "nombre_ipads+" + this.txtNb.getText() + "+";
                    test = 3;
                }
                changed = true;
            }
        }


        // Vérifie si changement de date de début
        Date dateDebut = this.dtDebut.getDate();
        if ((selectedOP.getDateDebut() == null) && ((dateDebut == null))) {
            test = 0;
        } else {
            if (!(dateDebut == null)) {
                if (selectedOP.getDateDebut() == null) {
                    this.selectedOP.setDateDebut(dateDebut);
                    changed = true;
                    System.out.println("ok1");
                    donnee = formatDate(donnee, dateDebut, "date_debut+", test);
                    test = 3;
                } else {
                    if (!selectedOP.getDateDebut().equals(dateDebut)) {
                        this.selectedOP.setDateDebut(dateDebut);
                        changed = true;
                        System.out.println("ok2");
                        donnee = formatDate(donnee, dateDebut, "date_debut+", test);
                        test = 3;
                    }
                }

            } else {
                this.selectedOP.setDateDebut(dateDebut);
                changed = true;
                System.out.println("ok3");
                donnee = formatDate(donnee, dateDebut, "date_debut+", test);
                test = 3;
            }
        }


        // Vérifie si changement de date de fin
        Date dateFin = this.dtFin.getDate();
        if ((selectedOP.getDateFin() == null) && ((dateFin == null))) {
            test = 0;
        } else {
            if (!(dateFin == null)) {
                if (selectedOP.getDateFin() == null) {
                    this.selectedOP.setDateFin(dateFin);
                    changed = true;
                    donnee = formatDate(donnee, dateFin, "date_fin+", test);
                } else {
                    if (!selectedOP.getDateFin().equals(dateFin)) {
                        this.selectedOP.setDateFin(dateFin);
                        changed = true;
                        donnee = formatDate(donnee, dateFin, "date_fin+", test);
                    }
                }
            } else {
                this.selectedOP.setDateFin(dateFin);
                changed = true;
                donnee = formatDate(donnee, dateFin, "date_fin+", test);
            }
        }

        if (changed) {
            //Modifie l'OP sélectionnée dans la JTable
            this.listOP.set(this.indexSelectedOP, this.selectedOP);
            this.modelP.fireTableRowsUpdated(this.indexSelectedOP, this.indexSelectedOP);

            //Modification de la nouvelle OP dans la BDD?
            InstructionSql modif_op = new InstructionSql();
            modif_op.Update_Operation(donnee, oldonnee);
        }
        //Suppression de la fenetre
        this.dispose();
    }//GEN-LAST:event_btnOKMouseClicked
    public String formatDate(String donnee, Date date, String nomdate, int test) {
        if ((test == 1) || (test == 2) || (test == 3)) {
            return donnee + nomdate + (date == null ? ""
                    : new SimpleDateFormat("yy/MM/dd").format(date)) + "+";
        } else {
            return nomdate + (date == null ? ""
                    : new SimpleDateFormat("yy/MM/dd").format(date)) + "+";
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOK;
    private javax.swing.JButton btnRetour;
    private com.toedter.calendar.JDateChooser dtDebut;
    private com.toedter.calendar.JDateChooser dtFin;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelDateDebut;
    private javax.swing.JLabel labelDateFin;
    private javax.swing.JLabel labelNb;
    private javax.swing.JLabel labelNom;
    private javax.swing.JLabel labelNomFenetre;
    private javax.swing.JPanel panelCentre;
    private javax.swing.JTextField txtNb;
    private javax.swing.JTextField txtNom;
    // End of variables declaration//GEN-END:variables
}