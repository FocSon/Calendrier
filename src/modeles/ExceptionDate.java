package modeles;

import modeles.Date;

/**
 * Abstraction d'un calendrier.
 * Appartient au package Modeles.
 * 
 * @author Antoine Limerutti
 * 
 * @see Date
 * @see Exception
 *
 * @version 1.0
 */
public class ExceptionDate extends Exception
	{
	/**
	 * Message qui vas �tre affich� avec l'erreur
	 */
	private String chMessage;

	/**
	 * Contstruit un objet ExceptionDate en d�finissant son message.
	 * 
	 * @param parMessage message qui vas devoir �tre affich� si l'erreur est relev�e.
	 * 
	 * @author Antoine Limerutti
	 */
	public ExceptionDate(String parMessage)
		{
		chMessage=parMessage;
		}

	/**
	 * Retourne le message qui vas �tre affich� si l'erreur a lieu.
	 * 
	 * @return String qui correspond  la valeur du champ chMessage.
	 * 
	 * @author Antoine Limerutti
	 */
	public String getMessage() {
		return chMessage;
		}
	}