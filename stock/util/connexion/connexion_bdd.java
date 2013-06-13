/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.util.connexion;

import java.sql.Statement;

import org.apache.derby.impl.jdbc.EmbedStatement;

/**
 *
 * @author magency01
 */
public class connexion_bdd {
    
    private local_connect Local = new local_connect() ;
    private serv_connect Magency = new serv_connect() ;
    private EmbedStatement instruction_sql = null;
    private boolean state;
    
    public connexion_bdd(){
       
         if (   //connexion à une base local
                this.Local.connect("temp_data_base", "root", "bourdonnais75")
                 &&
                 //connexion au serveur
                this.Magency.connect("jdbc:mysql://192.168.1.2:3306/Magency",
                "magency", 
                "root",
                "")
             )
             this.state=true;
         else 
             this.state=false;
    }
    
    public Statement getStatementServer(){
        return this.Magency.getstatement();
    }
    public local_connect getlocal(){
        return this.Local;
    }
    
    public serv_connect getserver(){
        return this.Magency;
    }
    
    public boolean getconnectstatu(){
        return this.state;
    }
    
    public void closeall(){
        this.Magency.close_conn();
        this.Local.close_conn();   
    }
    
}
