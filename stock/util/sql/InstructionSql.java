/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.util.sql;

import java.sql.Statement;
import java.util.Scanner;
import stock.util.connexion.connexion_bdd;
/**
 *
 * @author magency
 */
public class InstructionSql {
    
    public void Envoi_data(String datasql){

    
                    System.out.println("MySQL Connect Example.");

        Statement instruction_sql;
        
        try {
            connexion_bdd bdd = new connexion_bdd();
            instruction_sql = bdd.getStatementServer();
            instruction_sql.executeUpdate(datasql);
            bdd.closeall();
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }
 
///////////////////////////////////////////////
    String test = "";
    String ajout_data = "INSERT INTO ";
    String datab = "(";
    String update_data = "";
    String delete_data = "";

    public  void Ajout_Ipad(String data) {

        //data = "Adadef1+Aadedf1+Aadedf1+Aaddef1+2012-06-01+34+";
        Scanner scan = new Scanner(data).useDelimiter("\\+");
        String[] str = new String[6];
        
        int j = 0;
        while(scan.hasNext()){             
                str[j]=scan.next();
                System.out.println(str[j]);
                 j++;
        }

        if ((!str[0].equals(""))) {
            ajout_data = ajout_data + "`IPADS` (`numero_serie`,";
            datab = datab + "'" + str[0] + "',";
        }else{
            ajout_data = ajout_data + "`numero_serie`,";
            datab = datab + "NULL,";
        }
        
        if (!str[1].equals(""))  {
            ajout_data = ajout_data + "`udid`,";
            datab = datab + "'" + str[1] + "',";
        } else {
            ajout_data = ajout_data + "`udid`,";
            datab = datab + "NULL,";        
        }
        
        if ((!str[2].equals("")))  {
            ajout_data = ajout_data + "`code_rfid`,";
            datab = datab + "'" + str[2] + "',";
        } else{
            ajout_data = ajout_data + "`code_rfid`,";
            datab = datab + "NULL,";        
        }
        
        if ((!str[3].equals("")))  {
            ajout_data = ajout_data + "`etiquette`,";
            datab = datab + "'" + str[3] + "',";
        } else{
            ajout_data = ajout_data + "`etiquette`,";
            datab = datab + "NULL,";        
        }
        
        if ((!str[4].equals("")))  {
            ajout_data = ajout_data + "`date_achat`,";
            datab = datab + "'" + str[4] + "',";
        } else{
            ajout_data = ajout_data + "`date_achat`,";
            datab = datab + "NULL,";        
        }
        
        if ((!str[5].equals("")))  {
            ajout_data = ajout_data + "`duree_garantie`";
            datab = datab + "'" + str[5] + "'";
        } else {
            ajout_data = ajout_data + "`duree_garantie`";
            datab = datab + "NULL";
        }
       
        System.out.println("ok2");
       datab= datab + ") "; 
       ajout_data = ajout_data + ") VALUES "+datab; 
       System.out.println(ajout_data);
       Envoi_data(ajout_data) ;
       ajout_data = "";
        
    }
    
    
    //Ajout_Serveur
    public  void Ajout_Serveur(String data) {

        //data = "Adadef1+Aadedf1+Aadedf1+Aaddef1+2012-06-01+34+";
        Scanner scan = new Scanner(data).useDelimiter("\\+");
        String[] str = new String[6];
        
        int j = 0;
        while(scan.hasNext()){             
                str[j]=scan.next();
                System.out.println(str[j]);
                 j++;
        }

        if ((!str[0].equals(""))) {
            ajout_data = ajout_data + "`IPADS` (`numero_serie`,";
            datab = datab + "'" + str[0] + "',";
        }else{
            ajout_data = ajout_data + "`numero_serie`,";
            datab = datab + "NULL,";
        }
        
        if (!str[1].equals(""))  {
            ajout_data = ajout_data + "`udid`,";
            datab = datab + "'" + str[1] + "',";
        } else {
            ajout_data = ajout_data + "`udid`,";
            datab = datab + "NULL,";        
        }
        
        if ((!str[2].equals("")))  {
            ajout_data = ajout_data + "`code_rfid`,";
            datab = datab + "'" + str[2] + "',";
        } else{
            ajout_data = ajout_data + "`code_rfid`,";
            datab = datab + "NULL,";        
        }
        
        if ((!str[3].equals("")))  {
            ajout_data = ajout_data + "`etiquette`,";
            datab = datab + "'" + str[3] + "',";
        } else{
            ajout_data = ajout_data + "`etiquette`,";
            datab = datab + "NULL,";        
        }
        
        if ((!str[4].equals("")))  {
            ajout_data = ajout_data + "`date_achat`,";
            datab = datab + "'" + str[4] + "',";
        } else{
            ajout_data = ajout_data + "`date_achat`,";
            datab = datab + "NULL,";        
        }
        
        if ((!str[5].equals("")))  {
            ajout_data = ajout_data + "`duree_garantie`";
            datab = datab + "'" + str[5] + "'";
        } else {
            ajout_data = ajout_data + "`duree_garantie`";
            datab = datab + "NULL";
        }
       
        System.out.println("ok2");
       datab= datab + ") "; 
       ajout_data = ajout_data + ") VALUES "+datab; 
       System.out.println(ajout_data);
       Envoi_data(ajout_data) ;
       ajout_data = "";
        
    }
    
    
    
    
    
    public  void Ajout_Conteneur(String data){

            ajout_data = ajout_data + "`CONTENEUR` (`nom_conteneur`)";
            datab = "('" + data + "')";


       ajout_data = ajout_data + " VALUES "+datab; 
       System.out.println(ajout_data);
       Envoi_data(ajout_data) ;
       ajout_data = "";
    }
    
    public  void Ajout_Operation(String data){
        
        //data = "davy+++15+";
         System.out.println(data);
        Scanner scan = new Scanner(data).useDelimiter("\\+");
        String[] test = new String[4];
        
        int j = 0;
        while(scan.hasNext()){             
                test[j]=scan.next();
                 j++;
        }

//        for (String element : test ) {
//            System.out.println(element);
//        }

        if  ((!test[0].equals(null))||(!test[0].equals(""))) {
            ajout_data = ajout_data + "`OPE` (`nom_op`,";
            datab = datab + "'" + test[0] + "',";
        }else{
            ajout_data = ajout_data + "`nom_op`,";
            datab = datab + "NULL,";
        }
                    System.out.println(!test[2].equals(null));
        if (!test[2].equals("")) {
            System.out.println(";)");
            ajout_data = ajout_data + "`date_debut`,";
            datab = datab + "'" + test[2] + "',";
        } else {
            System.out.println(";(");
            ajout_data = ajout_data + "`date_debut`,";
            datab = datab + "NULL,";        
        }
 
         if (!test[3].equals("")) {
            ajout_data = ajout_data + "`date_fin`,";
            
            datab = datab + "'" + test[3] + "',";
        } else {
            ajout_data = ajout_data + "`date_fin`,";
            datab = datab + "NULL,";        
        }
 
        if (!test[1].equals("")) {
            ajout_data = ajout_data + "`nombre_ipads`";
            datab = datab + "'" + test[1] + "'";
        } else {
            ajout_data = ajout_data + "`nombre_ipads`";
            datab = datab + "NULL";        
        }
                
       datab= datab + ") "; 
       ajout_data = ajout_data + ") VALUES "+datab; 
       System.out.println(ajout_data);
       Envoi_data(ajout_data) ;
       ajout_data = "";

    }    
    
     public  String Update_Conteneur(String data){
        data = "id_conteneur+555+SAMARCHE+";// nomdata1+nomdata_A_modifié1+nom_data_modifié1
                                                    //+nomdata2+nom_data_modifié2  :::::::3 ou 5 donn
        Scanner scan = new Scanner(data).useDelimiter("\\+");
        String[] str = new String[5];
        
        int j = 0;
        while(scan.hasNext()){             
                str[j]=scan.next();
                System.out.println(str[j]);
                 j++;
        }
        update_data = update_data + "";
        if (j == 3){
        update_data = update_data +  "`CONTENEUR` SET `"
                +str[0]+ "`= "+  "'" + str[2] + "'"+"WHERE "
                + "`" + str[0] + "` = "+ "'" + str[1]+"'" ;
        return update_data;
        }else{
        update_data = update_data +  "`CONTENEUR` SET `"
                +str[0]+ "`= "+  "'" + str[2] + "',`" +str[3]+ "`= "+  "'" + str[4] +"' WHERE "
                + "`" + str[0] + "` = "+ "'" + str[1]+"'" ;            
            System.out.println(update_data);
        return update_data;
        }  
        
    
    }   

     public  void Update_Ipad(String newdata, String olddata){
        //data = "numero_serie+d1+a1+id+a2+udid+a3+tag_id+a4+date_achat+2012-07-01+duree_garantie+66+";
                    // nomdata1+nomdata_A_modifié1+nom_data_modifié1+nomdata2+nom_data_modifié2.....  :::::::3 ou 12 donn
        // data="tag_id+dd1+OKI";
         
        Scanner scan_newdata = new Scanner(newdata).useDelimiter("\\+");
        Scanner scan_olddata = new Scanner(olddata).useDelimiter("\\+");
        String[] newvalues = new String[13];
        String[] oldvalues = new String[2];
        
        int j = 0, k= 0;
        while(scan_newdata.hasNext()){             
                newvalues[j]=scan_newdata.next();
                 j++;
        }
        while(scan_olddata.hasNext()){             
                oldvalues[k]=scan_olddata.next();
                k++;
        }
        k = 0;
                update_data = "UPDATE `IPADS` SET ";
        j = j/2;
        while (j != 0){
         if (j == 1)
             update_data =  update_data + "`"+newvalues[k]+ "`= "+  "'" + newvalues[k+1]+ "' ";
         else    
            update_data = update_data + "`"+ newvalues[k]+ "`= "+  "'" + newvalues[k+1]+ "', ";
        j--;
        k = k+1;
        k++;
        }
        update_data = update_data + "WHERE " + "`" + oldvalues[0] + "` = "+ "'" + oldvalues[1]+"'" ;
        
                System.out.println(update_data);
        Envoi_data(update_data) ;
        update_data ="";
//        update_data = update_data + "";
//        if (j == 3){
//        update_data = update_data +  "`IPADS` SET `"
//                +str[0]+ "`= "+  "'" + str[2] + "'"+"WHERE "
//                + "`" + str[0] + "` = "+ "'" + str[1]+"'" ;
//        return update_data;
//        }else{
//        update_data = update_data +  "`IPADS` SET `"
//                +str[0]+ "`= "+  "'" + str[2] + "',`" +str[3]+ "`= "+  "'" + str[4]+"' ,`"
//                +str[5]+ "`= "+  "'" + str[6] + "',`" +str[7]+ "`= "+  "'" + str[8]+"' ,`"
//                +str[9]+ "`= "+  "'" + str[10] + "',`" +str[11]+ "`= "+  "'" + str[12] + "' WHERE "
//                + "`" + str[0] + "` = "+ "'" + str[1]+"'" ;            
//            System.out.println(update_data);
//        return update_data;
//        }  
        
    
    }     

     
     
     
     
     public  void Update_Serveur(String newdata, String olddata){
         //data = "numero_serie+d1+a1+id+a2+udid+a3+tag_id+a4+date_achat+2012-07-01+duree_garantie+66+";
                     // nomdata1+nomdata_A_modifié1+nom_data_modifié1+nomdata2+nom_data_modifié2.....  :::::::3 ou 12 donn
         // data="tag_id+dd1+OKI";
          
         Scanner scan_newdata = new Scanner(newdata).useDelimiter("\\+");
         Scanner scan_olddata = new Scanner(olddata).useDelimiter("\\+");
         String[] newvalues = new String[13];
         String[] oldvalues = new String[2];
         
         int j = 0, k= 0;
         while(scan_newdata.hasNext()){             
                 newvalues[j]=scan_newdata.next();
                  j++;
         }
         while(scan_olddata.hasNext()){             
                 oldvalues[k]=scan_olddata.next();
                 k++;
         }
         k = 0;
                 update_data = "UPDATE `IPADS` SET ";
         j = j/2;
         while (j != 0){
          if (j == 1)
              update_data =  update_data + "`"+newvalues[k]+ "`= "+  "'" + newvalues[k+1]+ "' ";
          else    
             update_data = update_data + "`"+ newvalues[k]+ "`= "+  "'" + newvalues[k+1]+ "', ";
         j--;
         k = k+1;
         k++;
         }
         update_data = update_data + "WHERE " + "`" + oldvalues[0] + "` = "+ "'" + oldvalues[1]+"'" ;
         
                 System.out.println(update_data);
         Envoi_data(update_data) ;
         update_data ="";
//         update_data = update_data + "";
//         if (j == 3){
//         update_data = update_data +  "`IPADS` SET `"
//                 +str[0]+ "`= "+  "'" + str[2] + "'"+"WHERE "
//                 + "`" + str[0] + "` = "+ "'" + str[1]+"'" ;
//         return update_data;
//         }else{
//         update_data = update_data +  "`IPADS` SET `"
//                 +str[0]+ "`= "+  "'" + str[2] + "',`" +str[3]+ "`= "+  "'" + str[4]+"' ,`"
//                 +str[5]+ "`= "+  "'" + str[6] + "',`" +str[7]+ "`= "+  "'" + str[8]+"' ,`"
//                 +str[9]+ "`= "+  "'" + str[10] + "',`" +str[11]+ "`= "+  "'" + str[12] + "' WHERE "
//                 + "`" + str[0] + "` = "+ "'" + str[1]+"'" ;            
//             System.out.println(update_data);
//         return update_data;
//         }  
         
     
     }     

     
     
     
     
     
     
     /* OBLIGATION QUE L'OPE AIT UN NOM*/
     public  void Update_Operation(String newdata, String olddata){
        //newdata = "nom_op+OKII+OPE_TEST+date_debut+2012-07-01+date_fin+2012-07-01+nombre_ipads+666+";
                    // nom_op+davy+nombre_ipads+66+date_debut+10/07/12+date_fin+17/07/12+  et nom_op+nomOP
         //data="nom_op+davy+OKI+"; nom_op+nomOPn+nombre_ipads+45+date_debut+11/07/12+date_fin+20/07/12+
         
        Scanner scan_newdata = new Scanner(newdata).useDelimiter("\\+");
        Scanner scan_olddata = new Scanner(olddata).useDelimiter("\\+");
        String[] newvalues = new String[9];
        String[] oldvalues = new String[2];
        
        int j = 0, k= 0;
        while(scan_newdata.hasNext()){             
                newvalues[j]=scan_newdata.next();
                 j++;
        }
        while(scan_olddata.hasNext()){             
                oldvalues[k]=scan_olddata.next();
                k++;
        }
        k = 0;
        
        update_data = "UPDATE `OPE` SET ";
        j = j/2;
        while (j != 0){
         if (j == 1)
             update_data =  update_data + "`"+newvalues[k]+ "`= "+  "'" + newvalues[k+1]+ "' ";
         else    
            update_data = update_data + "`"+ newvalues[k]+ "`= "+  "'" + newvalues[k+1]+ "', ";
        j--;
        k = k+1;
        k++;
        }
        update_data = update_data + "WHERE " + "`" + oldvalues[0] + "` = "+ "'" + oldvalues[1]+"'" ; 
//        if (j == 3){
//        update_data = update_data +  "`OPE` SET `"
//                +newvalues[0]+ "`= "+  "'" + newvalues[2] + "'"+ "WHERE "
//                + "`" + newvalues[0] + "` = "+ "'" + newvalues[1]+"'" ;
//        //return update_data;
//        }else{
//        update_data = update_data +  "`OPE` SET `"
//                +newvalues[0]+ "`= "+  "'" + newvalues[2] + "',`" +newvalues[3]+ "`= "+  "'" + newvalues[4]+"' ,`"
//                +newvalues[5]+ "`= "+  "'" + newvalues[6] + "',`" +newvalues[7]+ "`= "+  "'" + newvalues[8]+"' WHERE "
//                + "`" + newvalues[0] + "` = "+ "'" + newvalues[1]+"'" ;            
//            System.out.println(update_data);
//        //return update_data;
//        }  
        System.out.println(update_data);
        Envoi_data(update_data) ;
        update_data ="";
    
    }      

     public void Delete_Operation(String dataSup){
        delete_data = "DELETE FROM `OPE` WHERE `OPE`.`nom_op` = \'"+dataSup+"\'"; 
        System.out.println(delete_data);
        Envoi_data(delete_data) ;
        delete_data ="";
     }
     
          public void Supp_Conteneur(String dataSup){
        delete_data = "DELETE FROM `CONTENEUR` WHERE `CONTENEUR`.`nom_conteneur` = \'"+dataSup+"\'"; 
        System.out.println(delete_data);
        Envoi_data(delete_data) ;
        delete_data ="";
     }
    
}

