package modeles;

import javax.swing.JButton;

/**
 * <b>Abstraction d'un bouton auquel on associe un objet de type Date.<br>
 * Appartient au package modeles.</b>
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
	 *
	 * @see modeles.Date
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
	 * Modifieur du champ date.
	 * 
	 * @param parDate Date ayant la valeur que l'on veut donner au champ date.
	 * 
	 * @author Antoine Limerutti
	 * 
	 */
	public void setDate(Date parDate) {
		this.date = parDate;
	}

	/**
	 * Acesseur du champ date.
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