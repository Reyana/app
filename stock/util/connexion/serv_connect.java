/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.util.connexion;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.derby.impl.jdbc.EmbedConnection;

/**
 *
 * @author magency01
 */
public class serv_connect {
    
    private Connection my_conn;
    private String database;
    
    public serv_connect(){
        this.my_conn=null;
        this.database = null;
    }
    
    public boolean connect(String url, String dbName,String userName,String password){
       String driver = "com.mysql.jdbc.Driver";

    	
    	String url1 = "jdbc:mysql://192.168.1.2:3306/";
        String dbName1 = "Magency";
        String userName1 = "magency"; 
        String password1 = "";
        
               try {
            Class.forName(driver);
            System.out.println("connection ...");
           this.my_conn = (Connection) DriverManager.getConnection(url1+dbName1,userName1,password1);
            
            /*Ajout par moi pour test serveur*/
           // this.my_conn = (Connection) DriverManager.getConnection(url+dbName,userName,password);

            System.out.println("Connected to the server's database");
            this.database=dbName;
            return true;
        } catch (Exception e) {
            System.out.println("error connexion serveur: "+e.getMessage());
            return false;
        }  
    }
    
    public void close_conn(){
        try{
            this.my_conn.close();
            System.out.println("disconnected from the server's database");
        }catch (Exception e){
            System.out.println("error fermeture connexion "+e.getMessage());
        }
    }
    
    public Connection getconnection(){
        return this.my_conn;
    }
    
    public Statement getstatement(){
        try {
            return (Statement) this.my_conn.createStatement();
        } catch (Exception ex) {
            System.out.println("error serveur connexion:"+ ex.getMessage());
            return null;
        }
    }
    public String getdatabasename(){
        return this.database;
    }
}
