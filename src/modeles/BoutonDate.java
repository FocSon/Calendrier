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
	 * Date associée au bouton.
	 */
	private Date date;

	/**
	 * Construit un objet BoutonDate avec le contructeur de JButton (classe mère) et lui associe un objet Date fourni en paramètre.
	 * 
	 * @param parDate Date à associer à l'objet BoutonDate.
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
	 * Crée une chaine de charactère précisant l'objet Date lié à l'objet appellant.
	 * 
	 * @return String précisant la valeur du champ date.
	 * 
	 * @author Antoine Limerutti
	 * 
	 */
	public String toString() {
		return "Date liée au bouton : " + date.toString();
	}

	/**
	 * Change l'objet Date lié au bouton.
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
	 * Retourne l'objet Date lié au bouton.
	 * 
	 * @return Date qui correspond à la valeur du champ date.
	 * 
	 * @author Antoine Limerutti
	 * 
	 */
	public Date getDate() {
		return date;
	}
}