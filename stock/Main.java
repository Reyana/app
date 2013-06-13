/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stock;

import stock.base.Conteneur;
import stock.base.Ipad;
import stock.base.OP;
import stock.base.Serveur;
import stock.menu.FenetreMenuPrincipal;
import stock.menu.entrepot.FenetreMenuEntrepot;
import stock.menu.entrepot.envoi.FenetreEntrepotEnvoi;
import stock.menu.entrepot.list.FenetreEntrepotListeiPads;
import stock.menu.entrepot.recep.FenetreEntrepotReception;


/**
 *
 * @author Magency
 */
public class Main
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
               //DialogMDP fMDP = new DialogMDP();
               // fMDP.setVisible(true);
                FenetreMenuPrincipal fMenuPrincipal = new FenetreMenuPrincipal();
                fMenuPrincipal.setVisible(true);
           //     FenetreMenuEntrepot fme = new FenetreMenuEntrepot();
             //   fme.setVisible(true);
                /* Debut ajouts test */
               OP op = new OP("OPdeTEST");
               int tmp = 0;
        Ipad testIpad0 = new Ipad("sn" + String.valueOf(tmp), "udid" + String.valueOf(tmp), "rfid" + String.valueOf(tmp));
        tmp++;
        Ipad testIpad1 = new Ipad("sn" + String.valueOf(tmp), "udid" + String.valueOf(tmp), "rfid" + String.valueOf(tmp));
        tmp++;
        Ipad testIpad2 = new Ipad("sn" + String.valueOf(tmp), "udid" + String.valueOf(tmp), "rfid" + String.valueOf(tmp));
        tmp++;
        Ipad testIpad3 = new Ipad("sn" + String.valueOf(tmp), "udid" + String.valueOf(tmp), "rfid" + String.valueOf(tmp));
        tmp++;
        Ipad testIpad4 = new Ipad("sn" + String.valueOf(tmp), "udid" + String.valueOf(tmp), "rfid" + String.valueOf(tmp));
        tmp++;
        
        Serveur testServeur0 = new Serveur("sn" + String.valueOf(tmp), "udid" + String.valueOf(tmp), "rfid" + String.valueOf(tmp));
        tmp++;
        Serveur testServeur1 = new Serveur("sn" + String.valueOf(tmp), "udid" + String.valueOf(tmp), "rfid" + String.valueOf(tmp));
        tmp++;
        Serveur testServeur2 = new Serveur("sn" + String.valueOf(tmp), "udid" + String.valueOf(tmp), "rfid" + String.valueOf(tmp));
        tmp++;
        Serveur testServeur3 = new Serveur("sn" + String.valueOf(tmp), "udid" + String.valueOf(tmp), "rfid" + String.valueOf(tmp));
        tmp++;
        Serveur testServeur4 = new Serveur("sn" + String.valueOf(tmp), "udid" + String.valueOf(tmp), "rfid" + String.valueOf(tmp));
        tmp++;
         
        
        Conteneur testConteneur0 = new Conteneur("chariot" + String.valueOf(tmp));
        tmp++;
        Conteneur testConteneur1 = new Conteneur("chariot" + String.valueOf(tmp));
        tmp++;
        Conteneur testConteneur2 = new Conteneur("chariot" + String.valueOf(tmp));
        tmp++;
        
        
        testConteneur0.ajouterIpad(testIpad0);
        testConteneur0.ajouterIpad(testIpad1);
        testConteneur1.ajouterIpad(testIpad2);
        testConteneur2.ajouterIpad(testIpad3);
        testConteneur2.ajouterIpad(testIpad4);
        
        
        
    	testConteneur0.ajouterServeur(testServeur0);
        testConteneur0.ajouterServeur(testServeur1);
        testConteneur1.ajouterServeur(testServeur2);
        testConteneur2.ajouterServeur(testServeur3);
        testConteneur2.ajouterServeur(testServeur4);
         
        
        op.addConteneur(testConteneur0);
        op.addConteneur(testConteneur1);
        op.addConteneur(testConteneur2);
/* Fin ajouts tests */ 
     /*           FenetreEntrepotReception fEntrepotReception = new FenetreEntrepotReception(op);
                fEntrepotReception.setVisible(true);
                FenetreEntrepotEnvoi fEntrepotEnvoi = new FenetreEntrepotEnvoi(op);
                fEntrepotEnvoi.setVisible(true);
                stock.menu.entrepot.planning.FenetreEntrepotPlanning fEntrepotPlanning = new stock.menu.entrepot.planning.FenetreEntrepotPlanning();
                fEntrepotPlanning.setVisible(true);
                FenetreEntrepotListeiPads fEntrepotListe = new FenetreEntrepotListeiPads();
                fEntrepotListe.setVisible(true);
                stock.menu.op.recep.FenetreOPReception fOPReception = new stock.menu.op.recep.FenetreOPReception(op);
                fOPReception.setVisible(true);
*/
            }
        });
    }
}