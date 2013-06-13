/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.util.connexion;


import java.sql.DriverManager;
/*
 * Mis en commetaire par moi librairies not used
 * import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;;*/
import org.apache.derby.impl.jdbc.EmbedConnection;
import org.apache.derby.impl.jdbc.EmbedStatement;



/**
 *
 * @author magency01
 */
public class local_connect {
    
    private EmbedConnection my_conn;
    private String database;
    private EmbedStatement stat;
    
    public local_connect(){
        this.my_conn=null;
        this.database=null;
        this.stat = null;
    }
    
    
    public boolean connect(String dbName,String userName,String password){
        
        
        String url = "jdbc:derby:" + dbName;
        this.database= dbName;
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        
        try {
            Class.forName(driver);
            System.out.println("connection ...");
            this.my_conn = (EmbedConnection) DriverManager.getConnection(url + ";create=true; user=" + userName + "; password=" + password,userName,password);
            this.stat = (EmbedStatement) this.my_conn.createStatement();
            System.out.println("Connected to the local host's database");
            return true;
        } catch (Exception e) {
            System.out.println("error local connexion "+e.getMessage());
            return false;
        }  
    }
    
    public void close_conn(){
        try{
            this.stat.close();
            this.my_conn.close();
            System.out.println("disconnected from the local host's database");
        }catch (Exception e){
            System.out.println("error local fermeture: "+e.getMessage());
        }
    }
    
    public EmbedConnection getconnection(){
        return this.my_conn;
    }
    

    
    public EmbedStatement getstatement(){
        try {
            return this.stat;
        } catch (Exception ex) {
            System.out.println("error local connexion: "+ ex.getMessage());
            return null;
        }
    }
    public String getdatabasename(){
        return this.database;
    }
    
}
