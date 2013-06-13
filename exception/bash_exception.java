/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

import java.io.IOException;

/**
 *
 * @author magency01
 */
public class bash_exception extends IOException {
    public bash_exception(String cmd){
        System.out.println("erreur commande bash :" + cmd);
    }
}
