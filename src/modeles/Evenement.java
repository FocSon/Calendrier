package modeles;

import java.io.Serializable;

/**
 * Abstraction d'un Evenement ayant une date, un titre, un lieu et une heure de d�but et de fin.
 * Appartient au package Modeles.
 * Cette classe est s�rialisable.
 * 
 * @author Antoine Limerutti
 * 
 * @see java.io.Serializable
 *
 * @version 1.0
 */
public class Evenement implements Serializable, Comparable<Evenement>{
	/**
	 * Date de l'�v�nement
	 */
	private Date chDate;
	
	/**
	 * Titre de l'�v�nemet
	 */
	private String chTitre;
	
	/**
	 * Lieu de l'�v�nement
	 */
	private String chLieu;
	
	/**
	 * Heure du d�but de l'�v�nement
	 */
	private int chHeureDebut;
	
	/**
	 * Minute du d�but de l'�v�nement
	 */
	private int chMinutesDebut;
	
	/**
	 * Heure de la fin de l'�v�nement
	 */
	private int chHeureFin;
	
	/**
	 * Minute de la fin de l'�v�nement
	 */
	private int chMinutesFin;
	
	/**
	 * Indique le nombre d'�v�nements cr�es
	 */
	static int chNbEvtInstancies = 0;

	/**
	 * Cr�e un objet Evenement avec la date, le titre, le lieu et les heueres fourni et augmente le nombre d'�v�nement instanci�s.
	 * 
	 * @param parDate jour � donner � la date.
	 * @param parTitre mois � donner � la date.
	 * @param parLieu ann�e � donner � la date.
	 * @param hD Heure de d�but � donner � l'�v�nement
	 * @param hF Heure de fin � donner � l'�v�nement
	 * @param mD Minute de d�but � donner � l'�v�nement
	 * @param mF Minute de fin � donner � l'�v�nement
	 *
	 * @see modeles.Date
	 * 
	 * @author Antoine Limerutti
	 */
	public Evenement(Date parDate, String parTitre, String parLieu, int hD, int hF, int mD, int mF)
		{
		chDate=parDate;
		chTitre=parTitre;
		chLieu=parLieu;
		chHeureDebut=hD;
		chHeureFin=hF;
		chMinutesDebut=mD;
		chMinutesFin=mF;
		chNbEvtInstancies++;
		}

	/**
	 * Compare l'objet Evenement appellant et l'objet Evenement fourni en param�tre.
	 * 
	 * @param evenement2 Evenement � comparer � l'objet appellant
	 * 
	 * @return Entier positif (appellant>argument), negatif (appellant<argument) ou nul (appellant==argument)
	 *
	 * @author Antoine Limerutti
	 */
	public int compareTo(Evenement evenement2){
		int resultatComp=this.chDate.compareTo(evenement2.chDate);
			
		if(resultatComp!=0)
			return resultatComp;
		
		resultatComp=chHeureDebut-evenement2.chHeureDebut;
		if(resultatComp!=0)
			return resultatComp;
		
		resultatComp=chMinutesDebut-evenement2.chMinutesDebut;
		if(resultatComp!=0)
			return resultatComp;

		return 0;	
		}

	/**
	 * Cr�e une chaine de charact�re pr�cisant les champs de l'objet appellant.
	 * 
	 * @return String correspondant aux champs de l'objet appellant.
	 * 
	 * @author Antoine Limerutti
	 */
	public String toString()
		{
		String chaineMinutesDebut;
		String chaineMinutesFin;
		String chaineHeureDebut;
		String chaineHeureFin;

		//les if suivants permettent un affichage de l'heure plus naturel
		if(chHeureDebut<9)
			chaineHeureDebut="0"+String.valueOf(chHeureDebut);
		else
			chaineHeureDebut=String.valueOf(chHeureDebut);
		
		if(chMinutesFin<9)
			chaineMinutesFin="0"+String.valueOf(chMinutesFin);
		else
			chaineMinutesFin=String.valueOf(chMinutesFin);
		
		if(chMinutesDebut<9)
			chaineMinutesDebut="0"+String.valueOf(chMinutesDebut);
		else
			chaineMinutesDebut=String.valueOf(chMinutesDebut);
		
		if(chHeureFin<9)
			chaineHeureFin="0"+String.valueOf(chHeureFin);
		else
			chaineHeureFin=String.valueOf(chHeureFin);

		return chTitre+ ", "+chLieu +" \n"+chaineHeureDebut+":"+chaineMinutesDebut+" - "+chaineHeureFin+":"+chaineMinutesFin+"\n";
		}

	/**
	 * Donne au champ chDate la valeur de l'argument.
	 * 
	 * @param parDate valeur que l'on veut donner au champ chDate.
	 *
	 * @see modeles.Date
	 * 
	 * @author Antoine Limerutti
	 */
	public void setDate(Date parDate){
		chDate=parDate;
		}

	/**
	 * Donne au champ chTitre la valeur de l'argument.
	 * 
	 * @param parTitre Valeur que l'on veut donner au champ chTitre.
	 * 
	 * @author Antoine Limerutti
	 */
	public void setTitre(String parTitre){
		chTitre=parTitre;
		}

	/**
	 * Donne au champ chLieu la valeur de l'argument.
	 * 
	 * @param parLieu Valeur que l'on veut donner au champ chLieu.
	 * 
	 * @author Antoine Limerutti
	 */
	public void setLieu(String parLieu){
		chLieu=parLieu;
		}
	
	/**
	 * Retourne la valeur du champ chDate.
	 * 
	 * @return Date qui correspond � la valeur du champ chDate.
	 *
	 * @see modeles.Date
	 * 
	 * @author Antoine Limerutti
	 */
	public Date getDate(){
		return chDate;
		}

	/**
	 * Retourne la valeur du champ chTitre.
	 * 
	 * @return String qui correspond � la valeur du champ chTitre.
	 * 
	 * @author Antoine Limerutti
	 */
	public String getTitre(){
		return chTitre;
		}
	
	/**
	 * Retourne la valeur du champ chLieu.
	 * 
	 * @return String qui correspond � la valeur du champ chLieu.
	 * 
	 * @author Antoine Limerutti
	 */
	public String getLieu(){
		return chLieu;
		}

	/**
	 * Retourne la valeur du champ chHeureDebut.
	 * 
	 * @return Entier qui correspond � la valeur du champ chHeureDebut.
	 * 
	 * @author Antoine Limerutti
	 */
	public int getHeureDebut(){
		return chHeureDebut;
		}

	/**
	 * Retourne la valeur du champ chHeureFin.
	 * 
	 * @return Entier qui correspond � la valeur du champ chHeureFin.
	 * 
	 * @author Antoine Limerutti
	 */
	public int getHeureFin(){
		return chHeureFin;
		}
	
	/**
	 * Retourne la valeur du champ chMinutesDebut.
	 * 
	 * @return Entier qui correspond � la valeur du champ chMinutesDebut.
	 * 
	 * @author Antoine Limerutti
	 */
	public int getMinutesDebut(){
		return chMinutesDebut;
		}
	
	/**
	 * Retourne la valeur du champ chMinutesFin.
	 * 
	 * @return Entier qui correspond � la valeur du champ chMinutesFin.
	 * 
	 * @author Antoine Limerutti
	 */
	public int getMinutesFin() {
		return chMinutesFin;
		}
	}