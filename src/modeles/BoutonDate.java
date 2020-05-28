package modeles;

import javax.swing.JButton;

/**
 * Abstraction d'un bouton auquel on associe un objet de type Date.
 * Appartient au package Modeles.
 * 
 * @author Antoine Limerutti
 * 
 * @see modeles.Date
 * @see javax.swing.JButton
 *
 * @version 1.0
 */
public class BoutonDate extends JButton {
	/**
	 * Date associ�e au bouton.
	 */
	private Date date;

	/**
	 * Construit un objet BoutonDate avec le contructeur de JButton (classe m�re) et lui associe un objet Date fourni en param�tre.
	 * 
	 * @param parDate Date � associer � l'objet BoutonDate.
	 *
	 * @see modeles.Date
	 *
	 * @author Antoine Limerutti
	 */
	public BoutonDate(Date parDate) {
		super (Integer.toString (parDate.getJour()));
		date = parDate;
	}
	
	/**
	 * Cr�e une chaine de charact�re pr�cisant l'objet Date li� � l'objet appellant.
	 * 
	 * @return String pr�cisant la valeur du champ date.
	 * 
	 * @author Antoine Limerutti
	 * 
	 */
	public String toString() {
		return "Date li�e au bouton : " + date.toString();
	}

	/**
	 * Change l'objet Date li� au bouton.
	 * 
	 * @param parDate valeur que l'on veut donner au champ date.
	 * 
	 * @author Antoine Limerutti
	 * 
	 */
	public void setDate(Date parDate) {
		this.date = parDate;
	}
	
	/**
	 * Retourne l'objet Date li� au bouton.
	 * 
	 * @return Date qui correspond � la valeur du champ date.
	 * 
	 * @author Antoine Limerutti
	 * 
	 */
	public Date getDate() {
		return date;
	}
}