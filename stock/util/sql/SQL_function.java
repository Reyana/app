/*
 To change this template, choose Tools | Templates
 and open the template in the editor.
 */
package stock.util.sql;



import com.mysql.jdbc.Statement;

/*
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
*/


import stock.util.connexion.connexion_bdd;
import stock.util.connexion.serv_connect;
import exception.bash_exception;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//Changé par moi
import org.apache.derby.impl.jdbc.EmbedStatement;
import stock.base.Conteneur;
import stock.base.Ipad;
import stock.base.OP;
import stock.base.Serveur;
import stock.util.connexion.local_connect;
//import stock.util.connexion.serv_connect;

/**
 *
 * @author magency01
 */
public abstract class SQL_function {
    
    public static boolean create_data_base_loc(local_connect local, String nomOP){
        
       String request;
       ResultSet result;
       serv_connect server = null;
       int i;
       
       // creation de statement
        Statement Magency_state = server.getstatement();
        EmbedStatement Local_state = local.getstatement();
        
        ArrayList<coulomns_types> type_table = new ArrayList<coulomns_types>();
        try {
            
            SQL_function.erase(local);
            
            // creation de la table conteneur si elle n'existe pas 
            creation_copie_table(server.getdatabasename(),"CONTENEUR",Local_state,Magency_state,type_table);

             // remplissage des tables local
             remplir_table(server.getdatabasename(), "CONTENEUR",Local_state,Magency_state,nomOP,type_table);
            
            type_table.clear();
            // creation de la table IPADS si elle n'existe pas 
            creation_copie_table(server.getdatabasename(),"IPADS",Local_state,Magency_state,type_table);
  
             // remplissage des tables local
             remplir_table(server.getdatabasename(), "IPADS",Local_state,Magency_state,nomOP,type_table);
             
            type_table.clear();
            // creation de la table IPADS si elle n'existe pas 
            creation_copie_table(server.getdatabasename(),"IPADS_CONTENEURS",Local_state,Magency_state,type_table);
            
             // remplissage des tables local
             remplir_table(server.getdatabasename(), "IPADS_CONTENEURS",Local_state,Magency_state,nomOP,type_table);
            
            type_table.clear();
            
            // creation de la table OPE si elle n'existe pas 
            creation_copie_table(server.getdatabasename(),"OPE",Local_state,Magency_state,type_table);
            
            // remplissage des tables local
             remplir_table(server.getdatabasename(), "OPE",Local_state,Magency_state,nomOP,type_table);
            
            type_table.clear();

            // creation de la table IPADS si elle n'existe pas 
            creation_copie_table(server.getdatabasename(),"IPADS_NON_DEPLOYABLES",Local_state,Magency_state,type_table);
            mettre_manquant(Local_state, nomOP);
                    
            
            return true;
        } catch (Exception ex) {
            System.out.println("error requete: "+ex.getMessage());
            return false;
        }
    }
    
    public static void erase(local_connect local){
        EmbedStatement Local_state = local.getstatement();
        ResultSet result;
        ArrayList<String>  table= new ArrayList<String>();
        int i=1;
        try {
            
            result = Local_state.executeQuery("select TABLENAME from sys.SYSTABLES where TABLETYPE != 'S'");
            
            while(result.next()){
                table.add(result.getString("TABLENAME"));
            }
            
            for (String element : table){
                System.out.println(element);  
                Local_state.execute("drop table " + element);
            }
            
            
             System.out.println("all table deleted");
        } catch (Exception ex) {
            System.out.println("error delete :" + ex.getMessage());
        }
        
    } 
    
    public static void creation_copie_table(String database,
            String name_table,
            EmbedStatement Local_state,
            Statement magency_state,
            ArrayList<coulomns_types> table_type){
            ResultSet result;
            String request;
            int i;
            try{
                request ="select TABLENAME from sys.systables where TABLENAME= '" + name_table +"'";
                result = Local_state.executeQuery(request);
                if (!result.next()){
                    // creation de la table sous le model des table du serveur
                    request = "SELECT COLUMN_NAME, DATA_TYPE, CHARACTER_MAXIMUM_LENGTH, IS_NULLABLE, COLUMN_KEY, COLUMN_DEFAULT "
                            + "from information_schema.COLUMNS  "
                            + "Where TABLE_NAME = '" + name_table + "' AND TABLE_SCHEMA = '" + database + "'";
                    result = magency_state.executeQuery(request);
                    request = "CREATE TABLE " + name_table + "(";
                    i=0;
                    List<String> primary_key = new ArrayList<String>();
                    while(result.next()){
                        // gere les virgule
                        if (i==0){
                             i++;
                        }else{
                            request = request + ","; 
                        }
                        // creation de la requete avec les teste des type
                        coulomns_types ma_col = new coulomns_types() ;
                        request = request + result.getString("COLUMN_NAME") + " " + result.getString("DATA_TYPE");
                        
                        ma_col.setcolname(result.getString("COLUMN_NAME"));
                        ma_col.setcoltype(result.getString("DATA_TYPE"));
                        
                        
                        table_type.add(ma_col);
                        
                        if(result.getString("CHARACTER_MAXIMUM_LENGTH") != null)
                            request = request + "("+result.getString("CHARACTER_MAXIMUM_LENGTH")+") ";
                        else
                            request = request + " ";

                        if(result.getString("IS_NULLABLE").contentEquals("NO")){ 
                            request = request + "NOT NULL ";
                        }else{
                           if(result.getString("COLUMN_DEFAULT") == null){
                                request = request + "DEFAULT " + result.getString("COLUMN_DEFAULT") + " ";
                            } 
                        }
                        

                        if(result.getString("COLUMN_KEY").contentEquals("UNI"))
                            request = request + "UNIQUE";
                        else if(result.getString("COLUMN_KEY").contentEquals("PRI"))
                            //request = request + "PRIMARY KEY";
                            primary_key.add(result.getString("COLUMN_NAME"));
   
                    }
                    if (i==0){
                        System.out.println("table" + name_table +" doesn't exists");
                        return;
                    }

                    if (!primary_key.isEmpty()){
                            request = request + ",PRIMARY KEY (";
                            for (i = 0 ; i < primary_key.size() ; i ++){
                                if (i < primary_key.size()-1)
                                    request = request + primary_key.get(i) +",";
                                else 
                                    request = request + primary_key.get(i);
                            }
                            request = request + ")";
                    }
                    request = request + ")";
                    Local_state.execute(request);
                    
                    System.out.println("table " + name_table + " crée");
                }
               
            }catch(Exception e){
                System.out.println("erreur creation copie :" + e.getMessage());
            }
            

    }
    
    
    
     public static void remplir_table(String database, String table_name,EmbedStatement Local_state,
    		 							Statement magency_state,
                                        String OP,
                                        ArrayList<coulomns_types> table_type){
            ResultSet result,result_sys;
            String request,request_sys;
     
            int i,j;
            try {
            // requete port avoir les information d'une op    
            request = "select distinct " + table_name + ".* from  IPADS , CONTENEUR , IPADS_CONTENEURS , OPE, OPE_CONTENEUR"
                    + " where IPADS.id = IPADS_CONTENEURS.id_ipad"
                    + " and IPADS_CONTENEURS.id_conteneur = CONTENEUR.id "
                    + " and CONTENEUR.id = OPE_CONTENEUR.id_conteneur "
                    + " and OPE.id = OPE_CONTENEUR.id_op"
                    + " and OPE.nom_op  = '" + OP + "'" ;
            result = magency_state.executeQuery(request);

            request = "insert into " + table_name + " values";
            i = 0;
            while (result.next()){
                
                if (i==0){
                    i++;
                    request = request + "(";
                }else
                    request = request + ") ,(";
                for (j = 0 ; j < table_type.size() ; j++){ 
                    if(!table_type.get(j).gettype().equalsIgnoreCase("int") && result.getString(table_type.get(j).getcolname()) != null){
                        request = request + "'" + result.getString(table_type.get(j).getcolname()) + "'";
                    }else{
                        request = request + result.getString(table_type.get(j).getcolname());
                    }
                    if (j < table_type.size() - 1 ){
                        request = request + ", "; 
                    }
                }
            }
             
            request = request + ")";
            Local_state.execute(request);
            }catch (Exception e){
                System.out.println("erreur copie :" + e.getMessage());
            }
       }
     
    public static void creation_zip_bdd(String database, String path, String op, String OS){
        try {
            delete_dat(OS);
           
            creation_dat("IPADS","IPADS.dat");
            creation_dat("CONTENEUR","CONTENEUR.dat");
            creation_dat("IPADS_CONTENEURS","IPADS_CONTENEURS.dat");
            creation_dat("OPE","OPE.dat");
            creation_dat("IPADS_NON_DEPLOYABLES","IPADS_NON_DEPLOYABLES.dat");

            SimpleDateFormat formatter = new SimpleDateFormat ("dd-MM-yy" );
            String today = formatter.format(new Date());
           if (OS.equalsIgnoreCase("windows")){

                exe_commande_windows("del /F/S/Q  "+ path +op + "_" + today + "_database.zip ");                      
                String rar = "\"C:\\Program Files\\WinRAR\\RAR.exe\"";
               if (rechercherfile("C:\\",OS,"Program Files") == null){
                        rar = "\"C:\\Program Files (x86)\\WinRAR\\RAR.exe\"";
                        if (rechercherfile("C:\\",OS,"Program Files (x86)") == null)
                        rar = "\"C:\\Programmes\\WinRAR\\RAR.exe\"";
                }
                String dumpCommand = rar + " a \""+ path + op + "_" + today + "_database.zip\" IPADS.dat CONTENEUR.dat IPADS_CONTENEURS.dat OPE.dat IPADS_NON_DEPLOYABLES.dat" ;
                
                exec(dumpCommand);               

               if (rechercherfile("",OS,"historique") == null)
                    exe_commande_windows("mkdir historique");
              
               exe_commande_windows("del /F/S/Q  .\\historique\\"+ op + "_" + today + "_database.zip ");
               dumpCommand = rar + " a \".\\historique\\"+ op + "_" + today + "_database.zip\" IPADS.dat CONTENEUR.dat IPADS_CONTENEURS.dat OPE.dat IPADS_NON_DEPLOYABLES.dat";
               SQL_function.exec(dumpCommand); 
               
           }else{
               String dumpCommand = "rm -rf  \""+ path + "/"+ op + "_" + today + "_database.zip\" ";
               SQL_function.exec(dumpCommand);

               dumpCommand = "zip -r \""+ path + "/"+ op + "_" + today + "_database.zip\" IPADS.dat CONTENEUR.dat IPADS_CONTENEURS.dat OPE.dat IPADS_NON_DEPLOYABLES.dat" ;
               SQL_function.exec(dumpCommand);



               dumpCommand = "mkdir -p historique";
               SQL_function.exec(dumpCommand);

               dumpCommand = "rm -rf  \"./historique/"+ op + "_" + today + "_database.zip\" ";
               SQL_function.exec(dumpCommand);

               dumpCommand = "zip -r \"./historique/"+ op + "_" + today + "_database.zip\" IPADS.dat CONTENEUR.dat IPADS_CONTENEURS.dat OPE.dat IPADS_NON_DEPLOYABLES.dat";
               SQL_function.exec(dumpCommand);
           }
        } catch (Exception ex) {
            Logger.getLogger(SQL_function.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
    private static void exe_commande_windows(String cmd){
        try {
            Process proc ;
            ProcessBuilder processbuilder;
             processbuilder = new ProcessBuilder ("cmd","/c",cmd);                      
             proc = processbuilder.redirectErrorStream(true).start(); 
             BufferedReader buf = new BufferedReader(new InputStreamReader(proc.getInputStream()));
             String term;
             while((term = buf.readLine())!= null)
                 System.out.println(term);
            if (proc.getErrorStream().read() != -1)
                throw new bash_exception(cmd);

            proc.getErrorStream().close();
            proc.getInputStream().close();
            proc.getOutputStream().close();
            proc.waitFor();
        } catch (IOException ex) {
            Logger.getLogger(SQL_function.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            System.out.println("kill process erreur (exe_commande_windows): " + ex.getMessage());
        }
    }
     
     
    private static void delete_dat(String OS) throws Exception {
         Runtime rt = Runtime.getRuntime();
         String dumpCommand;
         if (OS.equalsIgnoreCase("windows")){
            ProcessBuilder processbuilder = new ProcessBuilder ("cmd","/c","dir /B");                      
            final Process proc = processbuilder.redirectErrorStream(true).start(); 
            if (proc.getErrorStream().read() != -1)
                throw new bash_exception("dir");
            ArrayList<String> filelist = new ArrayList<String>();
            BufferedReader buf = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String temps;
                    while ((temps = buf.readLine()) != null){
                        if (temps.endsWith(".dat")){
                            filelist.add(temps);
                        }
                    }
            proc.getErrorStream().close();
            proc.getInputStream().close();
            proc.getOutputStream().close();
            proc.destroy();
            for(String file : filelist){
                SQL_function.exe_commande_windows("del /F /S /Q  "+ file);
            }
         }else{
            dumpCommand = "ls";
            Process proc = rt.exec(dumpCommand);
            ArrayList<String> filelist = new ArrayList<String>();
            if (proc.getErrorStream().read() != -1)
                throw new bash_exception(dumpCommand);
            BufferedReader buf = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String temps;
                    while ((temps = buf.readLine()) != null){
                        if (temps.endsWith(".dat")){
                            filelist.add(temps);
                        }
                    }
            proc.getErrorStream().close();
            proc.getInputStream().close();
            proc.getOutputStream().close();
            proc.destroy();
            for(String file : filelist){
                dumpCommand = "rm -rf  "+ file;
                SQL_function.exec(dumpCommand);
            }            
         }

    }
     
     public static void creation_dat(String table_name, String file) throws Exception{
       connexion_bdd bdd = new connexion_bdd();
        java.sql.PreparedStatement ps=  bdd.getlocal().getconnection().prepareStatement("CALL SYSCS_UTIL.SYSCS_EXPORT_TABLE" +
        "(?,?,?,?,?,?)");
        ps.setString(1,null);
        ps.setString(2,table_name);
        ps.setString(3,file);
        ps.setString(4,";");
        ps.setString(5,null);
        ps.setString(6,null);
        ps.execute();
        bdd.closeall();
         
     }
     
     public static void importation_dat(String table_name, String file) {
        try {
            connexion_bdd bdd = new connexion_bdd();
            java.sql.PreparedStatement ps =  bdd.getlocal().getconnection().prepareStatement("CALL SYSCS_UTIL.SYSCS_IMPORT_DATA" +
            "(?,?,?,?,?,?,?,?,?)");
            ps.setString(1,null);
            ps.setString(2,table_name);
            ps.setString(3,null);
            ps.setString(4,null);
            ps.setString(5,file);
            ps.setString(6,";");
            ps.setString(7,null);
            ps.setString(8,null);
            ps.setString(9,"1");
            ps.execute();
            bdd.closeall();
        } catch (SQLException ex) {
           System.out.println("erreur .dat importation  (SQL_function.importation_dat) :" + file +" " +  ex.getMessage());
        }
         
     }
     
     public static void importation_zip(String database,String path,String OS) throws  Exception {
         String dumpCommand, filepath,pathzip;
        
        if (OS.equalsIgnoreCase("windows")){
            filepath = rechercherfile(path,OS,"database.zip");
            SQL_function.exe_commande_windows("move " + filepath + " .");

            pathzip = rechercherzip(OS);
            delete_dat(OS);
            String rar = "\"C:\\Program Files\\WinRAR\\RAR.exe\"";
                if (rechercherfile("C:\\",OS,"Program Files") == null){
                        rar = "\"C:\\Program Files (x86)\\WinRAR\\RAR.exe\"";
                        if (rechercherfile("C:\\",OS,"Program Files (x86)") == null)
                        rar = "\"C:\\Programmes\\WinRAR\\RAR.exe\"";
                }
            
            dumpCommand = rar + " e -O+ " + pathzip ;
            SQL_function.exec(dumpCommand); 
            SQL_function.exe_commande_windows("del /F/S/Q " + pathzip);

        }else{
            filepath = rechercherfile(path,OS,"database.zip");

            dumpCommand = "mv \"" + filepath + "\" .";
            SQL_function.exec(dumpCommand);

            pathzip = rechercherzip(OS);
            delete_dat(OS);
            dumpCommand = "unzip " + pathzip;
            SQL_function.exec(dumpCommand);
            dumpCommand = "rm -rf " + pathzip;
            SQL_function.exec(dumpCommand);
        }
        
         importation_dat("IPADS","IPADS.dat");
         importation_dat("CONTENEUR","CONTENEUR.dat");
         importation_dat("IPADS_CONTENEURS","IPADS_CONTENEURS.dat");
         importation_dat("OPE","OPE.dat");
         importation_dat("IPADS_NON_DEPLOYABLES","IPADS_NON_DEPLOYABLES.dat");
           

     }
     
     private static void exec(String cmd) throws Exception{
         Runtime rt = Runtime.getRuntime();
         Process proc = rt.exec(cmd);
         if (proc.getErrorStream().read() != -1)
             throw new bash_exception(cmd);
         proc.getErrorStream().close();
         proc.getInputStream().close();
         proc.getOutputStream().close();
         proc.destroy();
         
     }
     
     private static String rechercherfile(String path,String OS,String strcmp ) throws Exception{

         Runtime rt = Runtime.getRuntime();
         String dumpCommand;
         if (OS.equalsIgnoreCase("windows")){
             System.out.println("dir /B " + path);
            ProcessBuilder processbuilder = new ProcessBuilder ("cmd","/c","dir /b \"" + path +"\"");                      
            Process proc = processbuilder.redirectErrorStream(true).start(); 
            if (proc.getErrorStream().read() != -1)
                throw new bash_exception("dir");
            BufferedReader buf = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String temps;
                    while ((temps = buf.readLine()) != null && !temps.endsWith(strcmp)){
                    }
            proc.getErrorStream().close();
            proc.getInputStream().close();
            proc.getOutputStream().close();
            proc.destroy();
            if (temps == null)
                return null;
            return path + temps;            
         }else{
            dumpCommand= "ls " + path;
            Process proc = rt.exec(dumpCommand);
            if (proc.getErrorStream().read() != -1)
                throw new bash_exception(dumpCommand);
            BufferedReader buf = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String temps = "";
                    while ((temps = buf.readLine()) != null && !temps.endsWith(strcmp)){
                    }
            proc.getErrorStream().close();
            proc.getInputStream().close();
            proc.getOutputStream().close();
            proc.destroy();
            if (temps == null)
                return null;
            return path + "/" + temps;
         }
     }
     
     private static String rechercherzip(String OS) throws Exception{

         Runtime rt = Runtime.getRuntime();
         if(OS.equalsIgnoreCase("windows")){
            ProcessBuilder processbuilder = new ProcessBuilder ("cmd","/c","dir /B");                      
            final Process proc = processbuilder.redirectErrorStream(true).start(); 
            //dumpCommand= "dir " + path;
            //Process proc = rt.exec(dumpCommand);
            if (proc.getErrorStream().read() != -1)
                throw new bash_exception("dir");
            BufferedReader buf = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String temps = "";
                    while ((temps = buf.readLine()) != null && !temps.endsWith("database.zip")){

                    }
            proc.getErrorStream().close();
            proc.getInputStream().close();
            proc.getOutputStream().close();
            proc.destroy();
            return  temps;            
         }
            String dumpCommand = "ls ";
            Process proc = rt.exec(dumpCommand);
            if (proc.getErrorStream().read() != -1)
                throw new bash_exception(dumpCommand);
            BufferedReader buf = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String temps = "";
                    while ((temps = buf.readLine()) != null && !temps.endsWith("database.zip")){

                    }
            proc.getErrorStream().close();
            proc.getInputStream().close();
            proc.getOutputStream().close();
            proc.destroy();
            return  temps;
     }
     
     
     
     public static ResultSet getdataop(Statement embedStatement){
         String request = "SELECT * from OPE";
         try{
             ResultSet result = embedStatement.executeQuery(request);
             return result;
         }catch(Exception e){
             System.out.println("erreur mise a jour (getdataop): "+ e.getMessage());
             return null;
         }
     }


       /**
     * 
     * @param local
     * 
     * @Brief Met l'état de confirmation d'envoi en entrepôt à 2
     */
    public static void setconfenvBaseLocale(EmbedStatement local, String nom_op){
        try {
            String request = "UPDATE OPE SET confirmation_envoi = '2' WHERE OPE.nom_op ='"+nom_op +"'";
            local.executeUpdate(request);
        } catch (SQLException ex) {
            System.out.println("erreur mise a jour (getdataconteneur): " + ex.getMessage());
        }
     }
    
/**********************************************Conteneur*************************************************/

     
      public static ResultSet getdataconteneur_libre(Statement Magency){
         String request = "SELECT nom_conteneur FROM CONTENEUR "
                 + "WHERE CONTENEUR.id "
                 + "NOT IN (SELECT id_conteneur FROM OPE_CONTENEUR);";
         try{
             ResultSet result = Magency.executeQuery(request);
             return result;
         }catch(Exception e){
             System.out.println("erreur mise a jour (getdataconteneur): "+ e.getMessage());
             return null;
         }
     }
      
      
      
    public static List<Conteneur> getListConteneurs(Statement server){
         String request = "SELECT nom_conteneur FROM CONTENEUR;";
         return traiteRequeteListConteneur(server, request);
     }
            
            
            
    /**
     * 
     * @param local
     * @return La liste des conteneurs lies a l'OP de la base locale
     */ 
    public static List<Conteneur> getListConteneurs_Local(EmbedStatement local)
    {
         String request = "SELECT nom_conteneur FROM CONTENEUR ";
         return traiteRequeteListConteneur_Local(local, request);
    }
    
    
            
    /**
     * 
     * @param embedStatement
     * @param nom_Op Nom de l'OP dont on recupere la liste des conteneurs
     * 
     * @return La liste des conteneurs lies a l'OP
     */        
    public static List<Conteneur> getListConteneursLibresOp(Statement embedStatement, String nom_Op)
    {
         String request = "SELECT nom_conteneur FROM CONTENEUR WHERE "
                 + "CONTENEUR.id NOT IN (SELECT id_conteneur FROM OPE_CONTENEUR)"
                 + " UNION (SELECT nom_conteneur FROM CONTENEUR WHERE "
                 + "CONTENEUR.id IN (SELECT id_conteneur FROM OPE_CONTENEUR"
                 + " WHERE OPE_CONTENEUR.id_op IN "
                 + "(SELECT id FROM OPE WHERE OPE.nom_op = '"+nom_Op+"')))";
         return traiteRequeteListConteneur(embedStatement, request);
    }

    
    
    /**

     * 
     * @param server
     * @param requete La requete a executer
     * @return Une liste d'iPads contenus dans la base de donnees, vide si probleme
     */
    private static List<Conteneur> traiteRequeteListConteneur(Statement server, String requete)
    {
        List<Conteneur> listConteneurs = new ArrayList<Conteneur>();
        try
        {
            ResultSet result = server.executeQuery(requete);
            while (result.next()) {

                Conteneur conteneur = new Conteneur(result.getString("nom_conteneur"));
                listConteneurs.add(conteneur);
            }
        }
        catch(Exception e)
        {
            System.out.println("SQl_function.java traiteRequeteListConteneurs: requete incorrecte: "+ e.getMessage());
        }
        return listConteneurs;
    }
    
    
    
    /**
     * 
     * @param local
     * @param requete La requete a executer
     * @return Une liste d'iPads contenus dans la base de donnees, vide si probleme
     */
    private static List<Conteneur> traiteRequeteListConteneur_Local(EmbedStatement local, String requete)
    {
        List<Conteneur> listConteneurs = new ArrayList<Conteneur>();
        try
        {
            ResultSet result = local.executeQuery(requete);
            while (result.next()) {

                Conteneur conteneur = new Conteneur(result.getString("nom_conteneur"));
                listConteneurs.add(conteneur);
            }
        }
        catch(Exception e)
        {
            System.out.println("SQl_function.java traiteRequeteListConteneurs_Local: requete incorrecte: "+ e.getMessage());
        }
        return listConteneurs;
    }
    
    

     
/*************************************************Ipads*********************************/
     
     
     public static int actualisation_nombre_ipad_libre(Date date_deb, Date date_fin) throws SQLException{
         int total_ipads = 0, ipads_utilises = 0 , ipads_dead = 0;
         connexion_bdd bdd = new connexion_bdd();
         SimpleDateFormat formatter = new SimpleDateFormat ("yy/MM/dd" );
         String date_debut = formatter.format(date_deb);
         String date_retour = formatter.format(date_fin);
         
         // nombre ipad sur OPE
         String request = "Select count(IPADS.id) from IPADS,IPADS_CONTENEURS,OPE_CONTENEUR,OPE where"
                 + " IPADS.id = IPADS_CONTENEURS.id_ipad and"
                 + " IPADS_CONTENEURS.id_conteneur = OPE_CONTENEUR.id_conteneur and"
                 + " OPE_CONTENEUR.id_op = OPE.id and"
                 + " ((OPE.date_debut >= '" + date_debut 
                 + "' and OPE.date_debut <= '"+ date_retour + "')"
                 + " or (OPE.date_fin <= '" + date_retour + "' "
                 + "and OPE.date_fin >= '" + date_debut +  "')"
                 + " or (OPE.date_debut <= '" + date_debut
                 + "' and OPE.date_fin >= '" + date_retour + "'))";
         ResultSet result = bdd.getserver().getstatement().executeQuery(request);
          if (result.next())
              ipads_utilises = result.getInt(1);
          
          // nombre ipads non-focntinnels
          request = "Select count(id_ipad) from IPADS_NON_DEPLOYABLES";
          result = bdd.getserver().getstatement().executeQuery(request);
           if (result.next())
              ipads_dead = result.getInt(1);
         // nombre total d'ipads  
         request = "select count(IPADS.id) from IPADS";
         result = bdd.getserver().getstatement().executeQuery(request);
         if (result.next())
             total_ipads = result.getInt(1);
         bdd.closeall();
         return total_ipads - ipads_utilises - ipads_dead;
     }

     

    
    public static ResultSet getdataipads_libre(Statement Magency) {
        String request = "SELECT * FROM IPADS WHERE IPADS.id NOT IN"
                + " (SELECT id_ipad FROM IPADS_CONTENEURS) AND IPADS.id NOT IN"
                + " ( SELECT id_ipad FROM IPADS_NON_DEPLOYABLES)";
        try {
            ResultSet result = Magency.executeQuery(request);
            return result;
        } catch (Exception e) {
            System.out.println("erreur mise a jour (getdataconteneur): " + e.getMessage());
            return null;
        }
    }

        public static List<Ipad> getdataipads_libre_et_dans_ipad_nondeployables(Statement statement) {
        String request = "SELECT * FROM IPADS   WHERE IPADS.id "
                + "NOT IN (SELECT id_ipad FROM IPADS_CONTENEURS) AND IPADS.id "
                + "NOT IN (SELECT id_ipad FROM IPADS_NON_DEPLOYABLES)";

        return traiteRequeteListIpad(statement, request);
    }

      public static List<Ipad> getdataipads_libre_et_dans_ipad_nondeployables_en_local(EmbedStatement local) {
        String request =" SELECT * FROM IPADS LEFT JOIN IPADS_NON_DEPLOYABLES ON"
                + " IPADS.id = IPADS_NON_DEPLOYABLES.id_ipad WHERE"
                + " IPADS.id IN (SELECT id_ipad FROM IPADS_CONTENEURS )";
        return traiteRequeteListIpad_Local(local, request);
    }
        
      public static List<Ipad> getdataipads_libre_et_non_dans_ipad_nondeployablesManquant_en_local(EmbedStatement local) {
        String request =" SELECT * FROM IPADS WHERE"
                + " IPADS.id NOT IN (SELECT id_ipad FROM "
                + "IPADS_NON_DEPLOYABLES WHERE IPADS_NON_DEPLOYABLES.etat= 'Manquant' )";
        return traiteRequeteListIpad_Local(local, request);
    }    
       
    /**
     * @return La liste de tous les iPads contenus dans la base de donnees
     */
    public static List<Ipad> getListIpadsBDD (Statement statement){
        String request = "SELECT IPADS.id, IPADS.numero_serie, IPADS.udid, IPADS.code_rfid, IPADS.etiquette, IPADS.date_achat, IPADS.duree_garantie "
                + "FROM IPADS ";
        return traiteRequeteListIpad(statement, request);
     }
  
    /**
   * 
   * @param Magency
   * 
   * @return La liste de tous les iPads contenus dans la base de donnees local
   */
  public static List<Ipad> getListIpadsBDD_Local(EmbedStatement local){
       String request = "SELECT * from IPADS";
       return traiteRequeteListIpad_Local(local, request);
   }
    
   
    
    /**
     * @return La liste de tous les iPads contenus dans la base de donnees server
     */
       public static List<Ipad> getListIpadsBDD_AvecEtat (Statement statement){
            List<Ipad> ListIpad = new ArrayList<Ipad>();
        try {
            String request = "SELECT * FROM IPADS LEFT JOIN IPADS_NON_DEPLOYABLES ON"
                     + " IPADS.id = IPADS_NON_DEPLOYABLES.id_ipad ";
            ResultSet result = statement.executeQuery(request);
            
            while(result.next()){
                Ipad iPad = new Ipad(result.getInt("id"),
                        result.getString("numero_serie"),
                        result.getString("udid"),
                        result.getString("code_rfid"),
                        result.getString("etiquette"),
                        result.getString("etat"));
                ListIpad.add(iPad);
            }
              
           
        } catch (SQLException ex) {
            Logger.getLogger(SQL_function.class.getName()).log(Level.SEVERE, null, ex);
        }
         return ListIpad;
     }     
     
    /**
     * @return La liste de tous les iPads contenus dans la base de donnees local
     */
    public static List<Ipad> getListIpadsBDD_AvecEtat_Local (EmbedStatement local){
            List<Ipad> ListIpad = new ArrayList<Ipad>();
        try {
            String request = "SELECT * FROM IPADS LEFT JOIN IPADS_NON_DEPLOYABLES ON"
                     + " IPADS.id = IPADS_NON_DEPLOYABLES.id_ipad ";
            ResultSet result = local.executeQuery(request);
            
            while(result.next()){
                Ipad iPad = new Ipad(result.getInt("id"),
                        result.getString("numero_serie"),
                        result.getString("udid"),
                        result.getString("code_rfid"),
                        result.getString("etiquette"),
                        result.getString("etat"));
                ListIpad.add(iPad);
            }
              
           
        } catch (SQLException ex) {
            Logger.getLogger(SQL_function.class.getName()).log(Level.SEVERE, null, ex);
        }
         return ListIpad;
     }     
            
    /**
     * @return La liste de tous les iPads stockes a l'entrepot contenus dans la base de donnees
     */
    public static List<Ipad> getListIpadsEntrepotBDD (Statement Magency){
        String request = "SELECT IPADS.id, IPADS.numero_serie, IPADS.udid, IPADS.code_rfid, IPADS.etiquette, IPADS.date_achat, IPADS.duree_garantie "
                + "FROM IPADS "
                + "WHERE IPADS.id NOT IN "
                    + "(SELECT IPADS_CONTENEURS.id_ipad "
                    + "FROM IPADS_CONTENEURS "
                    + "WHERE IPADS_CONTENEURS.id_conteneur IN "
                        + "(SELECT OPE_CONTENEUR.id_conteneur "
                        + "FROM OPE_CONTENEUR "
                        + "WHERE OPE_CONTENEUR.id_op IN "
                            + "(SELECT OPE.id "
                            + "FROM OPE "
                            + "WHERE OPE.confirmation_envoi = '1') "
                        + ") "
                + ") ";
        return traiteRequeteListIpad(Magency, request);
     }
    
    
    
    /**
     * @return La liste de tous les iPads en OP contenus dans la base de donnees
     */
    public static List<Ipad> getListIpadsOPBDD (Statement Magency){
        String request = "SELECT IPADS.id, IPADS.numero_serie, IPADS.udid, IPADS.code_rfid, IPADS.etiquette, IPADS.date_achat, IPADS.duree_garantie "
                + "FROM IPADS "
                + "WHERE IPADS.id IN "
                    + "(SELECT IPADS_CONTENEURS.id_ipad "
                    + "FROM IPADS_CONTENEURS "
                    + "WHERE IPADS_CONTENEURS.id_conteneur IN "
                        + "(SELECT OPE_CONTENEUR.id_conteneur "
                        + "FROM OPE_CONTENEUR "
                        + "WHERE OPE_CONTENEUR.id_op IN "
                            + "(SELECT OPE.id "
                            + "FROM OPE "
                            + "WHERE OPE.confirmation_envoi = '1') "
                        + ") "
                + ") ";
        return traiteRequeteListIpad(Magency, request);
     }
    
    
    /**
     * @return La liste de tous les iPads casses contenus dans la base de donnees
     */
    public static List<Ipad> getListIpadsCassesBDD (Statement statement)
    {
        String request = "SELECT IPADS.id, IPADS.numero_serie, IPADS.udid, IPADS.code_rfid, IPADS.etiquette, IPADS.date_achat, IPADS.duree_garantie "
                + "FROM IPADS "
                + "WHERE IPADS.id IN "
                + "(SELECT IPADS_NON_DEPLOYABLES.id_ipad "
                + "FROM IPADS_NON_DEPLOYABLES "
                + "WHERE IPADS_NON_DEPLOYABLES.etat = 'casse')";
        return traiteRequeteListIpad(statement, request);
     }
    
    
    
    /**
     * @return La liste de tous les iPads casses contenus dans la base de donnees
     */
    public static List<Ipad> getListIpadsCassesBDD_Local (EmbedStatement local)
    {
        String request = "SELECT IPADS.id, IPADS.numero_serie, IPADS.udid, IPADS.code_rfid, IPADS.etiquette, IPADS.date_achat, IPADS.duree_garantie "
                + "FROM IPADS "
                + "WHERE IPADS.id IN "
                + "(SELECT IPADS_NON_DEPLOYABLES.id_ipad "
                + "FROM IPADS_NON_DEPLOYABLES "
                + "WHERE IPADS_NON_DEPLOYABLES.etat = 'casse')";
        return traiteRequeteListIpad_Local(local, request);
     }
    

   

     
    /**
     * @return La liste de tous les iPads casses contenus dans la base de donnees
     */
    public static List<Ipad> getListIpadsManquantsBDD (Statement Magency){
        String request = "SELECT IPADS.id, IPADS.numero_serie, IPADS.udid, IPADS.code_rfid, IPADS.etiquette, IPADS.date_achat, IPADS.duree_garantie "
                + "FROM IPADS "
                + "WHERE IPADS.id IN "
                + "(SELECT IPADS_NON_DEPLOYABLES.id_ipad "
                + "FROM IPADS_NON_DEPLOYABLES "
                + "WHERE IPADS_NON_DEPLOYABLES.etat = 'manquant')"; 
        return traiteRequeteListIpad(Magency, request);
     }
    
    
    
    /**
     * @return La liste de tous les iPads casses contenus dans la base de donnees
     */
    public static List<Ipad> getListIpadsManquantsBDD_Local (EmbedStatement local){
        String request = "SELECT IPADS.id, IPADS.numero_serie, IPADS.udid, IPADS.code_rfid, IPADS.etiquette, IPADS.date_achat, IPADS.duree_garantie "

                + "FROM IPADS "
                + "WHERE IPADS.id IN "
                + "(SELECT IPADS_NON_DEPLOYABLES.id_ipad "
                + "FROM IPADS_NON_DEPLOYABLES "
                + "WHERE IPADS_NON_DEPLOYABLES.etat = 'Manquant')"; 
        return traiteRequeteListIpad_Local(local, request);
     }
    
    
    
    /**
     * @return La liste de tous les iPads casses contenus dans la base de donnees
     */
    public static List<Ipad> getListIpadsFonctionnelsBDD (Statement Magency){
        String request = "SELECT IPADS.id, IPADS.numero_serie, IPADS.udid, IPADS.code_rfid, IPADS.etiquette, IPADS.date_achat, IPADS.duree_garantie "
                + "FROM IPADS "
                + "WHERE IPADS.id NOT IN "
                + "(SELECT IPADS_NON_DEPLOYABLES.id_ipad "
                + "FROM IPADS_NON_DEPLOYABLES)";
        return traiteRequeteListIpad(Magency, request);
     }
    
    
    /**
     * @return La liste de tous les iPads casses contenus dans la base de donnees
     */
    public static List<Ipad> getListIpadsFonctionnelsBDD_Local (EmbedStatement local){
        String request = "SELECT IPADS.id, IPADS.numero_serie, IPADS.udid, IPADS.code_rfid, IPADS.etiquette, IPADS.date_achat, IPADS.duree_garantie "
                + "FROM IPADS "
                + "WHERE IPADS.id NOT IN "
                + "(SELECT IPADS_NON_DEPLOYABLES.id_ipad "
                + "FROM IPADS_NON_DEPLOYABLES)";
        return traiteRequeteListIpad_Local(local, request);
     }
    
    
    /**
     * @return La liste de tous les iPads casses stockes a l'entrepot contenus dans la base de donnees
     */
    public static List<Ipad> getListIpadsFonctionnelsEntrepotBDD (Statement Magency){
        String request = "SELECT IPADS.id, IPADS.numero_serie, IPADS.udid, IPADS.code_rfid, IPADS.etiquette, IPADS.date_achat, IPADS.duree_garantie "
                + "FROM IPADS "
                + "WHERE IPADS.id NOT IN "
                    + "(SELECT IPADS_NON_DEPLOYABLES.id_ipad "
                    + "FROM IPADS_NON_DEPLOYABLES) "
                + "AND IPADS.id NOT IN "
                    + "(SELECT IPADS_CONTENEURS.id_ipad "
                    + "FROM IPADS_CONTENEURS "
                    + "WHERE IPADS_CONTENEURS.id_conteneur IN "
                        + "(SELECT OPE_CONTENEUR.id_conteneur "
                        + "FROM OPE_CONTENEUR "
                        + "WHERE OPE_CONTENEUR.id_op IN "
                            + "(SELECT OPE.id "
                            + "FROM OPE "
                            + "WHERE OPE.confirmation_envoi = '1') "
                        + ") "
                + ") ";
        return traiteRequeteListIpad(Magency, request);
     }
    
    
    /**
     * @return La liste de tous les iPads casses en OP contenus dans la base de donnees

     */
    public static List<Ipad> getListIpadsFonctionnelsOPBDD (Statement Magency){
        String request = "SELECT IPADS.id, IPADS.numero_serie, IPADS.udid, IPADS.code_rfid, IPADS.etiquette, IPADS.date_achat, IPADS.duree_garantie "
                + "FROM IPADS "
                + "WHERE IPADS.id NOT IN "
                    + "(SELECT IPADS_NON_DEPLOYABLES.id_ipad "
                    + "FROM IPADS_NON_DEPLOYABLES) "
                + "AND IPADS.id IN "
                    + "(SELECT IPADS_CONTENEURS.id_ipad "
                    + "FROM IPADS_CONTENEURS "
                    + "WHERE IPADS_CONTENEURS.id_conteneur IN "
                        + "(SELECT OPE_CONTENEUR.id_conteneur "
                        + "FROM OPE_CONTENEUR "
                        + "WHERE OPE_CONTENEUR.id_op IN "
                            + "(SELECT OPE.id "
                            + "FROM OPE "
                            + "WHERE OPE.confirmation_envoi = '1') "
                        + ") "
                + ") ";
        return traiteRequeteListIpad(Magency, request);
     }
        
    
    
    /**
     * @return La liste de tous les iPads casses stockes a l'entrepot contenus dans la base de donnees
     */
    public static List<Ipad> getListIpadsCassesEntrepotBDD (Statement statement){
        String request = "SELECT IPADS.id, IPADS.numero_serie, IPADS.udid, IPADS.code_rfid, IPADS.etiquette, IPADS.date_achat, IPADS.duree_garantie "
                + "FROM IPADS_NON_DEPLOYABLES ,IPADS "
                + "WHERE IPADS_NON_DEPLOYABLES.etat = 'casse' "
                + "AND IPADS_NON_DEPLOYABLES.nom_op = 'entrepot' "
                + "AND IPADS_NON_DEPLOYABLES.id_ipad = IPADS.id"; 
        return traiteRequeteListIpad(statement, request);
     }
    
        
    
    /**
     * @return La liste de tous les iPads casses en OP contenus dans la base de donnees
     */
    public static List<Ipad> getListIpadsCassesOPBDD (Statement Magency){
        String request = "SELECT IPADS.id, IPADS.numero_serie, IPADS.udid, IPADS.code_rfid, IPADS.etiquette, IPADS.date_achat, IPADS.duree_garantie "
                + "FROM IPADS_NON_DEPLOYABLES ,IPADS "
                + "WHERE IPADS_NON_DEPLOYABLES.etat = 'casse' "
                + "AND IPADS_NON_DEPLOYABLES.nom_op <> 'entrepot' "
                + "AND IPADS_NON_DEPLOYABLES.id_ipad = IPADS.id";             
        return traiteRequeteListIpad(Magency, request);
     }

  
    
    
    /**
     * @return La liste de tous les iPads casses stockes a l'entrepot contenus dans la base de donnees

     */
    public static List<Ipad> getListIpadsManquantsEntrepotBDD (Statement Magency){
        String request = "SELECT IPADS.id, IPADS.numero_serie, IPADS.udid, IPADS.code_rfid, IPADS.etiquette, IPADS.date_achat, IPADS.duree_garantie "
                + "FROM IPADS_NON_DEPLOYABLES ,IPADS "
                + "WHERE IPADS_NON_DEPLOYABLES.etat = 'Manquant' "
                + "AND IPADS_NON_DEPLOYABLES.nom_op = 'entrepot' "
                + "AND IPADS_NON_DEPLOYABLES.id_ipad = IPADS.id"; 
        return traiteRequeteListIpad(Magency, request);
     }
    
    
    
    /**
     * @return La liste de tous les iPads casses en OP contenus dans la base de donnees
     */
    public static List<Ipad> getListIpadsManquantsOPBDD (Statement Magency){
        String request = "SELECT IPADS.id, IPADS.numero_serie, IPADS.udid, IPADS.code_rfid, IPADS.etiquette, IPADS.date_achat, IPADS.duree_garantie "
                + "FROM IPADS_NON_DEPLOYABLES ,IPADS "
                + "WHERE IPADS_NON_DEPLOYABLES.etat = 'Manquant' "
                + "AND IPADS_NON_DEPLOYABLES.nom_op <> 'entrepot' "
                + "AND IPADS_NON_DEPLOYABLES.id_ipad = IPADS.id"; 
        return traiteRequeteListIpad(Magency, request);
    }

    
       
    /**
     * 
     * @param statement
     * @param requete La requete a executer
     * @return Une liste d'iPads contenus dans la base de donnees, vide si probleme
     */
    private static List<Ipad> traiteRequeteListIpad(Statement statement, String requete)
    {
        List<Ipad> listIpad = new ArrayList<Ipad>();
        ResultSet result;
        try
        {
            result = statement.executeQuery(requete);
            while (result.next())
            {
                Ipad iPad = new Ipad(result.getInt("id"),
                        result.getString("numero_serie"),
                        result.getString("udid"),
                        result.getString("code_rfid"),
                        result.getString("etiquette"),
                        result.getDate("date_achat"),
                        result.getInt("duree_garantie") + " jours");
                listIpad.add(iPad);
            }
            if(!listIpad.isEmpty())
            {
                for(Ipad iPad : listIpad)
                {
                    String requeteNomOP = "SELECT OPE.nom_op "
                            + "FROM OPE "
                            + "WHERE OPE.id IN "
                                + "(SELECT OPE_CONTENEUR.id_op "
                                + "FROM OPE_CONTENEUR "
                                + "WHERE OPE_CONTENEUR.id_conteneur IN "
                                    + "(SELECT IPADS_CONTENEURS.id_conteneur "
                                    + "FROM IPADS_CONTENEURS "
                                    + "WHERE IPADS_CONTENEURS.id_ipad = " + iPad.getDbNum()
                                    + ") "
                                + ")";
                    result = statement.executeQuery(requeteNomOP);
                    if(result.next())
                    {
                        String nomOP = result.getString("nom_op") == null ? "":result.getString("nom_op");
                        iPad.setNomOp(nomOP);
                    }
                }
            }
            
            
        }
        catch(Exception e)
        {
            System.out.println("SQl_function.java traiteRequeteListIpad: requete incorrecte): "+ e.getMessage());
        }
        return listIpad;
    }
    
    
    
    /**

     * 
     * @param Magency

     * @param requete La requete a executer
     * @return Une liste d'iPads contenus dans la base de donnees, vide si probleme

     */
    private static List<Ipad> traiteRequeteListIpad_Local(EmbedStatement local, String requete)
    {
        List<Ipad> listIpad = new ArrayList<Ipad>();
        try
        {
            ResultSet result = local.executeQuery(requete);
            while (result.next())
            {
                Ipad iPad = new Ipad(result.getInt("id"),
                        result.getString("numero_serie"),
                        result.getString("udid"),
                        result.getString("code_rfid"),
                        result.getString("etiquette"),
                        result.getDate("date_achat"),
                        result.getInt("duree_garantie") + " jours");
                listIpad.add(iPad);
            }
        }
        catch(Exception e)
        {
            System.out.println("SQl_function.java traiteRequeteListIpad_Local: requete incorrecte): "+ e.getMessage());
        }
        return listIpad;
    }
    
    
    
    /**
     * 
     * @param Magency
     * @param idIpad id dans la base de donnees de l'iPad dont on cherche l'etat
     * @return La chaine de caractere "fonctionnel", "casse" ou "Manquant" selon l'etat
     */
    public static String getEtatIpad(Statement Magency, int idIpad)
    {
        String etat = "Fonctionnel";
        String request = "SELECT IPADS_NON_DEPLOYABLES.etat "
                + "FROM IPADS_NON_DEPLOYABLES "
                + "WHERE IPADS_NON_DEPLOYABLES.id_ipad = '" + idIpad + "'"; 
        try
        {
            ResultSet result = Magency.executeQuery(request);
            if (result.next())
            {
                etat = result.getString("etat");
            }
        }
        catch(Exception e)
        {
            System.out.println("SQl_function.java getEtatIpad: requete incorrecte): "+ e.getMessage());
        }
        return etat;
    }
    
    
    
    
    /**
     * 
     * @param local
     * @param idIpad id dans la base de donnees de l'iPad dont on cherche l'etat
     * @return La chaine de caractere "Fonctionnel", "Casse" ou "Manquant" selon l'etat
     */
    public static String getEtatIpad_Local(EmbedStatement local, int idIpad)
    {
        String etat = "Fonctionnel";
        String request = "SELECT IPADS_NON_DEPLOYABLES.etat "
                + "FROM IPADS_NON_DEPLOYABLES "
                + "WHERE IPADS_NON_DEPLOYABLES.id_ipad = " + idIpad; 
        try
        {
            ResultSet result = local.executeQuery(request);
            if (result.next())
            {
                etat = result.getString("etat");
            }
        }
        catch(Exception e)
        {
            System.out.println("SQl_function.java getEtatIpad_Local: requete incorrecte): "+ e.getMessage());
        }
        return etat;
    }
    
    
    /**
     * @brief Mets a jour l'etat de l'iPad dans la base de donnees
     * 
     * @param Magency
     * @param id id de l'iPad dont l'etat va etre modifie dans la BDD
     * @param ancienEtat Ancien etat de l'iPad
     * @param nouvelEtat Nouvel etat de l'iPad
     * @param emplacement Nom de l'OP (ou "entrepot") ou l'iPad a ete casse/perdu (mettre une String vide si pas besoin)
     */
    public static void changeEtatIpad(Statement Magency, int id, String ancienEtat, String nouvelEtat, String emplacement)
    {
        String requete;
        if(ancienEtat.equalsIgnoreCase("Fonctionnel"))
        {
            // Fonctionnel => Casse ou manquant
            requete = "INSERT INTO IPADS_NON_DEPLOYABLES(id_ipad, etat, nom_op) "
                    + "VALUES (" + id + ",'" + nouvelEtat + "', '" + emplacement + "')";
        }
        else
        {
            if(nouvelEtat.equalsIgnoreCase("Fonctionnel"))
            {
                // Casse ou manquant => Fonctionnel
                requete = "DELETE "
                        + "FROM IPADS_NON_DEPLOYABLES"
                        + " WHERE id_ipad = " + id;
            }
            else
            {
                // Casse ou manquant => Manquant ou casse
                requete = "UPDATE IPADS_NON_DEPLOYABLES "
                        + "SET etat = '" + nouvelEtat + "' "
                        + "WHERE id_ipad = " + id;
            }
        }
        try
        {
            Magency.executeUpdate(requete);
        }
        catch (SQLException ex)
        {
            System.out.println("erreur mise a jour de la base (SQL_function.changeEtatIpad): " + ex.getMessage());
        }
        
    }
    
    
    
    /**

     * @brief Mets a jour l'etat de l'iPad dans la base de donnees
     * 

     * @param Magency
     * @param id id de l'iPad dont l'etat va etre modifie dans la BDD

     * @param ancienEtat Ancien etat de l'iPad
     * @param nouvelEtat Nouvel etat de l'iPad

     * @param emplacement Nom de l'OP (ou "entrepot") ou l'iPad a ete casse/perdu (mettre une String vide si pas besoin)
     */
    public static void changeEtatIpad_Local(EmbedStatement local, int id, String ancienEtat, String nouvelEtat, String emplacement)
    {
        String requete;
        if(!ancienEtat.equalsIgnoreCase(nouvelEtat))
        {
            if(ancienEtat.equalsIgnoreCase("Fonctionnel"))
            {
                // Fonctionnel => Casse ou manquant
                requete = "INSERT INTO IPADS_NON_DEPLOYABLES(id_ipad, etat, nom_op) "
                        + "VALUES (" + id + ", '" + nouvelEtat + "', '" + emplacement + "')";
            }
            else
            {
                if(nouvelEtat.equalsIgnoreCase("Fonctionnel"))
                {
                    // Casse ou manquant => Fonctionnel
                    requete = "DELETE "
                            + "FROM IPADS_NON_DEPLOYABLES"
                            + " WHERE id_ipad = " + id;
                }
                else
                {
                    // Casse ou manquant => Manquant ou casse
                    requete = "UPDATE IPADS_NON_DEPLOYABLES "
                            + "SET etat = '" + nouvelEtat + "' "
                            + "WHERE id_ipad = " + id;
                }
            }
            try
            {
                local.executeUpdate(requete);
            }
            catch (SQLException ex)
            {
                System.out.println("erreur mise a jour de la base (SQL_function.changeEtatIpad_Local): " + ex.getMessage());
            }
        }
    }
    
    
    
    /**
     * @return L'iPad de la BDD avec l'id donne, null si pas trouve
     */
    public static Ipad getIpadFromId_BDD (Statement Magency, int id){
        Ipad iPad = null;
        String request = "SELECT IPADS.id, IPADS.numero_serie, IPADS.udid, IPADS.code_rfid, IPADS.etiquette, IPADS.date_achat, IPADS.duree_garantie "
                + "FROM IPADS "
                + "WHERE IPADS.id = " + id;
        List<Ipad> listIpad = traiteRequeteListIpad(Magency, request);
        if(!listIpad.isEmpty())
        {
            iPad = listIpad.get(0);
        }
            
        return iPad;
     }
    
    

    /**

     * @brief Supprime l'iPad de la BDD dont l'id est passe en parametre

     * @param Magency

     * @param id 

     */
    public static void removeIpadBDD(Statement Magency, int id)
    {
        // Casse ou manquant => Fonctionnel
        String requete = "DELETE "
                + "FROM IPADS"
                + " WHERE id = " + id;
        try
        {
            Magency.executeUpdate(requete);
        }
        catch (SQLException ex)
        {
            System.out.println("erreur mise a jour de la base (SQL_function.removeIpadBDD): " + ex.getMessage());
        }
    }
    
    
    
    public static List<Ipad> recuperation_iPads_non_Scanne (EmbedStatement local, String request ){
       try {
           local.executeQuery(request);
       } catch (SQLException ex) {
           System.out.println("SQl_function.java getEtatIpad_Local: requete incorrecte): "+ ex.getMessage());
       }
       return traiteRequeteListIpad_Local(local, request);
    }
   
    /**************************************Fin_Ipad*************************************/
 /****************************************************Serveurs**********************************************/
    /**
     * @return La liste de tous les iPads casses contenus dans la base de donnees
     */
    public static List<Serveur> getListServeursCassesBDD_Local (Statement embedStatement)
    {
        String request = "SELECT SERVEURS.id, SERVEURS.numero_serie, SERVEURS.udid, SERVEURS.code_rfid, SERVEURS.etiquette, SERVEURS.date_achat, SERVEURS.duree_garantie "
                + "FROM SERVEURS "
                + "WHERE SERVEURS.id IN "
                + "(SELECT SERVEURS_NON_DEPLOYABLES.id_serveur "
                + "FROM SERVEURS_NON_DEPLOYABLES ";
        return traiteRequeteListServeur_Local(embedStatement, request);
     }
  
    
    public static List<Serveur> getListServeursCassesBDD (Statement Magency)
    {
        String request = "SELECT SERVEURS.id, SERVEURS.numero_serie, SERVEURS.udid, SERVEURS.code_rfid, SERVEURS.etiquette, SERVEURS.date_achat, SERVEURS.duree_garantie "
                + "FROM SERVEURS "
                + "WHERE SERVEURS.id IN "
                + "(SELECT SERVEURS_NON_DEPLOYABLES.id_ipad "
                + "FROM SERVEURS_NON_DEPLOYABLES "
                + "WHERE SERVEURS_NON_DEPLOYABLES.etat = 'casse')";
        return traiteRequeteListServeur(Magency, request);
     }
    
    
    
     
     /**
     * 
     * @param Magency
     * 
     * @return La liste de tous les serveurs contenus dans la base de donnees local
     */
    public static List<Serveur> getListServeursBDD_Local(Statement embedStatement){
         String request = "SELECT * from SERVEURS";
         return traiteRequeteListServeur_Local(embedStatement, request);
     }
    
    
    
    
    /**
     * @return La liste de tous les serveurs casses stockes a l'entrepot contenus dans la base de donnees
     */
    public static List<Serveur> getListServeursCassesEntrepotBDD (Statement Magency){
        String request = "SELECT SERVEURS.id, SERVEURS.numero_serie, SERVEURS.udid, SERVEURS.code_rfid, SERVEURS.etiquette, SERVEURS.date_achat, SERVEURS.duree_garantie "
                + "FROM SERVEURS_NON_DEPLOYABLES ,SERVEURS "
                + "WHERE SERVEURS_NON_DEPLOYABLES.etat = 'casse' "
                + "AND SERVEURS_NON_DEPLOYABLES.nom_op = 'entrepot' "
                + "AND SERVEURS_NON_DEPLOYABLES.id_serveur = SERVEURS.id"; 
        return traiteRequeteListServeur(Magency, request);
     }
    
        
    
    /**
     * @return La liste de tous les sevreurs casses en OP contenus dans la base de donnees
     */
    public static List<Serveur> getListServeursCassesOPBDD (Statement Magency){
        String request =  "SELECT SERVEURS.id, SERVEURS.numero_serie, SERVEURS.udid, SERVEURS.code_rfid, SERVEURS.etiquette, SERVEURS.date_achat, SERVEURS.duree_garantie "
                + "FROM SERVEURS_NON_DEPLOYABLES ,SERVEURS "
                + "WHERE SERVEURS_NON_DEPLOYABLES.etat = 'casse' "
                + "AND SERVEURS_NON_DEPLOYABLES.nom_op = 'entrepot' "
                + "AND SERVEURS_NON_DEPLOYABLES.id_serveur = SERVEURS.id";             
        return traiteRequeteListServeur(Magency, request);
     }

  
    
    
    /**
     * @return La liste de tous les serveurs casses stockes a l'entrepot contenus dans la base de donnees
     */
    public static List<Serveur> getListServeursManquantsEntrepotBDD (Statement Magency){
        String request = "SELECT SERVEURS.id, SERVEURS.numero_serie, SERVEURS.udid, SERVEURS.code_rfid, SERVEURS.etiquette, SERVEURS.date_achat, SERVEURS.duree_garantie "
                + "FROM SERVEURS_NON_DEPLOYABLES ,SERVEURS "
                + "WHERE SERVEURS_NON_DEPLOYABLES.etat = 'Manquant' "
                + "AND SERVEURS_NON_DEPLOYABLES.nom_op = 'entrepot' "
                + "AND SERVEURS_NON_DEPLOYABLES.id_serveur = SERVEURS.id"; 
        return traiteRequeteListServeur(Magency, request);
     }
    
    
    
    /**
     * @return La liste de tous les serverus casses en OP contenus dans la base de donnees
     */
    public static List<Serveur> getListServeursManquantsOPBDD (Statement Magency){
        String request = "SELECT SERVEURS.id, SERVEURS.numero_serie, SERVEURS.udid, SERVEURS.code_rfid, SERVEURS.etiquette, SERVEURS.date_achat, SERVEURS.duree_garantie "
                + "FROM SERVEURS_NON_DEPLOYABLES ,SERVEURS "
                + "WHERE SERVEURS_NON_DEPLOYABLES.etat = 'Manquant' "
                + "AND SERVEURS_NON_DEPLOYABLES.nom_op <> 'entrepot' "
                + "AND SERVEURS_NON_DEPLOYABLES.id_serveur = SERVEURS.id"; 
        return traiteRequeteListServeur(Magency, request);
    }

    
       
    /**
     * 
     * @param Magency
     * @param requete La requete a executer
     * @return Une liste de serveurscontenus dans la base de donnees, vide si probleme
     */
    private static List<Serveur> traiteRequeteListServeur(Statement Magency, String requete)
    {
        List<Serveur> listServeur = new ArrayList<Serveur>();
        ResultSet result;
        try
        {
            result = Magency.executeQuery(requete);
            while (result.next())
            {
                Serveur sErveur = new Serveur(result.getInt("id"),
                        result.getString("numero_serie"),
                        result.getString("udid"),
                        result.getString("code_rfid"),
                        result.getString("etiquette"),
                        result.getDate("date_achat"),
                        result.getInt("duree_garantie") + " jours");
                listServeur.add(sErveur);
            }
            if(!listServeur.isEmpty())
            {
                for(Serveur sErveur : listServeur)
                {
                    String requeteNomOP = "SELECT OPE.nom_op "
                            + "FROM OPE "
                            + "WHERE OPE.id IN "
                                + "(SELECT OPE_CONTENEUR.id_op "
                                + "FROM OPE_CONTENEUR "
                                + "WHERE OPE_CONTENEUR.id_conteneur IN "
                                    + "(SELECT SERVEURS_CONTENEURS.id_conteneur "
                                    + "FROM SERVEURS_CONTENEURS "
                                    + "WHERE SERVEURS_CONTENEURS.id_serveur = " + sErveur.getDbNum()
                                    + ") "
                                + ")";
                    result = Magency.executeQuery(requeteNomOP);
                    if(result.next())
                    {
                        String nomOP = result.getString("nom_op") == null ? "":result.getString("nom_op");
                        sErveur.setNomOp(nomOP);
                    }
                }
            }
            
            
        }
        catch(Exception e)
        {
            System.out.println("SQl_function.java traiteRequeteListServeur: requete incorrecte): "+ e.getMessage());
        }
        return listServeur;
    }
    
    
    
    /**
     * 
     * @param Magency
     * @param requete La requete a executer
     * @return Une liste de serveurs contenus dans la base de donnees, vide si probleme
     */
    private static List<Serveur> traiteRequeteListServeur_Local(Statement local, String requete)
    {
        List<Serveur> listServeur = new ArrayList<Serveur>();
        try
        {
            ResultSet result = local.executeQuery(requete);
            while (result.next())
            {
                Serveur sErveur = new Serveur(result.getInt("id"),
                        result.getString("numero_serie"),
                        result.getString("udid"),
                        result.getString("code_rfid"),
                        result.getString("etiquette"),
                        result.getDate("date_achat"),
                        result.getInt("duree_garantie") + " jours");
                listServeur.add(sErveur);
            }
        }
        catch(Exception e)
        {
            System.out.println("SQl_function.java traiteRequeteListServeur_Local: requete incorrecte): "+ e.getMessage());
        }
        return listServeur;
    }
    
    
    
    /**
     * 
     * @param Magency
     * @param idServeur id dans la base de donnees du Serveur dont on cherche l'etat
     * @return La chaine de caractere "fonctionnel", "casse" ou "Manquant" selon l'etat
     */
    public static String getEtatServeur(Statement Magency, int idServeur)
    {
        String etat = "Fonctionnel";
        String request = "SELECT SERVEURS_NON_DEPLOYABLES.etat "
                + "FROM SERVEURS_NON_DEPLOYABLES "
                + "WHERE SERVEURS_NON_DEPLOYABLES.id_serveur = '" + idServeur + "'"; 
        try
        {
            ResultSet result = Magency.executeQuery(request);
            if (result.next())
            {
                etat = result.getString("etat");
            }
        }
        catch(Exception e)
        {
            System.out.println("SQl_function.java getEtatServeur: requete incorrecte): "+ e.getMessage());
        }
        return etat;
    }
    
    
    
    
    /**
     * 
     * @param local
     * @param idServeur id dans la base de donnees du serveurPad dont on cherche l'etat
     * @return La chaine de caractere "Fonctionnel", "Casse" ou "Manquant" selon l'etat
     */
    public static String getEtatServeur_Local(Statement local, int idServeur)
    {
        String etat = "Fonctionnel";
        String request = "SELECT SERVEURS_NON_DEPLOYABLES.etat "
                + "FROM SERVEURS_NON_DEPLOYABLES "
                + "WHERE SERVEURS_NON_DEPLOYABLES.id_serveur = " + idServeur; 
        try
        {
            ResultSet result = local.executeQuery(request);
            if (result.next())
            {
                etat = result.getString("etat");
            }
        }
        catch(Exception e)
        {
            System.out.println("SQl_function.java getEtatServeur_Local: requete incorrecte): "+ e.getMessage());
        }
        return etat;
    }
    
    
    /**
     * @brief Mets a jour l'etat du serveur dans la base de donnees
     * 
     * @param Magency
     * @param id id du serveur dont l'etat va etre modifie dans la BDD
     * @param ancienEtat Ancien etat du serveur
     * @param nouvelEtat Nouvel etat du serveur
     * @param emplacement Nom de l'OP (ou "entrepot") ou le serveur a ete casse/perdu (mettre une String vide si pas besoin)
     */
    public static void changeEtatServeur(Statement Magency, int id, String ancienEtat, String nouvelEtat, String emplacement)
    {
        String requete;
        if(ancienEtat.equalsIgnoreCase("Fonctionnel"))
        {
            // Fonctionnel => Casse ou manquant
            requete = "INSERT INTO SERVEURS_NON_DEPLOYABLES(id_serveur, etat, nom_op) "
                    + "VALUES (" + id + ",'" + nouvelEtat + "', '" + emplacement + "')";
        }
        else
        {
            if(nouvelEtat.equalsIgnoreCase("Fonctionnel"))
            {
                // Casse ou manquant => Fonctionnel
                requete = "DELETE "
                        + "FROM SERVEURS_NON_DEPLOYABLES"
                        + " WHERE id_serveur = " + id;
            }
            else
            {
                // Casse ou manquant => Manquant ou casse
                requete = "UPDATE SERVEURS_NON_DEPLOYABLES "
                        + "SET etat = '" + nouvelEtat + "' "
                        + "WHERE id_serveur = " + id;
            }
        }
        try
        {
            Magency.executeUpdate(requete);
        }
        catch (SQLException ex)
        {
            System.out.println("erreur mise a jour de la base (SQL_function.changeEtatServeur): " + ex.getMessage());
        }
        
    }
    
    
    
    /**
     * @brief Mets a jour l'etat du serveur dans la base de donnees
     * 
     * @param Magency
     * @param id id du serveur dont l'etat va etre modifie dans la BDD
     * @param ancienEtat Ancien etat du serveur
     * @param nouvelEtat Nouvel etat du serveur
     * @param emplacement Nom de l'OP (ou "entrepot") ou le serveur a ete casse/perdu (mettre une String vide si pas besoin)
     */
    public static void changeEtatServeur_Local(Statement local, int id, String ancienEtat, String nouvelEtat, String emplacement)
    {
        String requete;
        if(!ancienEtat.equalsIgnoreCase(nouvelEtat))
        {
            if(ancienEtat.equalsIgnoreCase("Fonctionnel"))
            {
                // Fonctionnel => Casse ou manquant
                requete = "INSERT INTO SERVEURS_NON_DEPLOYABLES(id_serveur, etat, nom_op) "
                        + "VALUES (" + id + ", '" + nouvelEtat + "', '" + emplacement + "')";
            }
            else
            {
                if(nouvelEtat.equalsIgnoreCase("Fonctionnel"))
                {
                    // Casse ou manquant => Fonctionnel
                    requete = "DELETE "
                            + "FROM SERVEURS_NON_DEPLOYABLES"
                            + " WHERE id_serveur = " + id;
                }
                else
                {
                    // Casse ou manquant => Manquant ou casse
                    requete = "UPDATE SERVEURS_NON_DEPLOYABLES "
                            + "SET etat = '" + nouvelEtat + "' "
                            + "WHERE id_serveur = " + id;
                }
            }
            try
            {
                local.executeUpdate(requete);
            }
            catch (SQLException ex)
            {
                System.out.println("erreur mise a jour de la base (SQL_function.changeEtatServeur_Local): " + ex.getMessage());
            }
        }
    }
    
    
    
    /**
     * @return le serveur de la BDD avec l'id donne, null si pas trouve
     */
    public static Serveur getServeurFromId_BDD (Statement Magency, int id){
        Serveur sErveur = null;
        String request = "SELECT SERVEURS.id, SERVEURS.numero_serie, SERVEURS.udid, SERVEURS.code_rfid, SERVEURS.etiquette, SERVEURS.date_achat, SERVEURS.duree_garantie "
                + "FROM SERVEURS "
                + "WHERE SERVEURS.id = " + id;
        List<Serveur> listServeur = traiteRequeteListServeur(Magency, request);
        if(!listServeur.isEmpty())
        {
            sErveur = listServeur.get(0);
        }
            
        return sErveur;
     }
    
    

    /**
     * @brief Supprime le serveur de la BDD dont l'id est passe en parametre
     * @param Magency
     * @param id 
     */
    public static void removeServeurBDD(Statement Magency, int id)
    {
        // Casse ou manquant => Fonctionnel
        String requete = "DELETE "
                + "FROM SERVEURS"
                + " WHERE id = " + id;
        try
        {
            Magency.executeUpdate(requete);
        }
        catch (SQLException ex)
        {
            System.out.println("erreur mise a jour de la base (SQL_function.removeServeurBDD): " + ex.getMessage());
        }
    }
     
    public static List<Serveur> getListServeursEntrepotBDD (Statement Magency){
        String request = "SELECT SERVEURS.id, SERVEURS.numero_serie, SERVEURS.udid, SERVEURS.code_rfid, SERVEURS.etiquette, SERVEURS.date_achat, SERVEURS.duree_garantie "
                + "FROM SERVEURS "
                + "WHERE SERVEURS.id NOT IN "
                    + "(SELECT SERVEURS_CONTENEURS.id_iserveur "
                    + "FROM SERVEURS_CONTENEURS "
                    + "WHERE SERVEURS_CONTENEURS.id_conteneur IN "
                        + "(SELECT OPE_CONTENEUR.id_conteneur "
                        + "FROM OPE_CONTENEUR "
                        + "WHERE OPE_CONTENEUR.id_op IN "
                            + "(SELECT OPE.id "
                            + "FROM OPE "
                            + "WHERE OPE.confirmation_envoi = '1') "
                        + ") "
                + ") ";
        return traiteRequeteListServeur(Magency, request);
     }
    
    
    /**
     * @return La liste de tous les serveurs casses contenus dans la base de donnees
     */
    public static List<Serveur> getListServeursFonctionnelsBDD (Statement Magency){
        String request = "SELECT SERVEURS.id, SERVEURS.numero_serie, SERVEURS.udid, SERVEURS.code_rfid, SERVEURS.etiquette, SERVEURS.date_achat, SERVEURS.duree_garantie "
                + "FROM SERVEURS "
                + "WHERE SERVEURS.id NOT IN "
                + "(SELECT SERVEURS_NON_DEPLOYABLES.id_serveur "
                + "FROM SERVEURS_NON_DEPLOYABLES)";
        return traiteRequeteListServeur(Magency, request);
     }
    
    
    /**
     * @return La liste de tous les iPads casses contenus dans la base de donnees
     */
    public static List<Serveur> getListServeursFonctionnelsBDD_Local (Statement local){
        String request =  "SELECT SERVEURS.id, SERVEURS.numero_serie, SERVEURS.udid, SERVEURS.code_rfid, SERVEURS.etiquette, SERVEURS.date_achat, SERVEURS.duree_garantie "
                + "FROM SERVEURS "
                + "WHERE SERVEURS.id NOT IN "
                + "(SELECT SERVEURS_NON_DEPLOYABLES.id_serveur "
                + "FROM SERVEURS_NON_DEPLOYABLES)";
        return traiteRequeteListServeur_Local(local, request);
     }
    
    
    
    public static List<Serveur> getListServeursFonctionnelsEntrepotBDD (Statement Magency){
        String request = "SELECT SERVEURS.id, SERVEURS.numero_serie, SERVEURS.udid, SERVEURS.code_rfid, IPADS.etiquette, IPADS.date_achat, IPADS.duree_garantie "
                + "FROM SERVEURS "
                + "WHERE SERVEURS.id NOT IN "
                    + "(SELECT SERVEURS_NON_DEPLOYABLES.id_serveur "
                    + "FROM SERVEURS_NON_DEPLOYABLES) "
                + "AND SERVEURS.id NOT IN "
                    + "(SELECT SERVEURS_CONTENEURS.id_serveur "
                    + "FROM SERVEURS_CONTENEURS "
                    + "WHERE SERVEURS_CONTENEURS.id_conteneur IN "
                        + "(SELECT OPE_CONTENEUR.id_conteneur "
                        + "FROM OPE_CONTENEUR "
                        + "WHERE OPE_CONTENEUR.id_op IN "
                            + "(SELECT OPE.id "
                            + "FROM OPE "
                            + "WHERE OPE.confirmation_envoi = '1') "
                        + ") "
                + ") ";
        return traiteRequeteListServeur(Magency, request);
     }
    
    public static List<Serveur> getListServeursFonctionnelsOPBDD (Statement Magency){
        String request = "SELECT SERVEURS.id, SERVEURS.numero_serie, SERVEURS.udid, SERVEURS.code_rfid, SERVEURS.etiquette, SERVEURS.date_achat, SERVEURS.duree_garantie "
                + "FROM SERVEURS "
                + "WHERE SERVEURS.id NOT IN "
                    + "(SELECT SERVEURS_NON_DEPLOYABLES.id_serveur "
                    + "FROM SERVEURS_NON_DEPLOYABLES) "
                + "AND SERVEURS.id IN "
                    + "(SELECT SERVEURS_CONTENEURS.id_serveur "
                    + "FROM SERVEURS_CONTENEURS "
                    + "WHERE SERVEURS_CONTENEURS.id_conteneur IN "
                        + "(SELECT OPE_CONTENEUR.id_conteneur "
                        + "FROM OPE_CONTENEUR "
                        + "WHERE OPE_CONTENEUR.id_op IN "
                            + "(SELECT OPE.id "
                            + "FROM OPE "
                            + "WHERE OPE.confirmation_envoi = '1') "
                        + ") "
                + ") ";
        return traiteRequeteListServeur(Magency, request);
     }
    
    
    
    /**
     * @return La liste de tous les iPads casses contenus dans la base de donnees
     */
    public static List<Serveur> getListServeursManquantsBDD (Statement Magency){
        String request = "SELECT SERVEURS.id, SERVEURS.numero_serie, SERVEURS.udid, SERVEURS.code_rfid, SERVEURS.etiquette, SERVEURS.date_achat, SERVEURS.duree_garantie "
                + "FROM SERVEURS "
                + "WHERE SERVEURS.id IN "
                + "(SELECT SERVEURS_NON_DEPLOYABLES.id_ipad "
                + "FROM SERVEURS_NON_DEPLOYABLES "
                + "WHERE SERVEURS_NON_DEPLOYABLES.etat = 'manquant')"; 
        return traiteRequeteListServeur(Magency, request);
     }
    
    
    
    /**
     * @return La liste de tous les iPads casses contenus dans la base de donnees
     */
    public static List<Serveur> getListServeursManquantsBDD_Local (Statement embedStatement){
        String request = "SELECT SERVEURS.id, SERVEURS.numero_serie, SERVEURS.udid, SERVEURS.code_rfid, SERVEURS.etiquette, SERVEURS.date_achat, SERVEURS.duree_garantie "

                + "FROM SERVEURS "
                + "WHERE SERVEURS.id IN "
                + "(SELECT SERVEURS_NON_DEPLOYABLES.id_ipad "
                + "FROM SERVEURS_NON_DEPLOYABLES "
                + "WHERE SERVEURS_NON_DEPLOYABLES.etat = 'Manquant')"; 
        return traiteRequeteListServeur_Local(embedStatement, request);
     }
    
    
    public static List<Serveur> getListServeursOPBDD (Statement Magency){
        String request = "SELECT SERVEURS.id, SERVEURS.numero_serie, SERVEURS.udid, SERVEURS.code_rfid, SERVEURS.etiquette, SERVEURS.date_achat, SERVEURS.duree_garantie "
                + "FROM SERVEURS "
                + "WHERE SERVEURS.id IN "
                    + "(SELECT SERVEURS_CONTENEURS.id_serveur "
                    + "FROM SERVEURS_CONTENEURS "
                    + "WHERE SERVEURS_CONTENEURS.id_conteneur IN "
                        + "(SELECT OPE_CONTENEUR.id_conteneur "
                        + "FROM OPE_CONTENEUR "
                        + "WHERE OPE_CONTENEUR.id_op IN "
                            + "(SELECT OPE.id "
                            + "FROM OPE "
                            + "WHERE OPE.confirmation_envoi = '1') "
                        + ") "
                + ") ";
        return traiteRequeteListServeur(Magency, request);
     }
    
    
    public static List<Serveur> getListServeursBDD (Statement Magency){
        String request = "SELECT SERVEURS.id, SERVEURS.numero_serie, SERVEURS.udid, SERVEURS.code_rfid, SERVEURS.etiquette, SERVEURS.date_achat, SERVEURS.duree_garantie "
                + "FROM SERVEURS ";
        return traiteRequeteListServeur(Magency, request);
     }
  
    
  /**********************************************Fin_Seveurs******************************************************/  
    public static String miseajourbase(OP op){
        int nb_ipad = 0;
        connexion_bdd bdd = new connexion_bdd();
        SQL_function.erase(bdd.getlocal());
        
        // Recuperation de la dat actuelle sous forme de string
        SimpleDateFormat formatter = new SimpleDateFormat ("yy/MM/dd" );
        String today = formatter.format(new Date());
        
        String request; 
        try {
            request="Update OPE set confirmation_envoi = 1, date_debut = '"+ today + "' where OPE.nom_op = '" + op.getNomOP() + "'";
            bdd.getStatementServer().executeUpdate(request);
            // supprime les conteneurs de l'op avant la mise a jour
            request = "delete from OPE_CONTENEUR where OPE_CONTENEUR.id_op IN"
                    + " (select OPE.id from OPE where OPE.nom_op = '" + op.getNomOP() + "')";
            bdd.getStatementServer().executeUpdate(request);
            // mettre la liste dans la base de données
            for ( Conteneur C : op.getListConteneur()){
                request = "INSERT INTO OPE_CONTENEUR (id_op,id_conteneur) "
                        + "(SELECT OPE.id,CONTENEUR.id "
                        + "From OPE, CONTENEUR "
                        + "where OPE.nom_op = '" + op.getNomOP() + "' "
                        + "and  CONTENEUR.nom_conteneur ='" + C.getName() + "' and "
                        + " CONTENEUR.id NOT IN (select CONTENEUR.id from OPE_CONTENEUR , CONTENEUR where CONTENEUR.id = OPE_CONTENEUR.id_conteneur))";
                bdd.getStatementServer().executeUpdate(request);
                for (Ipad ipad : C.getListIpad()){
                     request = "INSERT INTO IPADS_CONTENEURS (id_ipad,id_conteneur) "
                                + "(SELECT IPADS.id,CONTENEUR.id "
                                + "From IPADS, CONTENEUR "
                                + "where IPADS.numero_serie = '" + ipad.getNumSerie() + "' "
                                + "and  CONTENEUR.nom_conteneur ='" + C.getName() + "' "
                                + "and IPADS.id NOT IN "
                                    + "(select IPADS_CONTENEURS.id_ipad "
                                    + "from IPADS_CONTENEURS))";
                   bdd.getStatementServer().executeUpdate(request);
                   nb_ipad++;
                }      
            }
            
            
       request="Update OPE set confirmation_envoi = 1,"
               + " date_debut = '"+ today + "',"
               + " nb_ipads_env = '" + nb_ipad + "'"
               + " where OPE.nom_op = '" + op.getNomOP() + "'";
       bdd.getStatementServer().executeUpdate(request);    
            
        create_data_base_loc(bdd.getlocal(), op.getNomOP());
        String database = bdd.getlocal().getdatabasename();
        bdd.closeall(); 
        return database;

        } catch (SQLException ex) {
            System.out.println("erreur mise a jour de la base (SQL_function.miseajourbase): " + ex.getMessage());
            bdd.closeall();
            return null;
        }

     }

    public static OP getdataop_local() {
        connexion_bdd bdd = new connexion_bdd();
        
        OP op_courante = null;
        try {
            
            String request;
            ResultSet result;
            request = "select * from OPE";
            
            
            result = bdd.getlocal().getstatement().executeQuery(request);
            while (result.next()){
                System.out.println("OP en cours de chargement : " + result.getString("nom_op"));
                op_courante = new OP(result.getString("nom_op"),result.getInt("nombre_ipads"),result.getInt("nombre_serveurs"),result.getDate("date_debut"),result.getDate("date_fin"),result.getInt("confirmation_envoi"));
            }
            
            request = "select * from CONTENEUR";
            result = bdd.getlocal().getstatement().executeQuery(request);
            while (result.next()){
                op_courante.getListConteneur().add( new Conteneur(result.getString("nom_conteneur")));
            }
            
            for (Conteneur C : op_courante.getListConteneur()){
                request = "select IPADS.* from CONTENEUR , IPADS_CONTENEURS , IPADS "
                        + "where CONTENEUR.nom_conteneur = '" + C.getName()+"' "
                        + "and CONTENEUR.id = IPADS_CONTENEURS.id_conteneur "
                        + "and IPADS.id = IPADS_CONTENEURS.id_ipad";
                
                result = bdd.getlocal().getstatement().executeQuery(request);
                while (result.next()){
                    Ipad iPad = new Ipad(result.getInt("id"),
                            result.getString("numero_serie"),
                            result.getString("udid"),
                            result.getString("code_rfid"),
                            result.getString("etiquette"),
                            result.getDate("date_achat"),
                            result.getInt("duree_garantie") + " jours");
                    C.ajouterIpad(iPad);
                }
            }
            bdd.closeall();
            return op_courante;
        } catch (SQLException ex) {
            System.out.println("erreur de recuperation info table local (SQL_function.getdataop_local): " + ex.getMessage());
            bdd.closeall();
            return null;
        }

    }

    private static void mettre_manquant(EmbedStatement local, String nomOP)
    {
        String request = "INSERT INTO IPADS_NON_DEPLOYABLES (id_ipad,etat,nom_op) "
                + "(SELECT IPADS.id , 'Manquant', '" + nomOP + "' "
                + "FROM IPADS )";
        //System.out.println(request);
        try
        {
            local.executeUpdate(request);
        }
        catch (SQLException ex){
            Logger.getLogger(SQL_function.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * 
     * @param listIdIpadsOP Liste des iPads des iPads qui ont ete scannes a la reception de l'OP
     * @param nomOP Nom de l'OP receptionnee
     */
    public static void receptionOPvalidee(List<Integer> listIdIpadsOP, String nomOP)
    {
        connexion_bdd bdd = new connexion_bdd();
        EmbedStatement local = bdd.getlocal().getstatement();
        try
        {
            String nouvelEtat;
            for(Integer id : listIdIpadsOP)
            {
                // Place l'iPad scanne dans la categorie "Fonctionnel"
                String ancienEtat = getEtatIpad_Local(local, id);
                if(ancienEtat.equalsIgnoreCase("Casse"))
                {
                 nouvelEtat = ancienEtat;
                }
                else
                {
                    nouvelEtat = "Fonctionnel";
                }
                changeEtatIpad_Local(local, id, ancienEtat, nouvelEtat, nomOP);

                // Delie l'iPad scanne de son conteneur
                String requete = "DELETE "
                                + "FROM IPADS_CONTENEURS"
                                + " WHERE id_ipad = " + id;

                    local.executeUpdate(requete);
                }
         }
         catch (SQLException ex)
         {
             System.out.println("erreur mise a jour de la base (SQL_function.receptionOPvalidee): " + ex.getMessage());
         }
        bdd.closeall();
    }
    
    
    
    public static void retireConteneurOP(String nomConteneur)
    {
        connexion_bdd bdd = new connexion_bdd();
        Statement server = bdd.getserver().getstatement();
        String requete1 = "DELETE "
                        + "FROM OPE_CONTENEUR "
                        + "WHERE id_conteneur IN "
                            + "(SELECT CONTENEUR.id "
                            + "FROM CONTENEUR "
                            + "WHERE CONTENEUR.nom_conteneur = '" + nomConteneur + "')";
        String requete2 = "DELETE "
                        + "FROM IPADS_CONTENEURS "
                        + "WHERE id_conteneur IN "
                            + "(SELECT CONTENEUR.id "
                            + "FROM CONTENEUR "
                            + "WHERE CONTENEUR.nom_conteneur = '" + nomConteneur + "')";
        try
        {
            server.executeUpdate(requete1);
            server.executeUpdate(requete2);
        }
        catch (SQLException ex)
        {
            System.out.println("erreur mise a jour de la base (SQL_function.retireConteneurOP): " + ex.getMessage());
        }
        bdd.closeall();
    }
     
    
    
    public static void retireConteneurOP_Local(String nomConteneur)
    {
        connexion_bdd bdd = new connexion_bdd();
        EmbedStatement local = bdd.getlocal().getstatement();
        String requete1 = "DELETE "
                        + "FROM OPE_CONTENEUR "
                        + "WHERE id_conteneur IN "
                            + "(SELECT CONTENEUR.id "
                            + "FROM CONTENEUR "
                            + "WHERE CONTENEUR.nom_conteneur = '" + nomConteneur + "')";
        String requete2 = "DELETE "
                        + "FROM IPADS_CONTENEURS "
                        + "WHERE id_conteneur IN "
                            + "(SELECT CONTENEUR.id "
                            + "FROM CONTENEUR "
                            + "WHERE CONTENEUR.nom_conteneur = '" + nomConteneur + "')";
        try
        {
            local.executeUpdate(requete1);
            local.executeUpdate(requete2);
        }
        catch (SQLException ex)
        {
            System.out.println("erreur mise a jour de la base (SQL_function.retireConteneurOP_Local): " + ex.getMessage());
        }
        bdd.closeall();
    }
    
    
    
    public static String get_NomOp_enBddLocale(EmbedStatement local){
    
    
           String nom ="pas_dOPE";
        String request = "SELECT OPE.nom_op "
                + "FROM OPE"; 
        try
        {
            ResultSet result = local.executeQuery(request);
            if (result.next())
            {
                nom = result.getString("nom_op");
            }else
                nom= "pas_dOPE";
        }
        catch(Exception e)
        {
            System.out.println("SQl_function.java getEtatIpad_Local: requete incorrecte): "+ e.getMessage());
        }
        return nom; 
    
    
    }

    public static void AjoutConteneurDansOP_Local(String nomConteneur, String nomOp)
    {
//        Statement server = new connexion_base().getserver().getstatement();
//        String requete1 = "INSERT INTO OPE_CONTENEUR(id_ope, id_conteneur) "
//                    + "VALUES "
//                + "(SELECT OPE.id "
//                + "FROM OPE "
//                + "WHERE OPE.nom_ope = '" + nomOp
//                + "( SELECT CONTENEUR.id "
//                + "FROM CONTENEUR "
//                + "WHERE" + id + ",'" + nouvelEtat + "', '" + emplacement + "')";
//        try
//        {
//            server.executeUpdate(requete1);
//            server.executeUpdate(requete2);
//        }
//        catch (SQLException ex)
//        {
//            System.out.println("erreur mise a jour de la base (SQL_function.retireConteneurOP): " + ex.getMessage());
//        }
    }     
     
     
    public static void retourOPvalidee(OP op)       
    {        
//        List<Integer> listIdIpadsOP = new ArrayList<Integer>();
//        
//        connexion_base bdd = new connexion_base();
//        
//        String Id_ipad_a_soustraire = "SELECT * FROM IPADS WHERE";
//        int nbipad = 0;
//        for(Conteneur C : op.getListConteneur() )
//        {
//             for(Ipad iPad : C.getListIpad() )
//            {
//               listIdIpadsOP.add(iPad.getDbNum());
//               nbipad++;
//            }
//        }
//        
//        
//        if (!listIdIpadsOP.isEmpty())
//        {
//            int i = 0;
//            while (i<listIdIpadsOP.size())
//            {
//                
//                if (i ==nbipad)
//                {
//                    Id_ipad_a_soustraire += "id<>"+listIdIpadsOP.get(i);
//                }
//                else
//                    Id_ipad_a_soustraire += "id<>"+listIdIpadsOP.get(i) +" and";
//                i++;
//            }
//            
//        }
//        
//        System.out.println(Id_ipad_a_soustraire);
//        
//        
//        List<Ipad> listIpad_libre_BDD = recuperation_iPads_non_Scanne(bdd.getlocal().getstatement(),Id_ipad_a_soustraire );
//        
//        if(!listIpad_libre_BDD.isEmpty())
//        {
//              int i = 0;
//              while(i<listIpad_libre_BDD.size())
//              {
//                changeEtatIpad_Local(bdd.getlocal().getstatement(), 
//                        listIpad_libre_BDD.get(i).getDbNum(), 
//                        listIpad_libre_BDD.get(i).getEtat(), 
//                        "Manquant", 
//                        op.getNomOP());
//              }
//        }
//        
//        for(Conteneur C : op.getListConteneur() )
//        {
//             
//            AjoutConteneurDansOP_Local(C.getName(),op.getNomOP());
//        }
//          
//        
//        try
//        {
//            String nouvelEtat;
//            for(Integer id : listIdIpadsOP)
//            {
//                // Place l'iPad scanne dans la categorie "Fonctionnel"
//               // String anciconnectenEtat = getEtatIpad_Local(local, id);
//                if(ancienEtat.equalsIgnoreCase("Casse"))
//                {
//                 nouvelEtat = ancienEtat;
//                }
//                else
//                {
//                    nouvelEtat = "Fonctionnel";
//                }
//                //changeEtatIpad_Local(local, id, ancienEtat, nouvelEtat, nomOP);
//
//                // Delie l'iPad scanne de son conteneur
//                String requete = "DELETE "
//                                + "FROM IPADS_CONTENEURS"
//                                + " WHERE id_ipad = " + id;
//
//                   // local.executeUpdate(requete);
//                }
//         }
//         catch (SQLException ex)
//         {
//             System.out.println("erreur mise a jour de la base (SQL_function.receptionOPvalidee): " + ex.getMessage());
//         }
//        bdd.closeall();
//                
    }
    
}
