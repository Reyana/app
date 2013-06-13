/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.util.dial;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListCellRenderer;
import stock.base.Conteneur;
import stock.base.Ipad;
import stock.base.Serveur;
import stock.base.OP;
import stock.menu.entrepot.FenetreMenuEntrepot;
import stock.menu.entrepot.envoi.FenetreEntrepotEnvoi;
import stock.menu.entrepot.planning.FenetreEntrepotPlanning;
import stock.util.connexion.connexion_bdd;
import stock.util.sql.SQL_function;



/**
 *
 * @author Magency
 */

public class DialogueChoixOP extends javax.swing.JDialog {
    /**
	 * 
	 */
 

    private static final long serialVersionUID = 1L;
    private FenetreMenuEntrepot fenMere;
    private List<OP> listOP = new ArrayList<OP>();
    private final String btnclicked;
    private String dbname = null;
    String Nom_OP;
    /**
     * Creates new form NewJDialog
     */
    public DialogueChoixOP(String name_button, java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        
        // Place le fenetre au centre de la fenetre parent
        this.setLocationRelativeTo(parent);
        
        // Recupere le modele de la JTable
        this.fenMere = (FenetreMenuEntrepot)parent;
        this.btnclicked=name_button;
        if(name_button.equalsIgnoreCase("entrepotEnvoi"))
        {
            this.labelMilieu1.setText("Choisir l'OP vers laquelle envoyer les iPads et les serveurs: ");
        }
        else
        {
            this.labelMilieu1.setText("Choisir l'OP à réceptionner : ");
            this.panelCreerOP.setVisible(false);
        }
        this.initListes();
        
        
    }
    
    public DialogueChoixOP(String name_button,java.awt.Frame parent, boolean modal,
                        int longueur, int largeur) {
        this(name_button,parent, modal);     
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
        labelMilieu1 = new javax.swing.JLabel();
        panelOP = new javax.swing.JPanel();
        cbOP = new javax.swing.JComboBox();
        txt_etat_OP = new javax.swing.JLabel();
        panelCreerOP = new javax.swing.JPanel();
        labelMilieu2 = new javax.swing.JLabel();
        btnVersPlanning = new javax.swing.JButton();
        panelBoutons = new javax.swing.JPanel();
        btnRetour = new javax.swing.JButton();
        btnOK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Choix de l'OP");
        setMinimumSize(new java.awt.Dimension(400, 300));

        labelTitre.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelTitre.setText("SELECTION D'UNE OP");
        labelTitre.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        panelTitre.add(labelTitre);

        getContentPane().add(panelTitre, java.awt.BorderLayout.NORTH);

        panelCentre.setLayout(new java.awt.GridBagLayout());

        labelMilieu1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelMilieu1.setText("Choisir l'OP à réceptionner : ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 5);
        panelCentre.add(labelMilieu1, gridBagConstraints);

        panelOP.setLayout(new java.awt.GridBagLayout());

        cbOP.setMinimumSize(new java.awt.Dimension(100, 27));
        cbOP.setPreferredSize(new java.awt.Dimension(100, 27));
        cbOP.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbOPItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        panelOP.add(cbOP, gridBagConstraints);
        DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
        dlcr.setHorizontalAlignment(DefaultListCellRenderer.LEFT);
        this.cbOP.setRenderer(dlcr);

        txt_etat_OP.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        panelOP.add(txt_etat_OP, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 20, 10);
        panelCentre.add(panelOP, gridBagConstraints);

        panelCreerOP.setLayout(new java.awt.GridBagLayout());

        labelMilieu2.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelMilieu2.setText("Si l'OP recherchée n'est pas dans la liste :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 5);
        panelCreerOP.add(labelMilieu2, gridBagConstraints);

        btnVersPlanning.setText("Créer une nouvelle OP");
        btnVersPlanning.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVersPlanningMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 20, 5);
        panelCreerOP.add(btnVersPlanning, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        panelCentre.add(panelCreerOP, gridBagConstraints);

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
        if (chargerinfoOP()){
            if (this.btnclicked.equalsIgnoreCase("entrepotEnvoi"))
            {
                //Memes modifications dans la BDD               
                FenetreEntrepotEnvoi fEntrepotEnvoi = new FenetreEntrepotEnvoi(listOP.get(this.cbOP.getSelectedIndex()),this.getWidth(), this.getHeight());
                fEntrepotEnvoi.setVisible(true);
                this.fenMere.dispose();
                //Suppression de la fenetre
                this.dispose();
            }
            else
            {
               DialogueChoixImport dChoixImport = new DialogueChoixImport(this.fenMere, true, 
                                this.fenMere.getWidth(), this.fenMere.getHeight(), 
                                listOP.get(this.cbOP.getSelectedIndex()), dbname, "entrepotReception");
               dChoixImport.setVisible(true);
               
               //Suppression de la fenetre
                this.dispose();
            }
        
        }
//        else
//        {
//           // Dialogue indiquant que il n'y a rien dans la box
//            String avert1 = "L'OP présente dans la base locale est différente de celle choisi";
//            String avert2 = "L'OP présente est : "+Nom_OP;
//            DialogueAvertissement warning = new DialogueAvertissement(this, true, avert1,avert2);
//            warning.setVisible(true);
//        }    
    }//GEN-LAST:event_btnOKMouseClicked

    private void btnVersPlanningMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVersPlanningMouseClicked
            FenetreEntrepotPlanning fEntrepotPlanning = new FenetreEntrepotPlanning(this.getWidth(), this.getHeight());
            fEntrepotPlanning.setVisible(true);
            this.fenMere.dispose();
            this.dispose();
    }//GEN-LAST:event_btnVersPlanningMouseClicked

    private void cbOPItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbOPItemStateChanged
        setavertissementOP();
    }//GEN-LAST:event_cbOPItemStateChanged
    

    private void initListes()
    {
        int i;
        listOP.clear();
        connexion_bdd bdd = new connexion_bdd();
        ResultSet result = null;
        if (this.btnclicked.equalsIgnoreCase("entrepotEnvoi")){
            result = SQL_function.getdataop(bdd.getserver().getstatement());
        }else{
            String request = "select * from Ope where confirmation_envoi = '1'";
            try {
                result = bdd.getserver().getstatement().executeQuery(request);
            } catch (SQLException ex) {
                System.out.println("erreur requete initliste (dialoguechoisop): " + ex.getMessage());
            }
        }
        try {
            while (result.next()){
                
                OP mon_OP = new OP(result.getString("nom_op"),result.getInt("nombre_ipads"),result.getInt("nombre_serveurs"),result.getDate("date_debut"),result.getDate("date_fin"),result.getInt("confirmation_envoi"));
                listOP.add(mon_OP);
            }
            bdd.closeall();
        } catch (SQLException ex) {
            System.out.println("erreur data op (dialoguechoisOPenvoi): " + ex.getMessage());
        }
        
        // Initialises la liste des op√©rations s√©lectionnables dans la combobox
        for(i = 0; i < listOP.size(); i++)
        {
            this.cbOP.addItem(listOP.get(i).getNomOP());
        }
    }
    
    private void setavertissementOP(){
        if (listOP.get(this.cbOP.getSelectedIndex()).getconfenv() == 1 && this.btnclicked.equalsIgnoreCase("entrepotEnvoi")){
            this.txt_etat_OP.setText("OP deja envoyee");
        }else{
            this.txt_etat_OP.setText("");
        }
    }
    
    private OP getOPcour(){
        return listOP.get(this.cbOP.getSelectedIndex());
    }
    
    private boolean chargerinfoOP(){
        OP op = getOPcour(); 
        if (op.getconfenv() == 1){
            try {
                connexion_bdd bdd = new connexion_bdd();
                dbname = bdd.getlocal().getdatabasename();
                String request = "select CONTENEUR.nom_conteneur from OPE_CONTENEUR, CONTENEUR, OPE where "
                                + "OPE.nom_op = '" + op.getNomOP() + "' "
                                + "and OPE.id = OPE_CONTENEUR.id_op "
                                + "and CONTENEUR.id = OPE_CONTENEUR.id_conteneur";
                
                
                Nom_OP = SQL_function.get_NomOp_enBddLocale(bdd.getlocal().getstatement());
//                if (!Nom_OP.equals(listOP.get(this.cbOP.getSelectedIndex()).getNomOP()))
//                {   
//                    return false;
//                }
//                else
                {
                    ResultSet result = bdd.getStatementServer().executeQuery(request);
                    while (result.next()){
                        Conteneur new_conteneur = new Conteneur(result.getString("nom_conteneur" ));
                        op.addConteneur(new_conteneur);
                    }
                    for (Conteneur C : op.getListConteneur()){
                        request = "select IPADS.* from IPADS_CONTENEURS , IPADS"
                                + " where IPADS_CONTENEURS.id_conteneur IN (Select id from CONTENEUR where nom_conteneur = '" + C.getName() + "')"
                                + " and IPADS.id = IPADS_CONTENEURS.id_ipad ";
                                result = bdd.getStatementServer().executeQuery(request);
                                while (result.next()){
                                    Ipad new_ipad = new Ipad(result.getInt("id"),
                                                            result.getString("numero_serie"),
                                                            result.getString("udid"),
                                                            result.getString("code_rfid"),
                                                            result.getString("etiquette"),
                                                            result.getDate("date_achat"),
                                                            result.getString("duree_garantie"));
                                    C.ajouterIpad(new_ipad);
                                }
                    }
                    bdd.closeall();
                }
            } catch (SQLException ex) {
                System.out.println("erreur chargement data op (dialoguechoixOPenvoi): " + ex.getMessage());
                return false ;  
            }
        }
        
        
        return true ;
    }
        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnOK;
        private javax.swing.JButton btnRetour;
        private javax.swing.JButton btnVersPlanning;
        private javax.swing.JComboBox cbOP;
        private javax.swing.JLabel labelMilieu1;
        private javax.swing.JLabel labelMilieu2;
        private javax.swing.JLabel labelTitre;
        private javax.swing.JPanel panelBoutons;
        private javax.swing.JPanel panelCentre;
        private javax.swing.JPanel panelCreerOP;
        private javax.swing.JPanel panelOP;
        private javax.swing.JPanel panelTitre;
        private javax.swing.JLabel txt_etat_OP;
        // End of variables declaration//GEN-END:variables
    } 
    
    


 
