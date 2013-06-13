/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.util.sql;

/**
 *
 * @author magency01
 */
public class coulomns_types {
    
   private String coulomn_name;
   private String coulomn_type;
    
    
   public coulomns_types(){
       this.coulomn_type = null;
       this.coulomn_name = null;
   }
   
   public String getcolname(){
       return this.coulomn_name;
   }
   
   public String gettype(){
       return this.coulomn_type;
   }
   
   public void setcolname(String name){
       this.coulomn_name=name;
   }
   
   public void setcoltype(String type){
       this.coulomn_type=type;
   }
}
