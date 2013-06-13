/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 *
 * @author uidavy
 */
public class Gestion_Duree_Garantie {

    public int regex(String data) {

        // Create a pattern to match breaks
        Pattern p = Pattern.compile("\\D");
        Pattern t = Pattern.compile("\\d");
        // Split input with the pattern
        //String data = "14 semaines ";
        String d = data.replaceAll(" ", "");
        String[] result = p.split(d);
        String[] resulte = t.split(d);

        int valeur = Integer.parseInt(result[result.length - 1]);
        String periode = resulte[resulte.length - 1];

        int i;
        i = verificatio_duree(periode);
        switch (i) {
            case 1:
                return valeur;
            case 2:
                return 7 * valeur;
            case 3:
                return 365 * valeur;
            case 0:
                return -1;
            default:
                return -1;
        }
    }

    public int verificatio_duree(String duree) {

        if ((duree.equals("jour") || duree.equals("jours"))) {
            return 1;
        } else if ((duree.equals("semaine") || duree.equals("semaines"))) {
            return 2;
        } else if ((duree.equals("an") || duree.equals("ans"))) {
            return 3;
        } else {
            return 0;
        }
    }

    public String donne_duree(int duree_en_jour) {
        if (duree_en_jour <= 30) {
            return duree_en_jour + " jour(s)";
        } else if (duree_en_jour <= 364) {
            return (duree_en_jour / 7) + " semaines" +((duree_en_jour % 7) == 0 ? "": (duree_en_jour % 7)/7 +" jours");
        } else {
            return (duree_en_jour / 365) + " an(s)" +((duree_en_jour % 365) == 0 ? "": (duree_en_jour % 365)/7+ " semaine(s)"); 
        }
    }
    
        public String donne_duree_restant(Date date_achat) {
            
    Date dateActuelle = new Date();
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    double jours = getDaysBetweenDates(date_achat,dateActuelle);
    double jours_restant = 365 - jours;
    if (jours_restant <= 0)
        return "Expirée";
    else
         return (int)jours_restant +" jours restant(s)";  
    }
        	public static double getDaysBetweenDates(Date theEarlierDate, Date theLaterDate) {
		double result = Double.POSITIVE_INFINITY;
		if (theEarlierDate != null && theLaterDate != null) {
			final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;
			Calendar aCal = Calendar.getInstance();
			aCal.setTime(theEarlierDate);
			long aFromOffset = aCal.get(Calendar.DST_OFFSET);
			aCal.setTime(theLaterDate);
			long aToOffset = aCal.get(Calendar.DST_OFFSET);
			long aDayDiffInMili = (theLaterDate.getTime() + aToOffset) - (theEarlierDate.getTime() + aFromOffset);
			result = ((double) aDayDiffInMili / MILLISECONDS_PER_DAY);
		}
		return result;
	}
}
