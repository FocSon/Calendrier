package modeles;

import java.util.GregorianCalendar;
import java.io.Serializable;
import java.util.Calendar;
 
/**
 * Abstraction d'un date ayant un jour, un mois et une ann�e.
 * Appartient au package Modeles.
 * Cette classe est s�rialisable.
 * 
 * @author Antoine Limerutti
 * 
 * @see java.io.Serializable
 * 
 * @version 1.0
 */
public class Date implements Comparable <Date>, Serializable{
	/**
	 * Num�ro du jour
	 */
	private int jour;
	
	/**
	 * Num�ro du mois
	 */
	private int mois;
	
	/**
	 * num�ro de l'ann�e
	 */
  private int annee;
  
	/**
	 * num�ro du jour de la semaine
	 */
  private int jourSemaine ;
  
	/**
	 * Cr�e un objet Date � la date courante.
	 * 
	 * @author Antoine Limerutti
	 * 
	 */
  public Date ()   { 
	  GregorianCalendar dateAuj = new GregorianCalendar ();
	  annee = dateAuj.get (Calendar.YEAR);
	  mois = dateAuj.get (Calendar.MONTH)+1; // janvier = 0, fevrier = 1...
	  jour = dateAuj.get (Calendar.DAY_OF_MONTH);
	  jourSemaine = dateAuj.get (Calendar.DAY_OF_WEEK);
  }
  
	/**
	 * Cr�e un objet Date au jour, au mois et � l'ann�e fourni, d�termine �galement le jour de la semaine correspondant.
	 * 
	 * @param parJour jour � donner � la date.
	 * @param parMois mois � donner � la date.
	 * @param parAnnee ann�e � donner � la date.
	 * 
	 * @author Antoine Limerutti
	 * 
	 */
  public Date (int parJour, int parMois, int parAnnee)   {   
	jour = parJour;
	mois = parMois;
	annee = parAnnee; 
	GregorianCalendar date = new GregorianCalendar (annee,mois-1,jour);
	jourSemaine = date.get (Calendar.DAY_OF_WEEK);				
  }
  
 
   
	/**
	 * Compare l'objet Date appellant et l'objet Date fourni en param�tre.
	 * 
	 * @param parDate Date � comparer � l'objet appellant
	 * 
	 * @return Entier positif (appellant>argument), negatif (appellant<argument) ou nul (appellant==argument)
	 * 
	 * @author Antoine Limerutti
	 */
  public int compareTo (Date parDate) {
    if (annee < parDate.annee)
		return -1;
	if (annee > parDate.annee)
		return 1;
	if (mois < parDate.mois)
		return -1;
	if (mois > parDate.mois)
		return 1;
	if (jour < parDate.jour)
		return -1;
	if (jour > parDate.jour)
		return 1;
	return 0;	
  }
  
	/**
	 * D�termine la date du premier jour de la semaine de l'objet appellant.
	 * 
	 * @return Date du premier jour de la semaine de l'objet appellant
	 * 
	 * @author Antoine Limerutti
	 * 
	 */
  public Date datePremierJourSemaine() {
	  Date date = new Date(jour, mois, annee);
	  
	  while(date.getJourSemaine()!=2) {
		  date = date.dateDeLaVeille();
	  }
	  
	  return date;
  }
 
	/**
	 * D�termine une date correspondant � la date du lendemain de l'objet appellant.
	 * 
	 * @return Date correspondant � la date du lendemain de l'objet appellant.
	 * 
	 * @author Antoine Limerutti
	 * 
	 * @version 1.0
	 */
  public Date dateDuLendemain ()   {	
    if (jour < dernierJourDuMois(mois,annee))
		     return  new Date (jour+1,mois,annee);
		else if (mois < 12)
				return new Date (1,mois+1,annee);
			 else return new Date (1,1,annee+1);	
  }  
  
	/**
	 * D�termine une date correspondant � la date de la veille de l'objet appellant.
	 * 
	 * @return Date correspondant � la date de la veille de l'objet appellant.
	 * 
	 * @author Antoine Limerutti
	 * 
	 * @version 1.0
	 */
  public Date dateDeLaVeille () {    
	if (jour > 1)
			return  new Date (jour-1,mois,annee);
	else if (mois > 1)
			   return new Date (Date.dernierJourDuMois(mois-1, annee),mois-1,annee);
		 else return  new Date (31,12,annee-1);
  }	 
  
	/**
	 * D�termine le dernier jour du mois et le renvoi.
	 * 
	 * @param parMois mois dont il faut trouver la dernier jour.
	 * @param parAnnee ann�e du mois dont on cherche le dernier jour.
	 * 
	 * @return Integer qui correspond au dernier jour du mois donn� pour l'ann�e choisie.
	 * 
	 * @author Antoine Limerutti
	 * 
	 */
  public static int dernierJourDuMois (int parMois, int parAnnee) {
		switch (parMois) {
			 case 2 : if (estBissextile (parAnnee))  return 29 ; else return 28 ;  
			 case 4 : 	 case 6 : 	 case 9 : 	 case 11 : return 30 ;
			 default : return 31 ;
			}  // switch
	  } 
  
	/**
	 * V�rifie si l'ann�e de l'objet appellant est bisextile.
	 * 
	 * @param parAnnee ann�e dont la m�thode vas v�rifier si elle est bisextile.
	 * 
	 * @return booleen qui indique si l'ann�e est bisextile.
	 * 
	 * @author Antoine Limerutti
	 * 
	 */
  private static boolean estBissextile(int parAnnee) {
			return parAnnee % 4 == 0 && (parAnnee % 100 != 0 || parAnnee % 400 == 0);
	  }
  
	/**
	 * Cr�e une chaine de charact�re pr�cisant les champs de l'objet appellant.
	 * 
	 * @return String correspondant au format JourDeLaSemaine Num�roDuJour Mois.
	 * 
	 * @author Antoine Limerutti
	 * 
	 */
  public String toString () {
    String chaine = new String();
    switch (jourSemaine) {
		 case 1: chaine = "dimanche"; break;
		 case 2: chaine = "lundi"; break;
		 case 3: chaine = "mardi"; break;
		 case 4: chaine = "mercredi"; break;
		 case 5: chaine = "jeudi"; break;
		 case 6: chaine = "vendredi"; break;
		 case 7: chaine = "samedi"; break;
		}
	chaine += " " + jour + " ";
	switch (mois) {
		 case 1: chaine += "janvier"; break;
		 case 2: chaine += "f�vrier"; break;
		 case 3: chaine += "mars"; break;
		 case 4: chaine += "avril"; break;
		 case 5: chaine += "mai"; break;
		 case 6: chaine += "juin"; break;
		 case 7: chaine += "juillet"; break;
		 case 8: chaine += "ao�t"; break;
		 case 9: chaine += "septembre"; break;
		 case 10: chaine += "octobre"; break;
		 case 11: chaine += "novembre"; break;
		 case 12: chaine += "d�cembre"; break;
		}	
	return chaine;
  }  
  
	/**
	 * V�rifie si l'objet appellant correspond � la date du jour.
	 * 
	 * @return booleen qui indique si l'objet appellant est � la date du jour.
	 * 
	 * @author Antoine Limerutti
	 * 
	 */
	public boolean isToday() {
		return new Date().compareTo(this) == 0;
	}

	/**
	 * Donne au champ jour la valeur de l'argument et red�finis la jour de la semaine correspondant.
	 * 
	 * @param parJour valeur que l'on veut donner au champ jour.
	 * 
	 * @author Antoine Limerutti
	 * 
	 */
	public void setJour(int parJour) {
		jour=parJour;
		GregorianCalendar date = new GregorianCalendar (annee,mois-1,jour);
		jourSemaine = date.get (Calendar.DAY_OF_WEEK);	
	}
	
	/**
	 * Donne au champ mois la valeur de l'argument et red�finis la jour de la semaine correspondant.
	 * 
	 * @param parMois valeur que l'on veut donner au champ mois.
	 * 
	 * @author Antoine Limerutti
	 * 
	 */
	public void setMois(int parMois) {
		mois=parMois;
		GregorianCalendar date = new GregorianCalendar (annee,mois-1,jour);
		jourSemaine = date.get (Calendar.DAY_OF_WEEK);	
	}
	
	/**
	 * Donne au champ ann�e la valeur de l'argument et red�finis la jour de la semaine correspondant.
	 * 
	 * @param parAnnee valeur que l'on veut donner au champ annee.
	 * 
	 * @author Antoine Limerutti
	 * 
	 */
	public void setAnnee(int parAnnee) {
		annee=parAnnee;
		GregorianCalendar date = new GregorianCalendar (annee,mois-1,jour);
		jourSemaine = date.get (Calendar.DAY_OF_WEEK);	
	}
	
	/**
	 * Retourne la valeur du champ jour.
	 * 
	 * @return Integer qui correspond � la valeur du champ jour.
	 * 
	 * @author Antoine Limerutti
	 * 
	 */
	public int getJour() {
		return jour;
	}
	
	/**
	 * Retourne la valeur du champ mois.
	 * 
	 * @return Integer qui correspond � la valeur du champ mois.
	 * 
	 * @author Antoine Limerutti
	 * 
	 */
	public int getMois() {
		return mois;
	}
	
	/**
	 * Retourne la valeur du champ annee.
	 * 
	 * @return Integer qui correspond � la valeur du champ annee.
	 * 
	 * @author Antoine Limerutti
	 * 
	 */
	public int getAnnee() {
		return annee;
	}

	/**
	 * Retourne la valeur du champ jourSemaine.
	 * 
	 * @return Integer qui correspond � la valeur du champ jourSemaine.
	 * 
	 * @author Antoine Limerutti
	 * 
	 */
	public int getJourSemaine () {
		return jourSemaine;
	}
	
	public int getSemaine() {
		return (new GregorianCalendar(annee,mois-1,jour)).get(Calendar.WEEK_OF_YEAR);
	}

}  // class Date